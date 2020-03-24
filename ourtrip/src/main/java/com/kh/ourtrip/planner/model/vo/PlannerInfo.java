package com.kh.ourtrip.planner.model.vo;

import java.sql.Date;
import java.util.List;

public class PlannerInfo {
	
	private int memberNo;
	private	int plannerNo;
	private String plannerTitle;
	private Date plannerCreateDT;
	private Date plannerStartDT;
	private String plannerDeleteYN;
	private int plannerCount;
	private String plannerUrl;
	private String groupName; 
	private String largeAreaName;
	private String smallAreaName;
	
	public PlannerInfo() {
		// TODO Auto-generated constructor stub
	}

	public PlannerInfo(int memberNo, int plannerNo, String plannerTitle, Date plannerCreateDT, Date plannerStartDT,
			String plannerDeleteYN, int plannerCount, String plannerUrl, String groupName, String largeAreaName,
			String smallAreaName) {
		super();
		this.memberNo = memberNo;
		this.plannerNo = plannerNo;
		this.plannerTitle = plannerTitle;
		this.plannerCreateDT = plannerCreateDT;
		this.plannerStartDT = plannerStartDT;
		this.plannerDeleteYN = plannerDeleteYN;
		this.plannerCount = plannerCount;
		this.plannerUrl = plannerUrl;
		this.groupName = groupName;
		this.largeAreaName = largeAreaName;
		this.smallAreaName = smallAreaName;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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

	public Date getPlannerCreateDT() {
		return plannerCreateDT;
	}

	public void setPlannerCreateDT(Date plannerCreateDT) {
		this.plannerCreateDT = plannerCreateDT;
	}

	public Date getPlannerStartDT() {
		return plannerStartDT;
	}

	public void setPlannerStartDT(Date plannerStartDT) {
		this.plannerStartDT = plannerStartDT;
	}

	public String getPlannerDeleteYN() {
		return plannerDeleteYN;
	}

	public void setPlannerDeleteYN(String plannerDeleteYN) {
		this.plannerDeleteYN = plannerDeleteYN;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getLargeAreaName() {
		return largeAreaName;
	}

	public void setLargeAreaName(String largeAreaName) {
		this.largeAreaName = largeAreaName;
	}

	public String getSmallAreaName() {
		return smallAreaName;
	}

	public void setSmallAreaName(String smallAreaName) {
		this.smallAreaName = smallAreaName;
	}

	@Override
	public String toString() {
		return "PlannerInfo [memberNo=" + memberNo + ", plannerNo=" + plannerNo + ", plannerTitle=" + plannerTitle
				+ ", plannerCreateDT=" + plannerCreateDT + ", plannerStartDT=" + plannerStartDT + ", plannerDeleteYN="
				+ plannerDeleteYN + ", plannerCount=" + plannerCount + ", plannerUrl=" + plannerUrl + ", groupName="
				+ groupName + ", largeAreaName=" + largeAreaName + ", smallAreaName=" + smallAreaName + "]";
	}

	
	

}
