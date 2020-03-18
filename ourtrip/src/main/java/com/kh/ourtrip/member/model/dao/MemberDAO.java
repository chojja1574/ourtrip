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

	/** 카카오 로그인용 DAO
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member kakaoLogin(Member member) throws Exception{
		return sqlSession.selectOne("memberMapper.kakaoLogin", member);
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


}
