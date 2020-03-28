package com.kh.ourtrip.planner.model.vo;

public class PlannerMemberView {
	
	private int plannerNo;
	private int memberNo;
	private int plannerPermission;
	private String memberNickName;
	
	public PlannerMemberView() {
		// TODO Auto-generated constructor stub
	}

	public PlannerMemberView(int plannerNo, int memberNo, int plannerPermission, String memberNickName) {
		super();
		this.plannerNo = plannerNo;
		this.memberNo = memberNo;
		this.plannerPermission = plannerPermission;
		this.memberNickName = memberNickName;
	}

	public int getPlannerNo() {
		return plannerNo;
	}

	public void setPlannerNo(int plannerNo) {
		this.plannerNo = plannerNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getPlannerPermission() {
		return plannerPermission;
	}

	public void setPlannerPermission(int plannerPermission) {
		this.plannerPermission = plannerPermission;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	public String toJsonString() {
		String str = null;
		str = "{\"plannerNo\":\"" + plannerNo + "\",\"memberNo\":\"" + memberNo + 
				"\",\"plannerPermission\":\"" + plannerPermission + "\",\"memberNickName\":\"" + memberNickName + "\"}";
		return str;
	}
	
	@Override
	public String toString() {
		return "plannerMember [plannerNo=" + plannerNo + ", memberNo=" + memberNo + ", plannerPermission="
				+ plannerPermission + "]";
	}

}