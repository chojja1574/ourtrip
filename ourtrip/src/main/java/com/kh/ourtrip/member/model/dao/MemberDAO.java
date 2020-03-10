package com.kh.ourtrip.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.member.model.vo.Member;

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

}
