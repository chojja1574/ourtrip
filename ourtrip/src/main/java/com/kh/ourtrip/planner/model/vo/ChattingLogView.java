package com.kh.ourtrip.planner.model.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChattingLogView {
	private int chatNo;
	private String chatContent;
	private Timestamp chatTime;
	private int plannerNo;
	private int memberNo;
	private String memberNickName;
	private String imagePath;
	
	public ChattingLogView() {}

	public ChattingLogView(int chatNo, String chatContent, Timestamp chatTime, int plannerNo, int memberNo,
			String memberNickName, String imagePath) {
		super();
		this.chatNo = chatNo;
		this.chatContent = chatContent;
		this.chatTime = chatTime;
		this.plannerNo = plannerNo;
		this.memberNo = memberNo;
		this.memberNickName = memberNickName;
		this.imagePath = imagePath;
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

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String toJsonString() {
		
		String str = null;
		String time = chatTime.toString().substring(11, 16);
		String convertImagePath = imagePath.replaceAll("\\",".");
		System.out.println("convertImagePath : " + convertImagePath);
		str = "{\"chatNo\":\"" + chatNo + "\",\"chatContent\":\"" + chatContent + "\",\"chatTime\":\"" + time + "\",\"imagePath\":\"" + convertImagePath + 
				"\",\"plannerNo\":\"" + plannerNo + "\",\"memberNo\":\"" + memberNo + "\",\"memberNickName\":\"" + memberNickName +"\"}";
		return str;
	}

	@Override
	public String toString() {
		return "ChattingLogView [chatNo=" + chatNo + ", chatContent=" + chatContent + ", chatTime=" + chatTime
				+ ", plannerNo=" + plannerNo + ", memberNo=" + memberNo + ", memberNickName=" + memberNickName
				+ ", imagePath=" + imagePath + "]";
	}
	
}
