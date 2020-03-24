package com.kh.ourtrip.planner.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlannerDAOJYS {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/** 회원 수정중인 플래너 수 조회용DAO
	 * @param memberNo
	 * @return updatePlannerCount
	 * @throws Exception
	 */
	public int updatePlannerCount(int memberNo) throws Exception{
		return sqlSession.selectOne("plannerCardMapper.updatePlannerCount", memberNo);
	}

}
