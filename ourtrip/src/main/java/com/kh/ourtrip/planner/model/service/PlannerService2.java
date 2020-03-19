package com.kh.ourtrip.planner.model.service;

import java.util.List;

import com.kh.ourtrip.planner.model.vo.PlannerView;

public interface PlannerService2 {
	public abstract List<PlannerView> selectPlannerView(int no) throws Exception;
}
