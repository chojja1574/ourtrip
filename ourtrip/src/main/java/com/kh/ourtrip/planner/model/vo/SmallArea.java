package com.kh.ourtrip.planner.model.vo;

public class SmallArea {
	private int smallAreaCode;
	private int largeAreaCode;
	private String smallAreaName;
	
	public SmallArea() {
		// TODO Auto-generated constructor stub
	}

	public SmallArea(int smallAreaCode, int largeAreaCode, String smallAreaName) {
		super();
		this.smallAreaCode = smallAreaCode;
		this.largeAreaCode = largeAreaCode;
		this.smallAreaName = smallAreaName;
	}

	public int getSmallAreaCode() {
		return smallAreaCode;
	}

	public void setSmallAreaCode(int smallAreaCode) {
		this.smallAreaCode = smallAreaCode;
	}

	public int getLargeAreaCode() {
		return largeAreaCode;
	}

	public void setLargeAreaCode(int largeAreaCode) {
		this.largeAreaCode = largeAreaCode;
	}

	public String getSmallAreaName() {
		return smallAreaName;
	}

	public void setSmallAreaName(String smallAreaName) {
		this.smallAreaName = smallAreaName;
	}

	@Override
	public String toString() {
		return "SmallArea [smallAreaCode=" + smallAreaCode + ", largeAreaCode=" + largeAreaCode + ", smallAreaName="
				+ smallAreaName + "]";
	}
	
	
}
