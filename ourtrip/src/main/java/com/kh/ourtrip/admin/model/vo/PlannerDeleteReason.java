package com.kh.ourtrip.admin.model.vo;

public class PlannerDeleteReason {
	
	int deleteNo;
	int plannerNo;
	String deleteReason;
	
	public PlannerDeleteReason() {
		// TODO Auto-generated constructor stub
	}

	public PlannerDeleteReason(int deleteNo, int plannerNo, String deleteReason) {
		super();
		this.deleteNo = deleteNo;
		this.plannerNo = plannerNo;
		this.deleteReason = deleteReason;
	}

	public int getDeleteNo() {
		return deleteNo;
	}

	public void setDeleteNo(int deleteNo) {
		this.deleteNo = deleteNo;
	}

	public int getPlannerNo() {
		return plannerNo;
	}

	public void setPlannerNo(int plannerNo) {
		this.plannerNo = plannerNo;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	@Override
	public String toString() {
		return "PlannerDeleteReason [deleteNo=" + deleteNo + ", plannerNo=" + plannerNo + ", deleteReason="
				+ deleteReason + "]";
	}
	
	
	
	

}
