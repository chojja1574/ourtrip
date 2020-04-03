package com.kh.ourtrip.planner.model.vo;

public class Schedule {
	
	private int scheduleNo;
	private String scheduleTitle;
	private int scheduleCost;
	private String scheduleTime;
	private String scheduleMemo;
	private String scheduleLocationNM;
	private double scheduleLat;
	private double scheduleLng;
	//private String infoWindow;
	private int dateNo;

	
	public Schedule() {
	}

	public Schedule(int scheduleNo, String scheduleTitle, int scheduleCost, String scheduleTime, String scheduleMemo,
			String scheduleLocationNM, double scheduleLat, double scheduleLng, int dateNo) {
		super();
		this.scheduleNo = scheduleNo;
		this.scheduleTitle = scheduleTitle;
		this.scheduleCost = scheduleCost;
		this.scheduleTime = scheduleTime;
		this.scheduleMemo = scheduleMemo;
		this.scheduleLocationNM = scheduleLocationNM;
		this.scheduleLat = scheduleLat;
		this.scheduleLng = scheduleLng;
		this.dateNo = dateNo;
	}

	public int getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public String getScheduleTitle() {
		return scheduleTitle;
	}

	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}

	public int getScheduleCost() {
		return scheduleCost;
	}

	public void setScheduleCost(int scheduleCost) {
		this.scheduleCost = scheduleCost;
	}

	public String getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public String getScheduleMemo() {
		return scheduleMemo;
	}

	public void setScheduleMemo(String scheduleMemo) {
		this.scheduleMemo = scheduleMemo;
	}

	public String getScheduleLocationNM() {
		return scheduleLocationNM;
	}

	public void setScheduleLocationNM(String scheduleLocationNM) {
		this.scheduleLocationNM = scheduleLocationNM;
	}

	public double getScheduleLat() {
		return scheduleLat;
	}

	public void setScheduleLat(double scheduleLat) {
		this.scheduleLat = scheduleLat;
	}

	public double getScheduleLng() {
		return scheduleLng;
	}

	public void setScheduleLng(double scheduleLng) {
		this.scheduleLng = scheduleLng;
	}

//	public String getInfoWindow() {
//		return infoWindow;
//	}
//
//	public void setInfoWindow(String infoWindow) {
//		this.infoWindow = infoWindow;
//	}

	public int getDateNo() {
		return dateNo;
	}

	public void setDateNo(int dateNo) {
		this.dateNo = dateNo;
	}

	public String toJsonString() {
		String str = "";
		if(scheduleMemo != null) {
			str = scheduleMemo.replaceAll("\n","\\\\\\\\r\\\\\\\\n");
		}
		return "{\"scheduleNo\":\"" + scheduleNo + "\",\"scheduleTitle\":\"" + scheduleTitle + "\",\"scheduleCost\":\""
				+ scheduleCost + "\",\"scheduleTime\":\"" + scheduleTime + "\",\"scheduleMemo\":\"" + str
				+ "\",\"scheduleLocationNM\":\"" + scheduleLocationNM + "\",\"scheduleLat\":\"" + scheduleLat + "\",\"scheduleLng\":\""
				+ scheduleLng + "\",\"dateNo\":\"" + dateNo + "\"}";
	}

	@Override
	public String toString() {
		return "Schedule [scheduleNo=" + scheduleNo + ", scheduleTitle=" + scheduleTitle + ", scheduleCost="
				+ scheduleCost + ", scheduleTime=" + scheduleTime + ", scheduleMemo=" + scheduleMemo
				+ ", scheduleLocationNM=" + scheduleLocationNM + ", scheduleLat=" + scheduleLat + ", scheduleLng="
				+ scheduleLng + ", dateNo=" + dateNo + "]";
	}
	
}