package com.kh.ourtrip.planner.model.vo;

import java.sql.Date;
import java.util.List;

public class PlannerInfo {
	
	private int memberNo;
	private String memberNickName;
	private String memberEmail;
	private int plannerNo;
	private String plannerTitle;
	private Date plannerCreateDT;
	private Date plannerStartDT;
	private String plannerPublicYN;
	private String plannerDeleteYN;
	private int plannerCount;
	private String plannerUrl;
	private String groupName;
	private int groupCode;
	private List<Day> days;
	private List<AreaName> areaNames;
	private Date plannerEndDate;
	private int tripDate;
	private int largeAreaCode;
	private int smallAreaCode;
	
	
	public PlannerInfo() {
		// TODO Auto-generated constructor stub
	}


	public PlannerInfo(int memberNo, String memberNickName, String memberEmail, int plannerNo, String plannerTitle,
			Date plannerCreateDT, Date plannerStartDT, String plannerPublicYN, String plannerDeleteYN, int plannerCount,
			String plannerUrl, String groupName, int groupCode, List<Day> days, List<AreaName> areaNames,
			Date plannerEndDate, int tripDate, int largeAreaCode, int smallAreaCode) {
		super();
		this.memberNo = memberNo;
		this.memberNickName = memberNickName;
		this.memberEmail = memberEmail;
		this.plannerNo = plannerNo;
		this.plannerTitle = plannerTitle;
		this.plannerCreateDT = plannerCreateDT;
		this.plannerStartDT = plannerStartDT;
		this.plannerPublicYN = plannerPublicYN;
		this.plannerDeleteYN = plannerDeleteYN;
		this.plannerCount = plannerCount;
		this.plannerUrl = plannerUrl;
		this.groupName = groupName;
		this.groupCode = groupCode;
		this.days = days;
		this.areaNames = areaNames;
		this.plannerEndDate = plannerEndDate;
		this.tripDate = tripDate;
		this.largeAreaCode = largeAreaCode;
		this.smallAreaCode = smallAreaCode;
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


	public String getMemberEmail() {
		return memberEmail;
	}


	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
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


	public int getGroupCode() {
		return groupCode;
	}


	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}


	public List<Day> getDays() {
		return days;
	}


	public void setDays(List<Day> days) {
		this.days = days;
	}


	public List<AreaName> getAreaNames() {
		return areaNames;
	}


	public void setAreaNames(List<AreaName> areaNames) {
		this.areaNames = areaNames;
	}


	public Date getPlannerEndDate() {
		return plannerEndDate;
	}


	public void setPlannerEndDate(Date plannerEndDate) {
		this.plannerEndDate = plannerEndDate;
	}


	public int getTripDate() {
		return tripDate;
	}


	public void setTripDate(int tripDate) {
		this.tripDate = tripDate;
	}


	public int getLargeAreaCode() {
		return largeAreaCode;
	}


	public void setLargeAreaCode(int largeAreaCode) {
		this.largeAreaCode = largeAreaCode;
	}


	public int getSmallAreaCode() {
		return smallAreaCode;
	}


	public void setSmallAreaCode(int smallAreaCode) {
		this.smallAreaCode = smallAreaCode;
	}


	@Override
	public String toString() {
		return "PlannerInfo [memberNo=" + memberNo + ", memberNickName=" + memberNickName + ", memberEmail="
				+ memberEmail + ", plannerNo=" + plannerNo + ", plannerTitle=" + plannerTitle + ", plannerCreateDT="
				+ plannerCreateDT + ", plannerStartDT=" + plannerStartDT + ", plannerPublicYN=" + plannerPublicYN
				+ ", plannerDeleteYN=" + plannerDeleteYN + ", plannerCount=" + plannerCount + ", plannerUrl="
				+ plannerUrl + ", groupName=" + groupName + ", groupCode=" + groupCode + ", days=" + days
				+ ", areaNames=" + areaNames + ", plannerEndDate=" + plannerEndDate + ", tripDate=" + tripDate
				+ ", largeAreaCode=" + largeAreaCode + ", smallAreaCode=" + smallAreaCode + "]";
	}
	
}
