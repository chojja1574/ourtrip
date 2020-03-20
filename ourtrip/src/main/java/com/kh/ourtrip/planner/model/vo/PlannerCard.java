package com.kh.ourtrip.planner.model.vo;

import java.sql.Date;

public class PlannerCard {
	
	private int plannerNo; //플래너번호
	private String plannerTitle; //플래너제목
	private Date plannerStartDT; //여행시작일
	private int tripDate; // 여행기간
	private String groupName; // 그룹네임
	private String LargeAreaName; // 대지역명
	private String SmallAreaName; // 소지역명
	private int plannerCount;  //조회수
	private String plannerUrl; //바로가기 
	
	public PlannerCard() {
		// TODO Auto-generated constructor stub
	}

	public PlannerCard(int plannerNo, String plannerTitle, Date plannerStartDT, int tripDate, String groupName,
			String largeAreaName, String smallAreaName, int plannerCount, String plannerUrl) {
		super();
		this.plannerNo = plannerNo;
		this.plannerTitle = plannerTitle;
		this.plannerStartDT = plannerStartDT;
		this.tripDate = tripDate;
		this.groupName = groupName;
		LargeAreaName = largeAreaName;
		SmallAreaName = smallAreaName;
		this.plannerCount = plannerCount;
		this.plannerUrl = plannerUrl;
	}

	public int getPlannerNo() {
		return plannerNo;
	}

	public void setPlannerNo(int plannerNo) {
		this.plannerNo = plannerNo;
	}

	public String getPlannerTitle() {
		return plannerTitle;
	}

	public void setPlannerTitle(String plannerTitle) {
		this.plannerTitle = plannerTitle;
	}

	public Date getPlannerStartDT() {
		return plannerStartDT;
	}

	public void setPlannerStartDT(Date plannerStartDT) {
		this.plannerStartDT = plannerStartDT;
	}

	public int getTripDate() {
		return tripDate;
	}

	public void setTripDate(int tripDate) {
		this.tripDate = tripDate;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getLargeAreaName() {
		return LargeAreaName;
	}

	public void setLargeAreaName(String largeAreaName) {
		LargeAreaName = largeAreaName;
	}

	public String getSmallAreaName() {
		return SmallAreaName;
	}

	public void setSmallAreaName(String smallAreaName) {
		SmallAreaName = smallAreaName;
	}

	public int getPlannerCount() {
		return plannerCount;
	}

	public void setPlannerCount(int plannerCount) {
		this.plannerCount = plannerCount;
	}

	public String getPlannerUrl() {
		return plannerUrl;
	}

	public void setPlannerUrl(String plannerUrl) {
		this.plannerUrl = plannerUrl;
	}

	@Override
	public String toString() {
		return "PlannerCard [plannerNo=" + plannerNo + ", plannerTitle=" + plannerTitle + ", plannerStartDT="
				+ plannerStartDT + ", tripDate=" + tripDate + ", groupName=" + groupName + ", LargeAreaName="
				+ LargeAreaName + ", SmallAreaName=" + SmallAreaName + ", plannerCount=" + plannerCount
				+ ", plannerUrl=" + plannerUrl + "]";
	}
	
	
}
