package com.kh.ourtrip.planner.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ourtrip.planner.model.dao.PlannerDAO2;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.ChattingLogView;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.PlannerMemberView;
import com.kh.ourtrip.planner.model.vo.PlannerView;
import com.kh.ourtrip.planner.model.vo.Schedule;

@Service
public class PlannerService2Imlp implements PlannerService2 {

	@Autowired
	PlannerDAO2 plannerDAO2;
	
	/** PlannerView에서 플래너 번호를 이용하여 플래너 정보를 가져오는 Service
	 * @param no
	 * @return pList
	 * @throws Exception
	 */
	@Override
	public List<PlannerView> selectPlannerView(int no) throws Exception {
		return plannerDAO2.selectPlannerView(no);
	}

	/** PLANNER_DATE 테이블의 다음 DATE_NO를 가져오는 Service
	 * @return result
	 * @throws Exception
	 */
	@Override
	public int getNextDateNo() throws Exception {
		return plannerDAO2.getNextDateNo();
	}
	
	/** SCHEDULE 테이블의 다음 SCHEDULE_NO를 가져오는 Service
	 * @return result
	 * @throws Exception
	 */
	@Override
	public int getNextScheduleNo() throws Exception {
		return plannerDAO2.getNextScheduleNo();
	}

	/** 플래너 일차 삽입용 Service
	 * @param day
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertDate(Day day) throws Exception {
		return plannerDAO2.insertDate(day);
	}

	/** 플래너 일차 순서(TRIP_DATE) 수정용 Service
	 * @param dayList
	 * @return -1
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateTripDate(List<Day> dayList) throws Exception {
		return plannerDAO2.updateTripDate(dayList);
	}

	/** 플래너 일차 생성 시 기본 일정 생성 Service
	 * @param schedule
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertDefaultSchedule(Schedule schedule) throws Exception {
		return plannerDAO2.insertDefaultSchedule(schedule);
	}

	/** 플래너 일차 삭제 Service
	 * @param dateNo
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteDate(int dateNo) throws Exception {
		return plannerDAO2.deleteDate(dateNo);
	}

	/** 플래너 일정 수정용 Service
	 * @param sche
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateSchedule(Schedule sche) throws Exception {
		return plannerDAO2.updateSchedule(sche);
	}

	/** 플래너 일정 추가용 Service
	 * @param sche
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertSchedule(Schedule sche) throws Exception {
		return plannerDAO2.insertSchedule(sche);
	}

	/** 플래너 일정 삭제용 Service
	 * @param sno
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteSchedule(int sno) throws Exception {
		return plannerDAO2.deleteSchedule(sno);
	}

	/** 플래너 채팅내역 저장용 Service
	 * @param chatLog
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertChattingLog(ChattingLogView chatLog) throws Exception {
		return plannerDAO2.insertChattingLog(chatLog);
	}

	/** 플래너 번호를 이용하여 해당플래너 채팅내역 불러오는 Service
	 * @param no
	 * @return cList
	 * @throws Exception
	 */
	@Override
	public List<ChattingLogView> selectChatList(int no) throws Exception {
		return plannerDAO2.selectChatList(no);
	}

	/** 플래너 번호를 이용하여 플래너에 참여중인 회원을 불러오는 Service
	 * @param pno
	 * @return pmList
	 * @throws Exception
	 */
	@Override
	public List<PlannerMemberView> selectPlannerMemeberListUsePlannerNo(int pno) throws Exception {
		return plannerDAO2.selectPlannerMemeberListUsePlannerNo(pno);
	}

	/** 플래너에 해당 회원이 존재하는지 검사하는 Service
	 * @param pm
	 * @return pmList
	 * @throws Exception
	 */
	@Override
	public int selectPlannerMemeberExist(PlannerMemberView pm) throws Exception {
		return plannerDAO2.selectPlannerMemeberExist(pm);
	}

	/** PLANNER_MEMBER 테이블에 새로운 값 추가 Service
	 * @param pm
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertPlannerMember(PlannerMember pm) throws Exception {
		return plannerDAO2.insertPlannerMemeber(pm);
	}
	
	/** PLANNER_MEMBER 테이블의 permission값 수정 Service
	 * @param pm
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updatePermission(PlannerMember pm) throws Exception{
		return plannerDAO2.updatePermission(pm);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateSumCost(Planner p) throws Exception {
		return plannerDAO2.updateSumCost(p);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateStartDate(Planner p) throws Exception {
		return plannerDAO2.updateStartDate(p);
	}

	@Override
	public int countDate(int pno) throws Exception {
		return plannerDAO2.countDate(pno);
	}
	
	@Override
	public int countSchedule(int dno) throws Exception {
		return plannerDAO2.countSchedule(dno);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateTitle(Planner planner) throws Exception {
		return plannerDAO2.updateTitle(planner);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updatePassword(Planner planner) throws Exception {
		return plannerDAO2.updatePassword(planner);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updatePublic(Planner planner) throws Exception {
		return plannerDAO2.updatePublic(planner);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int clearUserList(int no) throws Exception {
		return plannerDAO2.clearUserList(no);
	}

	@Override
	public List<AreaName> selectPlannerLocationName(Integer no) throws Exception {
		return plannerDAO2.selectPlannerLocationName(no);
	}

	@Override
	public int updateLocationList(List<AreaName> locationList) throws Exception {
		plannerDAO2.deletePlannerLocation(locationList.get(0).getPlannerNo());
		return plannerDAO2.updateLocationList(locationList);
	}

}
