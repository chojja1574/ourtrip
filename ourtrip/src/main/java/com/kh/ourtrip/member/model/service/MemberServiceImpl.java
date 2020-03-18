package com.kh.ourtrip.member.model.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ourtrip.member.model.dao.MemberDAO;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.member.model.vo.ProfileImage;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	// 암호화를 위한 객체 DI(의존성 주입)
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// 메일 전송을 위한 객체 DI
	@Autowired
	private JavaMailSender mailSender;
	
	/** 회원 로그인용 Service
	 * @author yousang
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	@Override
	public Member login(Member member) throws Exception {
		Member loginMember = memberDAO.login(member);
		
		if(loginMember != null &&
			!bCryptPasswordEncoder.matches(member.getMemberPwd(), loginMember.getMemberPwd())) {
			
			loginMember = null;
		}
		
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

	/** 이메일 확인용 Service
	 * @param email
	 * @return result
	 */
	@Override
	public int emailCertify(String email) throws Exception{
		int result = memberDAO.emailCertify(email);

		if (result > 0) { // 이메일이 이미 존재할 경우
			result = 0;
		} else { // 존재하지 않을 경우
			result = (int) (Math.random() * 999999) + 1;

			String setfrom = "jysrmb@gmail.com";

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(email); // 받는사람 이메일
			messageHelper.setSubject("OurTrip 인증번호"); // 메일제목은 생략이 가능하다
			messageHelper.setText(Integer.toString(result)); // 메일 내용

			mailSender.send(message);
		}

		return result;
	}

	/** 회원가입용 Service
	 * @param member
	 * @return memberNo
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int signUp(Member member) throws Exception {
		member.setMemberPwd(bCryptPasswordEncoder.encode(member.getMemberPwd()));
		
		int result = memberDAO.signUp(member);
		
		if(result > 0) {
			result = memberDAO.selectMemberNo(member);
		}
		
		return result;
	}
	
	
	

}
