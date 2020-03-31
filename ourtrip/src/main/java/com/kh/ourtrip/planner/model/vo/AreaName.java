package com.kh.ourtrip.planner.model.vo;

public class AreaName {
	
	private int plannerNo;
	private int largeAreaCode;
	private String largeAreaName;
	private int smallAreaCode;
	private String smallAreaName;
	
	public AreaName() {
		// TODO Auto-generated constructor stub
	}

	public AreaName(int plannerNo, int largeAreaCode, String largeAreaName, int smallAreaCode, String smallAreaName) {
		super();
		this.plannerNo = plannerNo;
		this.largeAreaCode = largeAreaCode;
		this.largeAreaName = largeAreaName;
		this.smallAreaCode = smallAreaCode;
		this.smallAreaName = smallAreaName;
	}

	public AreaName(int plannerNo, String largeAreaName, String smallAreaName) {
		super();
		this.plannerNo = plannerNo;
		this.largeAreaName = largeAreaName;
		this.smallAreaName = smallAreaName;
	}

	public int getPlannerNo() {
		return plannerNo;
	}

	public void setPlannerNo(int plannerNo) {
		this.plannerNo = plannerNo;
	}

	public int getLargeAreaCode() {
		return largeAreaCode;
	}

	public void setLargeAreaCode(int largeAreaCode) {
		this.largeAreaCode = largeAreaCode;
	}

	public String getLargeAreaName() {
		return largeAreaName;
	}

	public void setLargeAreaName(String largeAreaName) {
		this.largeAreaName = largeAreaName;
	}

	public int getSmallAreaCode() {
		return smallAreaCode;
	}

	public void setSmallAreaCode(int smallAreaCode) {
		this.smallAreaCode = smallAreaCode;
	}

	public String getSmallAreaName() {
		return smallAreaName;
	}

	public void setSmallAreaName(String smallAreaName) {
		this.smallAreaName = smallAreaName;
	}
	
	public String toJsonString() {
		return "{\"pno\":\""+plannerNo+"\",\"large\":\""+largeAreaCode+"\",\"largeNM\":\""+largeAreaName+"\",\"small\":\""+smallAreaCode+"\",\"smallNM\":\""+smallAreaName+"\"}";
	}
	
	@Override
	public String toString() {
		return "AreaName [plannerNo=" + plannerNo + ", largeAreaCode=" + largeAreaCode + ", largeAreaName="
				+ largeAreaName + ", smallAreaCode=" + smallAreaCode + ", smallAreaName=" + smallAreaName + "]";
	}
	
	
}
