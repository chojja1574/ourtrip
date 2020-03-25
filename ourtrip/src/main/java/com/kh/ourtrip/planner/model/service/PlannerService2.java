package com.kh.ourtrip.planner.model.service;

import java.util.List;

import com.kh.ourtrip.planner.model.vo.ChattingLogView;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.PlannerMemberView;
import com.kh.ourtrip.planner.model.vo.PlannerView;
import com.kh.ourtrip.planner.model.vo.Schedule;

public interface PlannerService2 {
	
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

	/** PLANNER_MEMBER 테이블에 새로운 값 추가
	 * @param pm
	 * @throws Exception
	 */
	public abstract int insertPlannerMember(PlannerMember pm) throws Exception;
}
