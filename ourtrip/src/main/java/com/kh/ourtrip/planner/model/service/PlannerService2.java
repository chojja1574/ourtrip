package com.kh.ourtrip.planner.model.service;

import java.util.List;

import com.kh.ourtrip.planner.model.vo.ChattingLogView;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.PlannerView;
import com.kh.ourtrip.planner.model.vo.Schedule;

public interface PlannerService2 {
	public abstract List<PlannerView> selectPlannerView(int no) throws Exception;
	
	public abstract int getNextDateNo() throws Exception;
	
	public abstract int getNextScheduleNo() throws Exception;

	public abstract int insertDate(Day day) throws Exception;

	public abstract int updateTripDate(List<Day> dayList) throws Exception;

	public abstract int insertDefaultSchedule(Schedule schedule) throws Exception;

	public abstract int deleteDate(int dateNo) throws Exception;

	public abstract int updateSchedule(Schedule sche) throws Exception;

	public abstract int insertSchedule(Schedule sche) throws Exception;

	public abstract int deleteSchedule(int sno) throws Exception;

	public abstract int insertChattingLog(ChattingLogView chatLog) throws Exception;

	public abstract List<ChattingLogView> selectChatList(int no) throws Exception;
}
