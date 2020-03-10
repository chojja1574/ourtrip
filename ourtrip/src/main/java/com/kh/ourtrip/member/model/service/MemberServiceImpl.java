package com.kh.ourtrip.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ourtrip.member.model.dao.MemberDAO;
import com.kh.ourtrip.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	// 암호화를 위한 객체 DI(의존성 주입)
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// 메일 전송을 위한 객체 DI
//	@Autowired
//	private JavaMailSender mailSender;
	
	/** 회원 로그인용 Service
	 * @author yousang
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	@Override
	public Member login(Member member) throws Exception {
		Member loginMember = memberDAO.login(member);
		
		return loginMember;
	}

}
