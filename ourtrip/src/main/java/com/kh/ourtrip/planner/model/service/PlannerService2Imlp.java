package com.kh.ourtrip.planner.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ourtrip.planner.model.dao.PlannerDAO2;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.PlannerView;
import com.kh.ourtrip.planner.model.vo.Schedule;

@Service
public class PlannerService2Imlp implements PlannerService2 {

	@Autowired
	PlannerDAO2 plannerDAO2;
	
	@Override
	public List<PlannerView> selectPlannerView(int no) throws Exception {
		return plannerDAO2.selectPlannerView(no);
	}

	@Override
	public int getNextDateNo() throws Exception {
		return plannerDAO2.getNextDateNo();
	}
	
	@Override
	public int getNextScheduleNo() throws Exception {
		return plannerDAO2.getNextScheduleNo();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertDate(Day day) throws Exception {
		return plannerDAO2.insertDate(day);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateTripDate(List<Day> dayList) throws Exception {
		return plannerDAO2.updateTripDate(dayList);
	}

	@Override
	public int insertDefaultSchedule(Schedule schedule) throws Exception {
		return plannerDAO2.insertDefaultSchedule(schedule);
	}

}
