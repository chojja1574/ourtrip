package com.kh.ourtrip.common.visitLog.model.DAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.common.visitLog.model.vo.VisitCount;

@Repository
public class VisitCountDAO {
	
	@Autowired
	public SqlSessionTemplate sqlSession;
	
	public int insertVisitor(VisitCount vc) throws Exception{
		return sqlSession.insert("visitCounterMapper.insertVisitor", vc);
	}
}
