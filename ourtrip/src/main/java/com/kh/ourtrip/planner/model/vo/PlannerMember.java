package com.kh.ourtrip.planner.model.vo;

public class PlannerMember {
	
	private int plannerNo;
	private int memberNo;
	private int plannerPermission;
	
	public PlannerMember() {
		// TODO Auto-generated constructor stub
	}

	public PlannerMember(int plannerNo, int memberNo, int plannerPermission) {
		super();
		this.plannerNo = plannerNo;
		this.memberNo = memberNo;
		this.plannerPermission = plannerPermission;
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

	public String toJsonString() {
		String str = null;
		str = "{\"plannerNo\":\"" + plannerNo + "\",\"memberNo\":\"" + memberNo + "\",\"plannerPermission\":\"" + plannerPermission + "\"}";
		return str;
	}
	
	@Override
	public String toString() {
		return "plannerMember [plannerNo=" + plannerNo + ", memberNo=" + memberNo + ", plannerPermission="
				+ plannerPermission + "]";
	}

}
