package com.kh.ourtrip.member.model.vo;

import java.sql.Date;

public class ProfileImage {
	private int imageNo;
	private String imagePath;
	private Date imageUploadDate;
	private String imageStatus;
	private int memberNo;
	
	public ProfileImage() {
		// TODO Auto-generated constructor stub
	}
	
	public ProfileImage(String imagePath, int memberNo) {
		super();
		this.imagePath = imagePath;
		this.memberNo = memberNo;
	}

	public ProfileImage(int imageNo, String imagePath, Date imageUploadDate, String imageStatus, int memberNo) {
		super();
		this.imageNo = imageNo;
		this.imagePath = imagePath;
		this.imageUploadDate = imageUploadDate;
		this.imageStatus = imageStatus;
		this.memberNo = memberNo;
	}

	public int getImageNo() {
		return imageNo;
	}

	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Date getImageUploadDate() {
		return imageUploadDate;
	}

	public void setImageUploadDate(Date imageUploadDate) {
		this.imageUploadDate = imageUploadDate;
	}

	public String getImageStatus() {
		return imageStatus;
	}

	public void setImageStatus(String imageStatus) {
		this.imageStatus = imageStatus;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "ProfileImage [imageNo=" + imageNo + ", imagePath=" + imagePath + ", imageUploadDate=" + imageUploadDate
				+ ", imageStatus=" + imageStatus + ", memberNo=" + memberNo + "]";
	}
	
}
