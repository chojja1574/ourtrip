package com.kh.ourtrip.planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.ourtrip.planner.model.service.PlannerService;

@Controller
@RequestMapping("/planner/*")
public class PlannerController {
	
	@Autowired
	private PlannerService plnnerService;
	
	@RequestMapping("create")
	public String  plannerForm() {
		
		return "planner/createForm";
	}
	
	
	

}
