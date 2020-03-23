package com.kh.ourtrip.planner.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.PlannerCard;

@Repository
public class PlannerDAOSDS {
	
	@Autowired
	public SqlSessionTemplate sqlSession;
	
	public List<PlannerCard> selectRecommendPCList() throws Exception{
		return sqlSession.selectList("plannerCardMapper.selectRecommendPCList");
	}

	public List<AreaName> selectAreaNames(List<Integer> rListNo) throws Exception {
		return sqlSession.selectList("plannerCardMapper.selectAreaNames", rListNo);
	}

}
