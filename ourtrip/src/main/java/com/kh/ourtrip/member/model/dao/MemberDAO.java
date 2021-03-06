package com.kh.ourtrip.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.member.model.vo.ProfileImage;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/** 회원 로그인용 DAO
	 * @author yousang
	 * @param member
	 * @return login
	 * @throws Exception
	 */
	public Member login(Member member) throws Exception{
		return sqlSession.selectOne("memberMapper.login", member);
	}

	/** 회원인지 확인용 DAO
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int isMember(Member member) throws Exception{
		return sqlSession.selectOne("memberMapper.isMember", member);
	}

	/** 회원가입용 DAO
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception{
		return sqlSession.insert("memberMapper.signUp", member);
	}

	/** 회원 이미지 등록용 DAO
	 * @param profileImage
	 * @return result
	 * @throws Exception
	 */
	public int insertProfileImage(ProfileImage profileImage) throws Exception{
		return sqlSession.insert("memberMapper.insertProfileImage", profileImage);
	}

	/** 소셜 로그인용 DAO
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member socialLogin(Member member) throws Exception{
		return sqlSession.selectOne("memberMapper.socialLogin", member);
	}

	/** 메일 확인용 DAO
	 * @param email
	 * @return result
	 */
	public int emailCertify(String email) {
		return sqlSession.selectOne("memberMapper.emailCertify", email);
	}

	/** 회원번호 조회용 DAO
	 * @param member
	 * @return memberNo
	 * @throws Exception
	 */
	public int selectMemberNo(Member member) throws Exception{
		return sqlSession.selectOne("memberMapper.selectMemberNo", member);
	}

	/** 회원 프로필 사진 조회용 DAO
	 * @param memberNo
	 * @return profileIamge
	 * @throws Exception
	 */
	public ProfileImage selectProfileImage(int memberNo) throws Exception{
		return sqlSession.selectOne("memberMapper.selectProfileImage", memberNo);
	}

	/** 회원 닉네임 수정용 DAO
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateNickName(Member member) throws Exception{
		return sqlSession.update("memberMapper.updateNickName", member);
	}

	/** 회원 프로필 사진 삭제용 DAO
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteProfileImage(int memberNo) throws Exception{
		return sqlSession.delete("memberMapper.deleteProfileImage", memberNo);
	}

	/** 회원 프로필 사진 수정용 DAO
	 * @param pi
	 * @return result
	 * @throws Exception
	 */
	public int updateProfileImage(ProfileImage pi) throws Exception{
		return sqlSession.update("memberMapper.updateProfileImage", pi);
	}

	/** 회원 비밀번호 조회용 DAO
	 * @param memberNo
	 * @return memberPwd
	 * @throws Exception
	 */
	public String selectMemberPwd(int memberNo) throws Exception{
		return sqlSession.selectOne("memberMapper.selectMemberPwd", memberNo);
	}

	/** 회원 비밀번호 변경용 DAO
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updatePwd(Member member) throws Exception{
		return sqlSession.update("memberMapper.updatePwd", member);
	}

	/** 회원탈퇴용 DAO
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int secession(Member member) throws Exception{
		return sqlSession.update("memberMapper.secession", member);
	}

	/** 회원가입된 이메일인지 확인용 DAO
	 * @param email
	 * @return result
	 */
	public int signUpedEmail(String email) {
		return sqlSession.selectOne("memberMapper.signUpedEmail", email);
	}

	/** 기존 회원 이메일로 접속용 DAO
	 * @param memberEmail
	 * @return member
	 * @throws Exception
	 */
	public Member selectMember(String memberEmail) throws Exception{
		return sqlSession.selectOne("memberMapper.selectMember", memberEmail);
	}

	public String getProfileImagePath(int memberNo) {
		return sqlSession.selectOne("memberMapper.getProfileImagePath", memberNo);
	}


}
