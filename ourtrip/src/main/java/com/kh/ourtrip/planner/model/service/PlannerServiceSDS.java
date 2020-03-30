package com.kh.ourtrip.planner.model.service;

import java.util.List;
import java.util.Map;

import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.SmallArea;

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

	/** 플래너 조회 ( + 검색)
	 * @param map
	 * @param pInf
	 * @return pList
	 * @throws Exception
	 */
	public abstract List<PlannerCard> selectPList(Map<String, Object> map, PageInfo pInf) throws Exception;

	/** 대지역 목록 조회용 Service
	 * @return largeNmList
	 * @throws Exception
	 */
	public abstract List<LargeArea> selectLargeNmList() throws Exception;

	/** 소지역 목록 조회용 Service
	 * @return smallNmList
	 * @throws Exception
	 */
	public abstract List<SmallArea> selectsmallNmList() throws Exception;
	
	/** 회원 수정중인 플래너 수 조회용 Service
	 * @param memberNo
	 * @return updatePlannerCount
	 * @throws Exception
	 */
	public abstract int updatePlannerCount(int memberNo) throws Exception;

	/** 회원이 참여하고있는 플래너 번호 목록 조회용 Service
	 * @param memberNo
	 * @return plannerNoList
	 * @throws Exception
	 */
	public abstract List<PlannerMember> selectPlannerMember(int memberNo) throws Exception;

	/** 수정중인 플래너 목록 조회용 Service
	 * @param memberNo
	 * @return uPlannerList
	 * @throws Exception
	 */
	public abstract List<PlannerCard> updatePlannerList(int memberNo) throws Exception;

	/** 완료된 플래너 목록 조회용 Service
	 * @param memberNo
	 * @return cPlannerList
	 * @throws Exception
	 */
	public abstract List<PlannerCard> completePlannerList(int memberNo) throws Exception;

	/** 플래너 지역이름 조회용 Service
	 * @param noList
	 * @return areaNames
	 * @throws Exception
	 */
	public abstract List<AreaName> selectAreaNames(List<Integer> noList) throws Exception;

	/** 플래너 삭제용 Service
	 * @param delPlanner
	 * @return result
	 * @throws Exception
	 */
	public abstract int delPlanner(PlannerMember delPlanner) throws Exception;

	/** 플래너 나가기용 Service
	 * @param outPlanner
	 * @return result
	 * @throws Exception
	 */
	public abstract int outPlanner(PlannerMember outPlanner) throws Exception;

}
