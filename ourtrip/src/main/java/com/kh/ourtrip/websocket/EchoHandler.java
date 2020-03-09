package com.kh.ourtrip.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private Map<String, List<WebSocketSession>> chatroomMap = new HashMap<String, List<WebSocketSession>>();
	private Map<WebSocketSession, List<String>> userjoinMap = new HashMap<WebSocketSession, List<String>>();
	
	// 클라이언트와 연결 이후에 실행되는 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		logger.info("{}이 연결됨 ({}) ", session.getId(),sessionList.get(sessionList.size()-1));
	}

	// 클라이언트가 서버로 메시지를 전송했을 때 실행되는 메서드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
		JSONObject jsonObj = null;
		try {
			JSONParser jsonParser = new JSONParser();
            jsonObj = (JSONObject) jsonParser.parse(message.getPayload());

            System.out.println("=====Members=====");

            //chatRoomId: "room1", type: 'JOIN', writer: "${userId}"
            System.out.println("print use json-simple");
            System.out.println("chatRoomId : "+jsonObj.get("chatRoomId"));
            System.out.println("type : "+jsonObj.get("type"));
            System.out.println("writer : "+jsonObj.get("writer"));
            System.out.println("----------------------------");
            
            if(jsonObj.get("type").equals("JOIN")) {
            	joinChattingRoom(session, jsonObj);
            }
            
		} catch(Exception e){
			System.out.println("json 아님");
		}
		for (WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage(message.getPayload()));
		}
	}

	// 클라이언트와 연결을 끊었을 때 실행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		logger.info("{} 연결 끊김", session.getId());
	}
	
	// 클라이언트가 입장버튼 클릭 시 채팅방 없을 시 맵에 추가, 채팅방 있을 시 채팅방 세션리스트에 세션 추가
	public void joinChattingRoom(WebSocketSession session, JSONObject joinJson) {
		String rId = (String) joinJson.get("chatRoomId");
		if(chatroomMap.containsKey(rId)) {
			chatroomMap.get(rId).add(session);
			System.out.println(session.getId() + "가 " + rId + "에 입장");
		}else {
			chatroomMap.put(rId, new ArrayList<WebSocketSession>());
			chatroomMap.get(rId).add(session);
			System.out.println(session.getId() + "가 " + rId + "에 최초 입장");
		}
		if(userjoinMap.containsKey(session)) {
			userjoinMap.get(session).add(rId);
		}else {
			userjoinMap.put(session, new ArrayList<String>());
			userjoinMap.get(session).add(rId);
		}
	}
	
	// 클라이언트 연결 해제시 연결해제된 session을 map에서 찾아 삭제
	public void exitChattingRoom(WebSocketSession session) {
		List<String> roomList = null;
		if(userjoinMap.containsKey(session)) {
			roomList = (List<String>)userjoinMap.get(session);
			for(String room : roomList) {
				chatroomMap.get(room).remove(session);
				System.out.println(room + "에서 " + session + " 나감");
			}
		}
	}
}
