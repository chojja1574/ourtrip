package com.kh.ourtrip.planner.controller;

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

import com.kh.ourtrip.planner.model.vo.UserInfo;

public class EchoHandler extends TextWebSocketHandler {
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private List<UserInfo> userList = new ArrayList<UserInfo>(); 
	private Map<String, List<UserInfo>> chatroomMap = new HashMap<String, List<UserInfo>>();
	
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
			/*
			 * System.out.println("=====Members=====");
			 * 
			 * //chatRoomId: "room1", type: 'JOIN', writer: "${userId}"
			 * System.out.println("print use json-simple");
			 * System.out.println("chatRoomId : "+jsonObj.get("chatRoomId"));
			 * System.out.println("type : "+jsonObj.get("type"));
			 * System.out.println("writer : "+jsonObj.get("writer"));
			 * System.out.println("----------------------------");
			 */
            
            if(jsonObj.get("type").equals("JOIN")) {
            	joinChattingRoom(session, jsonObj);
            }
            else {
            	sendChatroom(session, jsonObj);
            }
            
		} catch(Exception e){
			System.out.println("json 아님");
		}
		/*
		 * for (WebSocketSession sess : sessionList) { sess.sendMessage(new
		 * TextMessage(message.getPayload())); }
		 */
	}

	// 클라이언트와 연결을 끊었을 때 실행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		exitChattingRoom(session);
		logger.info("{} 연결 끊김", session.getId());
	}
	
	// 세션에 해당하는 UserInfo 반환 
	private UserInfo findUserInfo(WebSocketSession session) {
		UserInfo uInf = null;
		for(UserInfo ui : userList) {
			if(ui.getSession() == session)
				uInf = ui;
		}
		return uInf;
	}
	
	// 클라이언트가 입장버튼 클릭 시 채팅방 없을 시 맵에 추가, 채팅방 있을 시 채팅방 세션리스트에 세션 추가
	private void joinChattingRoom(WebSocketSession session, JSONObject joinJson) {
		
		
		UserInfo uInf = findUserInfo(session);
		
		if(uInf == null) {
			uInf = new UserInfo(session,(String)joinJson.get("writer"),(String)joinJson.get("chatRoomId"));
			userList.add(uInf);
		}
		String rId = (String) joinJson.get("chatRoomId");
		
		int existUser = -1;
		
		if(chatroomMap.containsKey(rId)) {
			for(UserInfo inf : chatroomMap.get(rId)) {
				if(inf.getSession() == uInf.getSession())
					existUser = 1;
			}
			if(existUser < 0) {
				chatroomMap.get(rId).add(uInf);
				System.out.println(uInf.getId() + "가 " + rId + "에 입장");
			}else
				System.out.println("join 한번만 클릭");
			
		}else {
			chatroomMap.put(rId, new ArrayList<UserInfo>());
			chatroomMap.get(rId).add(uInf);
			System.out.println(uInf.getId() + "가 " + rId + "에 최초 입장");
		}
	}
	
	// 클라이언트 연결 해제시 연결해제된 session을 map에서 찾아 삭제
	private void exitChattingRoom(WebSocketSession session) {
		UserInfo uInf = findUserInfo(session);
		chatroomMap.get(uInf.getChatRoom()).remove(uInf);
		System.out.println(session + "퇴장");
	}
	
	// 서버가 메세지 수신 시 같은 채팅방의 session에게만 메세지 전송
	private void sendChatroom(WebSocketSession session, JSONObject msgJson){
		System.out.println(userList);
		UserInfo sendUInf = findUserInfo(session);
		System.out.println(sendUInf);
		List<UserInfo> uInfList = chatroomMap.get(sendUInf.getChatRoom());
		System.out.println(uInfList);
		try {
			for(UserInfo uInf : uInfList ) {
				uInf.getSession().sendMessage(new TextMessage(msgJson.toJSONString()));
			}
		}catch(Exception e) {
			System.out.println("메세지 전송 실패");
		}
	}
}
