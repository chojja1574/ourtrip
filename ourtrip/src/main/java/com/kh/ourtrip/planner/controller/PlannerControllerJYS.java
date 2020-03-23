package com.kh.ourtrip.planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.ourtrip.planner.model.service.PlannerServiceJYS;

@Controller
@RequestMapping("/planner/*")
public class PlannerControllerJYS {
	
	@Autowired
	private PlannerServiceJYS plannerService;
	
	@RequestMapping("myPlanner")
	public String myPlanner() {
		
		return "planner/myPlanner";
	}

}
