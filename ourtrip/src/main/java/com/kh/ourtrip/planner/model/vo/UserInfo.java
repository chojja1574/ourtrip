package com.kh.ourtrip.planner.model.vo;

import org.springframework.web.socket.WebSocketSession;

public class UserInfo {
	
	private WebSocketSession session;
	private String id;
	private int plannerNo;
	
	public UserInfo() {
		super();
	}

	public UserInfo(WebSocketSession session, String id, int plannerNo) {
		super();
		this.session = session;
		this.id = id;
		this.plannerNo = plannerNo;
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

	public int getPlannerNo() {
		return plannerNo;
	}

	public void setPlannerNo(int plannerNo) {
		this.plannerNo = plannerNo;
	}

	@Override
	public String toString() {
		return "UserInfo [session=" + session + ", id=" + id + ", plannerNo=" + plannerNo + "]";
	}
	
}
