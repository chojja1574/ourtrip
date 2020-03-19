package com.kh.ourtrip.member.model.service;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.ourtrip.common.FileRename;
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
			
			if(result > 0) {
				result = memberDAO.selectMemberNo(member);
				result = memberDAO.insertProfileImage(new ProfileImage(imagePath, result));
			}
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

	/** 회원 프로필 사진 등록용 Service
	 * @param pi
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertProfileImage(ProfileImage pi) throws Exception {
		return memberDAO.insertProfileImage(pi);
	}

	/** 회원 프로필 사진 조회용 Service
	 * @param memberNo
	 * @return profileImage
	 * @throws Exception
	 */
	@Override
	public ProfileImage selectProfileImage(int memberNo) throws Exception {
		return memberDAO.selectProfileImage(memberNo);
	}

	/** 회원 닉네임 수정용 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateNickName(Member member) throws Exception {
		return memberDAO.updateNickName(member);
	}

	/** 회원 프로필 사진 삭제용 Service
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteProfileImage(int memberNo) throws Exception {
		int result = 0;
		ProfileImage pi = memberDAO.selectProfileImage(memberNo);
		
		// 회원의 프로필 사진이 존재하는 경우
		if(pi != null) {
			// 프로필 사진을 DB에서 삭제
			result = memberDAO.deleteProfileImage(memberNo);
			
			// DB에서 삭제 성공한 경우
			if(result > 0) {
				
			}
		}
		

		// 삭제 성공시 서버에서도 해당 이미지 삭제
		
		return result;
	}

	/** 회원 프로필 사진 수정용 Service
	 * @param memberNo
	 * @param profileImage
	 * @param savePath
	 * @param isDefault
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateProfileImage(int memberNo, MultipartFile profileImage, String savePath, String isDefault) throws Exception {
		int result = 0;
		ProfileImage pi = memberDAO.selectProfileImage(memberNo);
		
		String changeFileName = null;
		if(!profileImage.getOriginalFilename().equals("")) { // 이미지 변경시 rename
			changeFileName = FileRename.rename(profileImage.getOriginalFilename());
		}
		
		File deleteFile = null;
		
		// 회원의 기존 프로필 사진이 존재하는 경우
		if(pi != null) {
			
			// 사진을 변경했는데 디폴트 이미지로 변경했을 경우
			if(profileImage.getOriginalFilename().equals("") && isDefault.equals("true")) {
				// DB에서 이미지 삭제
				result = memberDAO.deleteProfileImage(memberNo);
				
				if(result > 0) { // 서버에서도 이미지 삭제
					deleteFile = new File(pi.getImagePath());
					deleteFile.delete();
					
				}else throw new Exception("이미지 삭제 중 오류 발생");
				
			// 사진을 변경한 경우
			}else if(!profileImage.getOriginalFilename().equals("")) {
				
				// 서버에 존재하는 기존 이미지를 삭제하기 위해 저장k
				String originFilePath = pi.getImagePath();
				
				// DB에 update하기 위해 경로 값을 변경
				pi.setImagePath(savePath + "/" + changeFileName);
				
				result = memberDAO.updateProfileImage(pi);
				
				// DB에서 수정 성공한 경우 서버에 새로운 이미지 저장
				if(result > 0) profileImage.transferTo(new File(pi.getImagePath()));
				else throw new Exception("이미지 수정 중 오류 발생");
				
				deleteFile = new File(originFilePath);
				deleteFile.delete();
			}
			
		// 회원의 기존 프로필 사진이 존재하지 않는 경우
		}else {
			
			// 사진을 변경한 경우
			if(!profileImage.getOriginalFilename().equals("")) {
				pi = new ProfileImage(savePath + "/" + changeFileName, memberNo);
				
				result = memberDAO.insertProfileImage(pi);
				
				if(result > 0) profileImage.transferTo(new File(pi.getImagePath()));
			}
		}

		return result;
	}
	
	


}
