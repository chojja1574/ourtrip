package com.kh.ourtrip.planner.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.PlannerView;
import com.kh.ourtrip.planner.model.vo.Schedule;

@Repository
public class PlannerDAO2 {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<PlannerView> selectPlannerView(int no) throws Exception{
		return sqlSessionTemplate.selectList("planner1Mapper.selectPlannerView",no);
	}

	public int getNextDateNo() throws Exception {
		return sqlSessionTemplate.selectOne("planner1Mapper.selectDateNo");
	}
	public int getNextScheduleNo() throws Exception {
		return sqlSessionTemplate.selectOne("planner1Mapper.selectScheduleNo");
	}

	public int insertDate(Day day) throws Exception {
		return sqlSessionTemplate.insert("planner1Mapper.insertDate", day);
	}

	public int updateTripDate(List<Day> dayList) throws Exception {
		return sqlSessionTemplate.update("planner1Mapper.updateTripDate", dayList);
	}

	public int insertDefaultSchedule(Schedule schedule) throws Exception {
		return sqlSessionTemplate.insert("planner1Mapper.insertDefaultSchedule", schedule);
	}

	public int deleteDate(int dateNo) throws Exception {
		return sqlSessionTemplate.delete("planner1Mapper.deleteDate", dateNo);
	}
	
}
