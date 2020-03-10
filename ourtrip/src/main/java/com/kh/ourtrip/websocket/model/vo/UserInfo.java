package com.kh.ourtrip.websocket.model.vo;

import org.springframework.web.socket.WebSocketSession;

public class UserInfo {
	
	private WebSocketSession session;
	private String id;
	private String chatRoom;
	
	public UserInfo() {
		super();
	}

	public UserInfo(WebSocketSession session, String id, String chatRoom) {
		super();
		this.session = session;
		this.id = id;
		this.chatRoom = chatRoom;
	}

	public WebSocketSession getSession() {
		return session;
	}

	public void setSession(WebSocketSession session) {
		this.session = session;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(String chatRoom) {
		this.chatRoom = chatRoom;
	}

}
