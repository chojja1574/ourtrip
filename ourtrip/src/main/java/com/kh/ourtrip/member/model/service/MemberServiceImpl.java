package com.kh.ourtrip.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ourtrip.member.model.dao.MemberDAO;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.member.model.vo.ProfileImage;

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

	/** 카카오 로그인용 Service
	 * @param member
	 * @param imagePath
	 * @return loginMember
	 * @throws Exception
	 */
	@Override
	public Member kakaoLogin(Member member, String imagePath) throws Exception {
		// 1) ourtrip DB에 회원으로 등록되어있는지 확인
		int result = memberDAO.isMember(member);
				
		// 2) 안되있을 시 회원가입
		if(result == 0) {
			result = memberDAO.signUp(member);
			result = memberDAO.insertProfileImage(new ProfileImage(imagePath, member.getMemberNo()));
		}
		
		// 3) 회원 객체 반환
		return memberDAO.kakaoLogin(member);
	}
	
	

}
