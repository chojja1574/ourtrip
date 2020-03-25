package com.kh.ourtrip.planner.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.kh.ourtrip.planner.model.service.PlannerService2;
import com.kh.ourtrip.planner.model.vo.ChattingLogView;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.Schedule;
import com.kh.ourtrip.planner.model.vo.UserInfo;

public class EchoHandler extends TextWebSocketHandler {
	
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private List<UserInfo> userList = new ArrayList<UserInfo>(); 
	private Map<String, List<UserInfo>> chatroomMap = new HashMap<String, List<UserInfo>>();
	
	@Autowired
	private PlannerService2 plannerService;
	
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
            }else if(jsonObj.get("type").equals("addDate")) {
            	addDate(session, jsonObj);
            }else if(jsonObj.get("type").equals("deleteDate")) {
            	deleteDate(session, jsonObj);
            }else if(jsonObj.get("type").equals("orderDate")) {
            	orderDate(session, jsonObj);
            }else if(jsonObj.get("type").equals("updateSchedule")) {
            	updateSchedule(session, jsonObj);
            }else if(jsonObj.get("type").equals("addSchedule")) {
            	addSchedule(session, jsonObj);
            }else if(jsonObj.get("type").equals("removeSchedule")) {
            	removeSchedule(session, jsonObj);
            }else if(jsonObj.get("type").equals("msg")) {
            	chattingMessage(session, jsonObj);
            }else {
            	sendChatroom(session, jsonObj);
            }
            
		} catch(Exception e){
			e.printStackTrace();
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
		List<UserInfo> uInfList = chatroomMap.get(sendUInf.getChatRoom());
		try {
			for(UserInfo uInf : uInfList ) {
				uInf.getSession().sendMessage(new TextMessage(msgJson.toJSONString()));
			}
		}catch(Exception e) {
			System.out.println("메세지 전송 실패");
		}
	}
	
	// 일차 추가
	private int addDate(WebSocketSession session, JSONObject msgJson) throws Exception{
		int result = 0;
		System.out.println("addDate");
		
		int dateNo = plannerService.getNextDateNo();
		int scheduleNo = plannerService.getNextScheduleNo();
		Day day = new Day();
		day.setPlannerNo(Integer.parseInt(msgJson.get("pno").toString()));
		day.setDateNo(dateNo);
		
		day.setSchedules(new ArrayList<Schedule>());
		
		day.getSchedules().add(new Schedule());
		day.getSchedules().get(0).setScheduleNo(scheduleNo);
		day.getSchedules().get(0).setDateNo(dateNo);
		
		result = plannerService.insertDate(day);
		if(result > 0) {
			System.out.println("날짜 생성 완료");
			result = plannerService.insertDefaultSchedule(day.getSchedules().get(0));
			if(result > 0) {
				System.out.println("기본일정 생성 완료");
			}
		}
		msgJson.putIfAbsent("dno", dateNo);
		msgJson.putIfAbsent("sno", scheduleNo);
		System.out.println(msgJson);
		sendChatroom(session, msgJson);
		return result;
	}
	
	// 일차 정렬
	private int orderDate(WebSocketSession session, JSONObject msgJson) throws Exception {
		int result = 0;

		JSONArray dateInfo = (JSONArray)(msgJson.get("dateInfo"));

		List<Day> dayList = new ArrayList<Day>();
		Day tempDay = null;
		
		if (dateInfo != null) { 
		   for (int i=0;i<dateInfo.size();i++){ 
			   JSONParser jsonParser = new JSONParser();
			   JSONObject jsonObj = (JSONObject) jsonParser.parse(dateInfo.get(i).toString());
			   tempDay = new Day();
			   int dno = Integer.parseInt(jsonObj.get("dno").toString());
			   int order = Integer.parseInt(jsonObj.get("order").toString());
			   tempDay.setDateNo(dno);
			   tempDay.setTripDate(order);
			   dayList.add(tempDay);
		   } 
		}
		
		result = plannerService.updateTripDate(dayList);
		
		System.out.println("orderDate result : " + result);
		
		return result;
	}
	
	// 일차 삭제
	private int deleteDate(WebSocketSession session, JSONObject msgJson) throws Exception {
		int result = 0;
		System.out.println("deleteDate");
		int dateNo = Integer.parseInt(msgJson.get("dno").toString());
		System.out.println("dateNo : " + dateNo);
		result = plannerService.deleteDate(dateNo);
		sendChatroom(session, msgJson);
		
		return result;
	}
	
	// 일정 저장
	private int updateSchedule(WebSocketSession session, JSONObject msgJson) throws Exception {
		int result = 0;
		System.out.println("updateSchedule");
		
		Schedule sche = new Schedule();
		sche.setScheduleNo(Integer.parseInt(msgJson.get("sno").toString()));
		sche.setScheduleTitle(msgJson.get("title").toString());
		sche.setScheduleCost(Integer.parseInt(msgJson.get("cost").toString()));
		sche.setScheduleTime(msgJson.get("time").toString().replace(":",""));
		sche.setScheduleMemo(msgJson.get("memo").toString());
		sche.setScheduleLocationNM(msgJson.get("location").toString());
		sche.setScheduleLat(Double.parseDouble(msgJson.get("lat").toString()));
		sche.setScheduleLng(Double.parseDouble(msgJson.get("lng").toString()));
		System.out.println(sche);
		result = plannerService.updateSchedule(sche);
		
		System.out.println(result);
		
		sendChatroom(session, msgJson);
		return result;
	}
	
	// 일정 추가
	private int addSchedule(WebSocketSession session, JSONObject msgJson) throws Exception {
		int result = 0;
		System.out.println("addSchedule");
		int scheduleNo = plannerService.getNextScheduleNo();
		msgJson.put("sno", scheduleNo);
		 //{"pno":"1","chatRoomId":"","type":"addSchedule","id":"","dno":59,"title":"제목1111","time":"11:02","location":"11","cost":"11111","memo":"113412","lat":36.41451465711704,"lng":128.75637817215454}
		
		Schedule sche = new Schedule();
		sche.setScheduleNo(Integer.parseInt(msgJson.get("sno").toString()));
		sche.setScheduleTitle(msgJson.get("title").toString());
		sche.setScheduleCost(Integer.parseInt(msgJson.get("cost").toString()));
		sche.setScheduleTime(msgJson.get("time").toString().replace(":",""));
		sche.setScheduleMemo(msgJson.get("memo").toString());
		sche.setScheduleLocationNM(msgJson.get("location").toString());
		sche.setScheduleLat(Double.parseDouble(msgJson.get("lat").toString()));
		sche.setScheduleLng(Double.parseDouble(msgJson.get("lng").toString()));
		sche.setDateNo(Integer.parseInt(msgJson.get("dno").toString()));
		
		result = plannerService.insertSchedule(sche);
		
		sendChatroom(session, msgJson);
		return result;
	}
	
	// 일정 삭제
	private int removeSchedule(WebSocketSession session, JSONObject msgJson) throws Exception {
		int result = 0;
		System.out.println("removeSchedule");
		
		result = plannerService.deleteSchedule(Integer.parseInt(msgJson.get("sno").toString()));
		
		sendChatroom(session, msgJson);
		return result;
	}
	
	private int chattingMessage(WebSocketSession session, JSONObject msgJson) throws Exception {
		int result = 0;
		System.out.println("chattingMessage");
		
		ChattingLogView chatLog = new ChattingLogView();
		System.out.println(msgJson.toString());
		chatLog.setMemberNo(Integer.parseInt(msgJson.get("memberNo").toString()));
		chatLog.setPlannerNo(Integer.parseInt(msgJson.get("pno").toString()));
		chatLog.setChatContent(msgJson.get("content").toString());
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String today = null;
		today = formatter.format(cal.getTime());
		Timestamp ts = Timestamp.valueOf(today);
		chatLog.setChatTime(ts);
		System.out.println("timestamp substring : " + ts.toString().substring(11, 16));
		msgJson.put("time",today.substring(11, 16));
		
		result = plannerService.insertChattingLog(chatLog);
		//{"pno":"1","chatRoomId":"","type":"msg","id":"2","content":"asdf","time":"18:15"}
		sendChatroom(session, msgJson);
		return result;
	}
}
