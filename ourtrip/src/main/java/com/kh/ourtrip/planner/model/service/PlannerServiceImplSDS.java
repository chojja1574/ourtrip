package com.kh.ourtrip.planner.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ourtrip.planner.model.dao.PlannerDAOSDS;
import com.kh.ourtrip.planner.model.vo.PlannerCard;

@Service
public class PlannerServiceImplSDS implements PlannerServiceSDS{

	@Autowired
	public PlannerDAOSDS plannerDAOSDS;
	
	@Override
	public List<PlannerCard> selectRecommendPCList() throws Exception {
		List<PlannerCard> recommendPCList = plannerDAOSDS.selectRecommendPCList();
//		List<Integer> rListNo = null;
//		if(!recommendPCList.isEmpty()) {
//			for(PlannerCard card : recommendPCList) {
//				rListNo.card.getPlannerNo()
//			}
//		}
		return recommendPCList;
	}

}
