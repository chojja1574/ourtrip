package com.kh.ourtrip.planner.model.vo;

public class LargeArea {
	private int largeAreaCode;
	private String largeAreaName;
	
	public LargeArea() {
		// TODO Auto-generated constructor stub
	}

	public LargeArea(int largeAreaCode, String largeAreaName) {
		super();
		this.largeAreaCode = largeAreaCode;
		this.largeAreaName = largeAreaName;
	}

	public int getLargeAreaCode() {
		return largeAreaCode;
	}

	public void setLargeAreaCode(int largeAreaCode) {
		this.largeAreaCode = largeAreaCode;
	}

	public String getLargeAreaName() {
		return largeAreaName;
	}

	public void setLargeAreaName(String largeAreaName) {
		this.largeAreaName = largeAreaName;
	}

	@Override
	public String toString() {
		return "LargeArea [largeAreaCode=" + largeAreaCode + ", largeAreaName=" + largeAreaName + "]";
	}
	
}
