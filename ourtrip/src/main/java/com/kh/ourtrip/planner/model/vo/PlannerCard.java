package com.kh.ourtrip.planner.model.vo;

import java.sql.Date;
import java.util.List;

public class PlannerCard {
	
	private int plannerNo; //플래너번호
	private String plannerTitle; //플래너제목
	private Date plannerStartDT; //여행시작일
	private int tripDate; // 여행기간
	private String groupName; // 그룹네임
	private List<AreaName> areaNames; // 지역
	private int plannerCount;  //조회수
	private String plannerUrl; //바로가기 
	private String plannerPermission; //권한등급
	
	public PlannerCard() {
		// TODO Auto-generated constructor stub
	}

	public PlannerCard(int plannerNo, String plannerTitle, Date plannerStartDT, int tripDate, String groupName,
			List<AreaName> areaNames, int plannerCount, String plannerUrl, String plannerPermission) {
		super();
		this.plannerNo = plannerNo;
		this.plannerTitle = plannerTitle;
		this.plannerStartDT = plannerStartDT;
		this.tripDate = tripDate;
		this.groupName = groupName;
		this.areaNames = areaNames;
		this.plannerCount = plannerCount;
		this.plannerUrl = plannerUrl;
		this.plannerPermission = plannerPermission;
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

	public List<AreaName> getareaNames() {
		return areaNames;
	}

	public void setareaNames(List<AreaName> areaNames) {
		this.areaNames = areaNames;
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

	public String getPlannerPermission() {
		return plannerPermission;
	}

	public void setPlannerPermission(String plannerPermission) {
		this.plannerPermission = plannerPermission;
	}


	@Override
	public String toString() {
		return "PlannerCard [plannerNo=" + plannerNo + ", plannerTitle=" + plannerTitle + ", plannerStartDT="
				+ plannerStartDT + ", tripDate=" + tripDate + ", groupName=" + groupName + ", areaNames=" + areaNames
				+ ", plannerCount=" + plannerCount + ", plannerUrl=" + plannerUrl + ", plannerPermission="
				+ plannerPermission + "]";
	}
	
	

	
}
