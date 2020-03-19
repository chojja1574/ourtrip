package com.kh.ourtrip.planner.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ourtrip.planner.model.dao.PlannerDAO2;
import com.kh.ourtrip.planner.model.vo.PlannerView;

@Service
public class PlannerService2Imlp implements PlannerService2 {

	@Autowired
	PlannerDAO2 plannerDAO2;
	
	@Override
	public List<PlannerView> selectPlannerView(int no) throws Exception {
		return plannerDAO2.selectPlannerView(no);
	}

}
