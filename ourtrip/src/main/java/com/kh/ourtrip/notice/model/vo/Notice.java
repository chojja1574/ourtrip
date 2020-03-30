package com.kh.ourtrip.notice.model.vo;

import java.sql.Date;

public class Notice {
	
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private int noticeCount;
	private Date noticeCreateDT;
	private Date noticeModifyDT;
	private char noticeStatus;
	private int memberNo;

	public Notice() {}

	public Notice(int noticeNo, String noticeTitle, String noticeContent, int noticeCount, Date noticeCreateDT,
			Date noticeModifyDT, char noticeStatus, int memberNo) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeCount = noticeCount;
		this.noticeCreateDT = noticeCreateDT;
		this.noticeModifyDT = noticeModifyDT;
		this.noticeStatus = noticeStatus;
		this.memberNo = memberNo;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public int getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}

	public Date getNoticeCreateDT() {
		return noticeCreateDT;
	}

	public void setNoticeCreateDT(Date noticeCreateDT) {
		this.noticeCreateDT = noticeCreateDT;
	}

	public Date getNoticeModifyDT() {
		return noticeModifyDT;
	}

	public void setNoticeModifyDT(Date noticeModifyDT) {
		this.noticeModifyDT = noticeModifyDT;
	}

	public char getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(char noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeCount=" + noticeCount + ", noticeCreateDT=" + noticeCreateDT + ", noticeModifyDT="
				+ noticeModifyDT + ", noticeStatus=" + noticeStatus + ", memberNo=" + memberNo + "]";
	}
	
}
