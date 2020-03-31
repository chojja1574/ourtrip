package com.kh.ourtrip.common.visitLog.model.DAO;

import org.mybatis.spring.SqlSessionTemplate;

public class VisitCountDAO {
	
	/** 방문자수 저장용 DAO
	 * @param ip
	 * @param sqlSession
	 * @return result
	 * @throws Exception
	 */
	public int insertVisitor(String ip, SqlSessionTemplate sqlSession) throws Exception{
		return sqlSession.insert("visitCounterMapper.insertVisitor", ip);
	}
}
