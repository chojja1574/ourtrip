package com.kh.ourtrip.member.model.service;

import com.kh.ourtrip.member.model.vo.Member;

public interface MemberService {

	/** 회원 로그인용 Service
	 * @author yousang
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public abstract Member login(Member member) throws Exception;

	/** 카카오 로그인용 Service
	 * @param member
	 * @param imagePath
	 * @return loginMember
	 * @throws Exception
	 */
	public abstract Member kakaoLogin(Member member, String imagePath) throws Exception;

}
