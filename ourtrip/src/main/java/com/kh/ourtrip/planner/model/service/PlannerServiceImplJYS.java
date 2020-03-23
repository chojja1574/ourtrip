package com.kh.ourtrip.planner.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ourtrip.planner.model.dao.PlannerDAOJYS;

@Service
public class PlannerServiceImplJYS implements PlannerServiceJYS{

	@Autowired
	private PlannerDAOJYS plannerDAO;
}
