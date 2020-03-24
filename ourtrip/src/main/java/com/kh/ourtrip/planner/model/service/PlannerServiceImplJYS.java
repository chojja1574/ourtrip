package com.kh.ourtrip.planner.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ourtrip.planner.model.dao.PlannerDAOJYS;

@Service
public class PlannerServiceImplJYS implements PlannerServiceJYS{

	@Autowired
	private PlannerDAOJYS plannerDAO;

	/** 회원 수정중인 플래너 수 조회용 Service
	 * @param memberNo
	 * @return updatePlannerCount
	 * @throws Exception
	 */
	@Override
	public int updatePlannerCount(int memberNo) throws Exception {
		return plannerDAO.updatePlannerCount(memberNo);
	}
	
}
