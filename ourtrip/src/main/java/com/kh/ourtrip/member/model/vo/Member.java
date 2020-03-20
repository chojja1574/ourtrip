package com.kh.ourtrip.member.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo;
	private String memberEmail;
	private String memberPwd;
	private String memberNickName;
	private String memberGrade;
	private Date memberEnrollDt;
	private String memberStatus;
	private String signUpRoute;
	
	public Member() {
		
	}

	public Member(int memberNo, String memberNickName) {
		super();
		this.memberNo = memberNo;
		this.memberNickName = memberNickName;
	}

	public Member(int memberNo, String memberEmail, String memberPwd, String memberNickName, String memberGrade,
			Date memberEnrollDt, String memberStatus, String signUpRoute) {
		super();
		this.memberNo = memberNo;
		this.memberEmail = memberEmail;
		this.memberPwd = memberPwd;
		this.memberNickName = memberNickName;
		this.memberGrade = memberGrade;
		this.memberEnrollDt = memberEnrollDt;
		this.memberStatus = memberStatus;
		this.signUpRoute = signUpRoute;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public Date getMemberEnrollDt() {
		return memberEnrollDt;
	}

	public void setMemberEnrollDt(Date memberEnrollDt) {
		this.memberEnrollDt = memberEnrollDt;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getSignUpRoute() {
		return signUpRoute;
	}

	public void setSignUpRoute(String signUpRoute) {
		this.signUpRoute = signUpRoute;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberEmail=" + memberEmail + ", memberPwd=" + memberPwd
				+ ", memberNickName=" + memberNickName + ", memberGrade=" + memberGrade + ", memberEnrollDt="
				+ memberEnrollDt + ", memberStatus=" + memberStatus + ", signUpRoute=" + signUpRoute + "]";
	}
	
	
}
