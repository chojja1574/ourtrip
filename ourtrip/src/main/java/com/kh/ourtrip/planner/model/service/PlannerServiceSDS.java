package com.kh.ourtrip.planner.model.service;

import java.util.List;
import java.util.Map;

import com.kh.ourtrip.planner.model.vo.PlannerCard;

public interface PlannerServiceSDS {

	/** 추천플래너카드 조회
	 * @return recommendPCList
	 * @throws Exception
	 */
	public abstract List<PlannerCard> selectRecommendPCList() throws Exception;

	/** 전체 플래너 수 조회
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	public abstract int getListCount(Map<String, Object> map) throws Exception;

}
