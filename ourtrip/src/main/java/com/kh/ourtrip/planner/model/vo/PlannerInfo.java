package com.kh.ourtrip.planner.model.vo;

import java.sql.Date;
import java.util.List;

public class PlannerInfo {
	
	private int memberNo;
	private String memberNickName;
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
	private String groupName;
	private int groupCode;
	private List<Day> days;
	private List<AreaName> areaNames;
	private Date plannerEndDate;
	private Date tripDate;
	private int largeAreaCode;
	private int smallAreaCode;
	
	
	public PlannerInfo() {
		// TODO Auto-generated constructor stub
	}


	public PlannerInfo(int memberNo, String memberNickName, int plannerNo, String plannerTitle, String plannerPwd,
			int plannerCost, Date plannerCreateDT, Date plannerModifyDT, Date plannerStartDT, String plannerPublicYN,
			String plannerDeleteYN, String plannerExpiry, int plannerCount, String plannerUrl, String groupName,
			int groupCode, List<Day> days, List<AreaName> areaNames, Date plannerEndDate, Date tripDate,
			int largeAreaCode, int smallAreaCode) {
		super();
		this.memberNo = memberNo;
		this.memberNickName = memberNickName;
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


	public Date getPlannerEndDate() {
		return plannerEndDate;
	}


	public void setPlannerEndDate(Date plannerEndDate) {
		this.plannerEndDate = plannerEndDate;
	}


	public Date getTripDate() {
		return tripDate;
	}


	public void setTripDate(Date tripDate) {
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


	public List<AreaName> getAreaNames() {
		return areaNames;
	}


	public void setAreaNames(List<AreaName> areaNames) {
		this.areaNames = areaNames;
	}


	@Override
	public String toString() {
		return "PlannerInfo [memberNo=" + memberNo + ", memberNickName=" + memberNickName + ", plannerNo=" + plannerNo
				+ ", plannerTitle=" + plannerTitle + ", plannerPwd=" + plannerPwd + ", plannerCost=" + plannerCost
				+ ", plannerCreateDT=" + plannerCreateDT + ", plannerModifyDT=" + plannerModifyDT + ", plannerStartDT="
				+ plannerStartDT + ", plannerPublicYN=" + plannerPublicYN + ", plannerDeleteYN=" + plannerDeleteYN
				+ ", plannerExpiry=" + plannerExpiry + ", plannerCount=" + plannerCount + ", plannerUrl=" + plannerUrl
				+ ", groupName=" + groupName + ", groupCode=" + groupCode + ", days=" + days + ", areaNames="
				+ areaNames + ", plannerEndDate=" + plannerEndDate + ", tripDate=" + tripDate + ", largeAreaCode="
				+ largeAreaCode + ", smallAreaCode=" + smallAreaCode + "]";
	}


	
	
	

}
