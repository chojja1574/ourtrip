package com.kh.ourtrip.planner.model.vo;

import java.util.List;

public class PlannerJson {
	private int planner_no;
	private List<Day> days;
	
	public PlannerJson() {
		super();
	}

	public PlannerJson(int planner_no, List<Day> days) {
		super();
		this.planner_no = planner_no;
		this.days = days;
	}

	public int getPlanner_no() {
		return planner_no;
	}
	public void setPlanner_no(int planner_no) {
		this.planner_no = planner_no;
	}
	public List<Day> getDays() {
		return days;
	}
	public void setDays(List<Day> days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "PlannerJson [planner_no=" + planner_no + ", days=" + days + "]";
	}
	
	
	
}
