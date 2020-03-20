package com.kh.ourtrip.planner.model.vo;

import java.sql.Date;

public class PlannerView {
	private int plannerNo;
	private String plannerTitle;
	private String plannerPwd;
	private int plannerCost;
	private Date plannerCreateDT;
	private Date plannerModifyDT;
	private Date plannerStartDT;
	private String plannerPublicYN;
	private String plannerDeleteYN;
	private String plannerExpiry;
	private int plannerCount;
	private String plannerUrl;
	private int groupCode;
	private int dateNo;
	private int tripDate;
	private int scheduleNo;
	private String scheduleTitle;
	private int scheduleCost;
	private String scheduleTime;
	private String scheduleMemo;
	private String scheduleLocationNM;
	private double scheduleLat;
	private double scheduleLng;
	private int smallAreaCode;
	private int largeAreaCode;
	
	public PlannerView() {
	}
	
	public PlannerView(int plannerNo, String plannerTitle, String plannerPwd, int plannerCost, Date plannerCreateDT,
			Date plannerModifyDT, Date plannerStartDT, String plannerPublicYN, String plannerDeleteYN,
			String plannerExpiry, int plannerCount, String plannerUrl, int groupCode, int dateNo, int tripDate,
			int scheduleNo, String scheduleTitle, int scheduleCost, String scheduleTime, String scheduleMemo,
			String scheduleLocationNM, double scheduleLat, double scheduleLng, int smallAreaCode, int largeAreaCode) {
		super();
		this.plannerNo = plannerNo;
		this.plannerTitle = plannerTitle;
		this.plannerPwd = plannerPwd;
		this.plannerCost = plannerCost;
		this.plannerCreateDT = plannerCreateDT;
		this.plannerModifyDT = plannerModifyDT;
		this.plannerStartDT = plannerStartDT;
		this.plannerPublicYN = plannerPublicYN;
		this.plannerDeleteYN = plannerDeleteYN;
		this.plannerExpiry = plannerExpiry;
		this.plannerCount = plannerCount;
		this.plannerUrl = plannerUrl;
		this.groupCode = groupCode;
		this.dateNo = dateNo;
		this.tripDate = tripDate;
		this.scheduleNo = scheduleNo;
		this.scheduleTitle = scheduleTitle;
		this.scheduleCost = scheduleCost;
		this.scheduleTime = scheduleTime;
		this.scheduleMemo = scheduleMemo;
		this.scheduleLocationNM = scheduleLocationNM;
		this.scheduleLat = scheduleLat;
		this.scheduleLng = scheduleLng;
		this.smallAreaCode = smallAreaCode;
		this.largeAreaCode = largeAreaCode;
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

	public String getPlannerPwd() {
		return plannerPwd;
	}

	public void setPlannerPwd(String plannerPwd) {
		this.plannerPwd = plannerPwd;
	}

	public int getPlannerCost() {
		return plannerCost;
	}

	public void setPlannerCost(int plannerCost) {
		this.plannerCost = plannerCost;
	}

	public Date getPlannerCreateDT() {
		return plannerCreateDT;
	}

	public void setPlannerCreateDT(Date plannerCreateDT) {
		this.plannerCreateDT = plannerCreateDT;
	}

	public Date getPlannerModifyDT() {
		return plannerModifyDT;
	}

	public void setPlannerModifyDT(Date plannerModifyDT) {
		this.plannerModifyDT = plannerModifyDT;
	}

	public Date getPlannerStartDT() {
		return plannerStartDT;
	}

	public void setPlannerStartDT(Date plannerStartDT) {
		this.plannerStartDT = plannerStartDT;
	}

	public String getPlannerPublicYN() {
		return plannerPublicYN;
	}

	public void setPlannerPublicYN(String plannerPublicYN) {
		this.plannerPublicYN = plannerPublicYN;
	}

	public String getPlannerDeleteYN() {
		return plannerDeleteYN;
	}

	public void setPlannerDeleteYN(String plannerDeleteYN) {
		this.plannerDeleteYN = plannerDeleteYN;
	}

	public String getPlannerExpiry() {
		return plannerExpiry;
	}

	public void setPlannerExpiry(String plannerExpiry) {
		this.plannerExpiry = plannerExpiry;
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

	public int getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}

	public int getDateNo() {
		return dateNo;
	}

	public void setDateNo(int dateNo) {
		this.dateNo = dateNo;
	}

	public int getTripDate() {
		return tripDate;
	}

	public void setTripDate(int tripDate) {
		this.tripDate = tripDate;
	}

	public int getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public String getScheduleTitle() {
		return scheduleTitle;
	}

	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}

	public int getScheduleCost() {
		return scheduleCost;
	}

	public void setScheduleCost(int scheduleCost) {
		this.scheduleCost = scheduleCost;
	}

	public String getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public String getScheduleMemo() {
		return scheduleMemo;
	}

	public void setScheduleMemo(String scheduleMemo) {
		this.scheduleMemo = scheduleMemo;
	}

	public String getScheduleLocationNM() {
		return scheduleLocationNM;
	}

	public void setScheduleLocationNM(String scheduleLocationNM) {
		this.scheduleLocationNM = scheduleLocationNM;
	}

	public double getScheduleLat() {
		return scheduleLat;
	}

	public void setScheduleLat(double scheduleLat) {
		this.scheduleLat = scheduleLat;
	}

	public double getScheduleLng() {
		return scheduleLng;
	}

	public void setScheduleLng(double scheduleLng) {
		this.scheduleLng = scheduleLng;
	}

	public int getSmallAreaCode() {
		return smallAreaCode;
	}

	public void setSmallAreaCode(int smallAreaCode) {
		this.smallAreaCode = smallAreaCode;
	}

	public int getLargeAreaCode() {
		return largeAreaCode;
	}

	public void setLargeAreaCode(int largeAreaCode) {
		this.largeAreaCode = largeAreaCode;
	}

	@Override
	public String toString() {
		return "PlannerView [plannerNo=" + plannerNo + ", plannerTitle=" + plannerTitle + ", plannerPwd=" + plannerPwd
				+ ", plannerCost=" + plannerCost + ", plannerCreateDT=" + plannerCreateDT + ", plannerModifyDT="
				+ plannerModifyDT + ", plannerStartDT=" + plannerStartDT + ", plannerPublicYN=" + plannerPublicYN
				+ ", plannerDeleteYN=" + plannerDeleteYN + ", plannerExpiry=" + plannerExpiry + ", plannerCount="
				+ plannerCount + ", plannerUrl=" + plannerUrl + ", groupCode=" + groupCode + ", dateNo=" + dateNo
				+ ", tripDate=" + tripDate + ", scheduleNo=" + scheduleNo + ", scheduleTitle=" + scheduleTitle
				+ ", scheduleCost=" + scheduleCost + ", scheduleTime=" + scheduleTime + ", scheduleMemo=" + scheduleMemo
				+ ", scheduleLocationNM=" + scheduleLocationNM + ", scheduleLat=" + scheduleLat + ", scheduleLng="
				+ scheduleLng + ", smallAreaCode=" + smallAreaCode + ", largeAreaCode=" + largeAreaCode + "]";
	}

	
}
