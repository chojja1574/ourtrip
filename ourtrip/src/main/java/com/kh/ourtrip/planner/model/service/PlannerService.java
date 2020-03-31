package com.kh.ourtrip.planner.model.service;

import java.util.List;

import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.SmallArea;

public interface PlannerService {

	
	
	/**플레너 생성용 service
	 * @param planner
	 * @param multiArea 
	 * @return result
	 * @throws Exception
	 */
	public abstract int createPlanner(Planner planner, Member member, String multiArea)throws Exception;

	/** 전체 대지역
	 * @return List
	 * @throws Exception
	 */
	public abstract List<LargeArea> selectLargeNmList() throws Exception;

	/** 전체 소지역
	 * @return List
	 * @throws Exception
	 */
	public abstract List<SmallArea> selectsmallNmList() throws Exception;

}
