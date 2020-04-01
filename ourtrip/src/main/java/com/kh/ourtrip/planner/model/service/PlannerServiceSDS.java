package com.kh.ourtrip.planner.model.service;

import java.util.List;
import java.util.Map;

import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.ChattingLogView;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerInfo;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.PlannerMemberView;
import com.kh.ourtrip.planner.model.vo.PlannerView;
import com.kh.ourtrip.planner.model.vo.Schedule;
import com.kh.ourtrip.planner.model.vo.SmallArea;

public interface PlannerServiceSDS {

	// @author 신덕수
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

	
	// @author 조유상
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
	

	/** 플래너 복사용 Service
	 * @param no
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public abstract int plannerCopy(int no, int memberNo) throws Exception;
	
	// @author 박지현
	/** PlannerView에서 플래너 번호를 이용하여 플래너 정보를 가져오는 Service
	 * @param no
	 * @return pList
	 * @throws Exception
	 */
	public abstract List<PlannerView> selectPlannerView(int no) throws Exception;
	
	/** PLANNER_DATE 테이블의 다음 DATE_NO를 가져오는 Service
	 * @return result
	 * @throws Exception
	 */
	public abstract int getNextDateNo() throws Exception;
	
	/** SCHEDULE 테이블의 다음 SCHEDULE_NO를 가져오는 Service
	 * @return result
	 * @throws Exception
	 */
	public abstract int getNextScheduleNo() throws Exception;

	/** 플래너 일차 삽입용 Service
	 * @param day
	 * @return result
	 * @throws Exception
	 */
	public abstract int insertDate(Day day) throws Exception;

	/** 플래너 일차 순서(TRIP_DATE) 수정용 Service
	 * @param dayList
	 * @return -1
	 * @throws Exception
	 */
	public abstract int updateTripDate(List<Day> dayList) throws Exception;

	/** 플래너 일차 생성 시 기본 일정 생성 Service
	 * @param schedule
	 * @return result
	 * @throws Exception
	 */
	public abstract int insertDefaultSchedule(Schedule schedule) throws Exception;

	/** 플래너 일차 삭제 Service
	 * @param dateNo
	 * @return result
	 * @throws Exception
	 */
	public abstract int deleteDate(int dateNo) throws Exception;

	/** 플래너 일정 수정용 Service
	 * @param sche
	 * @return result
	 * @throws Exception
	 */
	public abstract int updateSchedule(Schedule sche) throws Exception;

	/** 플래너 일정 추가용 Service
	 * @param sche
	 * @return result
	 * @throws Exception
	 */
	public abstract int insertSchedule(Schedule sche) throws Exception;

	/** 플래너 일정 삭제용 Service
	 * @param sno
	 * @return result
	 * @throws Exception
	 */
	public abstract int deleteSchedule(int sno) throws Exception;

	/** 플래너 채팅내역 저장용 Service
	 * @param chatLog
	 * @return result
	 * @throws Exception
	 */
	public abstract int insertChattingLog(ChattingLogView chatLog) throws Exception;

	/** 플래너 번호를 이용하여 해당플래너 채팅내역 불러오는 Service
	 * @param no
	 * @return cList
	 * @throws Exception
	 */
	public abstract List<ChattingLogView> selectChatList(int no) throws Exception;

	/** 플래너 번호를 이용하여 플래너에 참여중인 회원을 불러오는 Service
	 * @param pno
	 * @return pm
	 * @throws Exception
	 */
	public abstract List<PlannerMemberView> selectPlannerMemeberListUsePlannerNo(int pno) throws Exception;

	/** 플래너에 해당 회원이 존재하는지 검사하는 Service
	 * @param pm
	 * @return pmList
	 * @throws Exception
	 */
	public abstract int selectPlannerMemeberExist(PlannerMemberView pm) throws Exception;

	/** PLANNER_MEMBER 테이블에 새로운 값 추가 Service
	 * @param pm
	 * @throws Exception
	 */
	public abstract int insertPlannerMember(PlannerMember pm) throws Exception;

	/** PLANNER_MEMBER 테이블의 permission값 수정 Service
	 * @param pm
	 * @return result
	 * @throws Exception
	 */
	public abstract int updatePermission(PlannerMember pm) throws Exception;

	/** 총 경비 수정용 Service
	 * @param sumCost
	 * @return
	 * @throws Exception
	 */
	public abstract int updateSumCost(Planner p) throws Exception;

	/** 출발일 수정용 Service
	 * @param parseInt
	 * @return
	 */
	public abstract int updateStartDate(Planner p) throws Exception;
	
}
