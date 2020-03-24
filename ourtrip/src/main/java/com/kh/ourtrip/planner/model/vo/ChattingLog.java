package com.kh.ourtrip.planner.model.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChattingLog {
	private int chatNo;
	private String chatContent;
	private Timestamp chatTime;
	private int plannerNo;
	private int memberNo;
	
	public ChattingLog() {}

	public ChattingLog(int chatNo, String chatContent, Timestamp chatTime, int plannerNo, int memberNo) {
		super();
		this.chatNo = chatNo;
		this.chatContent = chatContent;
		this.chatTime = chatTime;
		this.plannerNo = plannerNo;
		this.memberNo = memberNo;
	}

	public int getChatNo() {
		return chatNo;
	}

	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public Timestamp getChatTime() {
		return chatTime;
	}

	public void setChatTime(Timestamp chatTime) {
		this.chatTime = chatTime;
	}

	public int getPlannerNo() {
		return plannerNo;
	}

	public void setPlannerNo(int plannerNo) {
		this.plannerNo = plannerNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String toJsonString() {
		
		String str = null;
		String time = chatTime.toString().substring(11, 16);
		
		str = "{\"chatNo\":\"" + chatNo + "\",\"chatContent\":\"" + chatContent + "\",\"chatTime\":\"" + time +
				"\",\"plannerNo\":\"" + plannerNo + "\",\"memberNo\":\"" + memberNo +"\"}";
		
		return str;
	}
	
	@Override
	public String toString() {
		return "ChattingLog [chatNo=" + chatNo + ", chatContent=" + chatContent + ", chatTime=" + chatTime
				+ ", plannerNo=" + plannerNo + ", memberNo=" + memberNo + "]";
	}
	
}
