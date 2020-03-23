package com.kh.ourtrip.planner.model.service;

public interface PlannerServiceJYS {

	/** 회원 수정중인 플래너 수 조회용 Service
	 * @param memberNo
	 * @return updatePlannerCount
	 * @throws Exception
	 */
	public abstract int updatePlannerCount(int memberNo) throws Exception;

}
