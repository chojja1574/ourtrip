package com.kh.ourtrip.planner.model.service;

import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.vo.Planner;

public interface PlannerService {

	
	
	/**플레너 생성용 service
	 * @param planner
	 * @return result
	 * @throws Exception
	 */
	public abstract int createPlanner(Planner planner, Member member)throws Exception;

}
