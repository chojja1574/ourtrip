package com.kh.ourtrip.planner.model.service;

import java.util.List;

import com.kh.ourtrip.planner.model.vo.PlannerCard;

public interface PlannerServiceSDS {

	public abstract List<PlannerCard> selectRecommendPCList() throws Exception;

}
