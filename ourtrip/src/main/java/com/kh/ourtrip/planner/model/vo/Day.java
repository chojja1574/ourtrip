package com.kh.ourtrip.planner.model.vo;

import java.util.List;

public class Day {
	private int dateNo;
	private int date;
	private List<Schedule> schedules;
	private int plannerNo;
	
	public Day() {
		super();
	}
	
	public Day(int dateNo, int date, List<Schedule> schedules, int plannerNo) {
		super();
		this.dateNo = dateNo;
		this.date = date;
		this.schedules = schedules;
		this.plannerNo = plannerNo;
	}
	
	public int getDateNo() {
		return dateNo;
	}
	public void setDateNo(int dateNo) {
		this.dateNo = dateNo;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public List<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	public int getPlannerNo() {
		return plannerNo;
	}
	public void setPlannerNo(int plannerNo) {
		this.plannerNo = plannerNo;
	}
	
	@Override
	public String toString() {
		return "Day [dateNo=" + dateNo + ", date=" + date + ", schedules=" + schedules + ", plannerNo=" + plannerNo
				+ "]";
	}
}