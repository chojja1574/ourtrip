package com.kh.ourtrip.planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/planner/*")
public class PlannerControllerSDS {
	
	@RequestMapping("searchPlanner")
	public String searchPlanner() {
		
		return "planner/searchPlanner";
		
	}
}

