package com.kh.ourtrip.planner.model.vo;

import java.util.List;

public class Day {
	private int dateNo;
	private int tripDate;
	private int plannerNo;
	private List<Schedule> schedules;
	
	public Day() {
	}
	
	public Day(int dateNo, int tripDate, int plannerNo) {
		super();
		this.dateNo = dateNo;
		this.tripDate = tripDate;
		this.plannerNo = plannerNo;
	}
	

	public Day(int dateNo, int tripDate, int plannerNo, List<Schedule> schedules) {
		super();
		this.dateNo = dateNo;
		this.tripDate = tripDate;
		this.plannerNo = plannerNo;
		this.schedules = schedules;
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

	public int getPlannerNo() {
		return plannerNo;
	}

	public void setPlannerNo(int plannerNo) {
		this.plannerNo = plannerNo;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	private String listToString() {
		String str = "";
		for(Schedule sc : schedules) {
			str += sc.toJsonString()+",";
		}
		str = str.substring(0, str.length()-1);
		return str;
	}
	
	public String toJsonString() {
		return "{\"dateNo\":\"" + dateNo + "\",\"tripDate\":\"" + tripDate + "\",\"plannerNo\":\"" + plannerNo + "\",\"schedules\":["
				+ listToString() + "]}";
	}

	@Override
	public String toString() {
		return "Day [dateNo=" + dateNo + ", tripDate=" + tripDate + ", plannerNo=" + plannerNo + ", schedules="
				+ schedules + "]";
	}
	
}