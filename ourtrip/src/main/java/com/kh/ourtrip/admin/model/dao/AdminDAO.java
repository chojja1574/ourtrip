package com.kh.ourtrip.admin.model.dao;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AdminDAO {

	@Autowired
	public SqlSessionTemplate sqlSession;
	
	/** 방문자 날짜 조회용 DAO
	 * @return vList
	 * @throws Exception
	 */
	public List<Date> getVisitDateList() throws Exception{
		return sqlSession.selectList("adminMapper.getVisitDateList");
	}

	/** 플래너 생성 날짜 조회용 DAO
	 * @return pList
	 * @throws Exception
	 */
	public List<Date> getPlannerDateList() throws Exception{
		return sqlSession.selectList("adminMapper.getPlannerDateList");
	}

}
