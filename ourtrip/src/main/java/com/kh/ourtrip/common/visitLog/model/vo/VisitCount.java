package com.kh.ourtrip.common.visitLog.model.vo;

import java.sql.Date;

public class VisitCount {
	private int visitNo;
	private Date visitDate;
	private String visitIp;
	
	public VisitCount() {
		// TODO Auto-generated constructor stub
	}

	public VisitCount(int visitNo, Date visitDate, String visitIp) {
		super();
		this.visitNo = visitNo;
		this.visitDate = visitDate;
		this.visitIp = visitIp;
	}

	
	public VisitCount(String visitIp) {
		super();
		this.visitIp = visitIp;
	}
	
	public int getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(int visitNo) {
		this.visitNo = visitNo;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitIp() {
		return visitIp;
	}

	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}

	

	@Override
	public String toString() {
		return "VisitCount [visitNo=" + visitNo + ", visitDate=" + visitDate + ", visitIp=" + visitIp + "]";
	}

		
	
	
}
