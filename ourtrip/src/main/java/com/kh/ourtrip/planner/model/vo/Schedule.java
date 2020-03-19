package com.kh.ourtrip.planner.model.vo;

public class Schedule {
	
	private int scheduleNo;
	private String title;
	private int cost;
	private String memo;
	private String time;
	private String location;
	private double lat;
	private double lng;
	private int dateNo;
	
	public Schedule() {
		super();
	}

	public Schedule(int scheduleNo, String title, int cost, String memo, String time, String location, double lat,
			double lng, int dateNo) {
		super();
		this.scheduleNo = scheduleNo;
		this.title = title;
		this.cost = cost;
		this.memo = memo;
		this.time = time;
		this.location = location;
		this.lat = lat;
		this.lng = lng;
		this.dateNo = dateNo;
	}

	public int getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public int getDateNo() {
		return dateNo;
	}
	public void setDateNo(int dateNo) {
		this.dateNo = dateNo;
	}
	
	@Override
	public String toString() {
		return "Schedule [scheduleNo=" + scheduleNo + ", title=" + title + ", cost=" + cost + ", memo=" + memo
				+ ", time=" + time + ", location=" + location + ", lat=" + lat + ", lng=" + lng + ", dateNo=" + dateNo
				+ "]";
	}
}