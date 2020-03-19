package com.kh.ourtrip.planner.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.planner.model.vo.PlannerView;

@Repository
public class PlannerDAO2 {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<PlannerView> selectPlannerView(int no) throws Exception {
		return sqlSessionTemplate.selectList("plannerMapper1.selectPlannerView");
	}
	
}
