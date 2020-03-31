package com.kh.ourtrip.planner.model.service;

import java.util.List;

import org.json.simple.JSONArray;

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
	public abstract int createPlanner(Planner planner)throws Exception;

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

	/** 플래너 지역입력용 service
	 * @param jarr
	 * @param plannerNo 
	 * @return result
	 * @throws Exception
	 */
	public abstract int createLocation(JSONArray jarr, int plannerNo)throws Exception;

	/** 플래너_맴버 service
	 * @param plannerNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public abstract int createMember(int plannerNo, int memberNo)throws Exception;

}
