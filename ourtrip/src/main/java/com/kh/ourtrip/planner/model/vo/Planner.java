package com.kh.ourtrip.planner.model.vo;

import java.sql.Date;
import java.util.List;

public class Planner {
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
	private List<Day> days;
	private String largeAreaCode;
	private String smallAreaCode;
	
	public Planner() {
	}
	
	public Planner(int plannerNo, String plannerTitle, String plannerPwd, int plannerCost, Date plannerCreateDT,
			Date plannerModifyDT, Date plannerStartDT, String plannerPublicYN, String plannerDeleteYN,
			String plannerExpiry, int plannerCount, String plannerUrl, int groupCode) {
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
	}
	

	public Planner(int plannerNo, String plannerTitle, String plannerPwd, int plannerCost, Date plannerCreateDT,
			Date plannerModifyDT, Date plannerStartDT, String plannerPublicYN, String plannerDeleteYN,
			String plannerExpiry, int plannerCount, String plannerUrl, int groupCode, List<Day> days) {
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
		this.days = days;
	}

	
	
	public Planner(int plannerNo, String plannerTitle, String plannerPwd, int plannerCost, Date plannerCreateDT,
			Date plannerModifyDT, Date plannerStartDT, String plannerPublicYN, String plannerDeleteYN,
			String plannerExpiry, int plannerCount, String plannerUrl, int groupCode, List<Day> days,
			String largeAreaCode, String smallAreaCode) {
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
		this.days = days;
		this.largeAreaCode = largeAreaCode;
		this.smallAreaCode = smallAreaCode;
	}
	
	public Planner(int plannerNo, String plannerTitle, int plannerCost, Date plannerStartDT, String plannerExpiry,
			String plannerUrl, int groupCode) {
		super();
		this.plannerNo = plannerNo;
		this.plannerTitle = plannerTitle;
		this.plannerCost = plannerCost;
		this.plannerStartDT = plannerStartDT;
		this.plannerExpiry = plannerExpiry;
		this.plannerUrl = plannerUrl;
		this.groupCode = groupCode;
	}
	

	public Planner(int plannerNo, String plannerTitle, int plannerCost, Date plannerStartDT, String plannerPublicYN,
			String plannerExpiry, String plannerUrl, int groupCode) {
		super();
		this.plannerNo = plannerNo;
		this.plannerTitle = plannerTitle;
		this.plannerCost = plannerCost;
		this.plannerStartDT = plannerStartDT;
		this.plannerPublicYN = plannerPublicYN;
		this.plannerExpiry = plannerExpiry;
		this.plannerUrl = plannerUrl;
		this.groupCode = groupCode;
	}
	
	public Planner(int plannerNo, String plannerTitle, int plannerCost, String plannerPublicYN, String plannerExpiry,
			String plannerUrl, int groupCode) {
		super();
		this.plannerNo = plannerNo;
		this.plannerTitle = plannerTitle;
		this.plannerCost = plannerCost;
		this.plannerPublicYN = plannerPublicYN;
		this.plannerExpiry = plannerExpiry;
		this.plannerUrl = plannerUrl;
		this.groupCode = groupCode;
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

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

	public String getLargeAreaCode() {
		return largeAreaCode;
	}

	public void setLargeAreaCode(String largeAreaCode) {
		this.largeAreaCode = largeAreaCode;
	}

	public String getSmallAreaCode() {
		return smallAreaCode;
	}

	public void setSmallAreaCode(String smallAreaCode) {
		this.smallAreaCode = smallAreaCode;
	}

	private String listToString() {
		String str = "";
		for(Day sc : days) {
			str += sc.toJsonString()+",";
		}
		str = str.substring(0, str.length()-1);
		return str;
	}
	
	public String toJsonString() {
		return "{\"plannerNo\":\"" + plannerNo + "\",\"plannerTitle\":\"" + plannerTitle + "\",\"plannerPwd\":\"" + plannerPwd
				+ "\",\"plannerCost\":\"" + plannerCost + "\",\"plannerCreateDT\":\"" + plannerCreateDT + "\",\"plannerModifyDT\":\"" + plannerModifyDT
				+ "\",\"plannerStartDT\":\"" + plannerStartDT + "\",\"plannerPublicYN\":\"" + plannerPublicYN + "\",\"plannerDeleteYN\":\""
				+ plannerDeleteYN + "\",\"plannerExpiry\":\"" + plannerExpiry + "\",\"plannerCount\":\"" + plannerCount
				+ "\",\"plannerUrl\":\"" + plannerUrl + "\",\"groupCode\":\"" + groupCode + "\",\"days\":[" + listToString() + "]}";
	}

	@Override
	public String toString() {
		return "Planner [plannerNo=" + plannerNo + ", plannerTitle=" + plannerTitle + ", plannerPwd=" + plannerPwd
				+ ", plannerCost=" + plannerCost + ", plannerCreateDT=" + plannerCreateDT + ", plannerModifyDT="
				+ plannerModifyDT + ", plannerStartDT=" + plannerStartDT + ", plannerPublicYN=" + plannerPublicYN
				+ ", plannerDeleteYN=" + plannerDeleteYN + ", plannerExpiry=" + plannerExpiry + ", plannerCount="
				+ plannerCount + ", plannerUrl=" + plannerUrl + ", groupCode=" + groupCode + ", days=" + days
				+ ", largeAreaCode=" + largeAreaCode + ", smallAreaCode=" + smallAreaCode + "]";
	}
}
