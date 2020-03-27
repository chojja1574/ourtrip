package com.kh.ourtrip.member.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.member.model.vo.ProfileImage;

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

	/** 이메일 확인용 Service
	 * @param email
	 * @return result
	 */
	public abstract int emailCertify(String email) throws Exception;

	/** 회원가입용 Service
	 * @param member
	 * @return memberNo
	 * @throws Exception
	 */
	public abstract int signUp(Member member) throws Exception;

	/** 회원 프로필 사진 등록용 Service
	 * @param pi
	 * @return result
	 * @throws Exception
	 */
	public abstract int insertProfileImage(ProfileImage pi) throws Exception;

	/** 회원 프로필 사진 조회용 Service
	 * @param memberNo
	 * @return profileImage
	 * @throws Exception
	 */
	public abstract ProfileImage selectProfileImage(int memberNo) throws Exception;

	/** 회원 닉네임 수정용 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public abstract int updateNickName(Member member) throws Exception;

	/** 회원 프로필 사진 삭제용 Service
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public abstract int deleteProfileImage(int memberNo) throws Exception;

	/** 회원 프로필 사진 수정용 Service
	 * @param memberNo
	 * @param profileImage
	 * @param savePath
	 * @param isDefault
	 * @return result
	 * @throws Exception
	 */
	public abstract int updateProfileImage(int memberNo, MultipartFile profileImage, String savePath, String isDefault) throws Exception;

	/** 회원 비밀번호 변경용 Service
	 * @param member
	 * @param changePwd
	 * @return result
	 * @throws Exception
	 */
	public abstract int changePwd(Member member, String changePwd) throws Exception;

	/** 회원탈퇴용 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public abstract int secession(Member member) throws Exception;

	/** 회원가입된 이메일인지 확인용 Service
	 * @param email
	 * @return result
	 * @throws Exception
	 */
	public abstract int signUpedEmail(String email) throws Exception;

	/** 회원 비밀번호 찾기용 Service
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public abstract int findPwd(String memberEmail) throws Exception;

	/** 프로필 사진 경로 조회용 Service
	 * @param memberNo
	 * @return profileImagePath
	 * @throws Exception
	 */
	public abstract String getProfileImagePath(int memberNo) throws Exception;

	/** 네이버 로그인용 Service
	 * @param member
	 * @param imagePath
	 * @return loginMember
	 * @throws Exception
	 */
	public abstract Member naverLogin(Member member, String imagePath) throws Exception;


}
