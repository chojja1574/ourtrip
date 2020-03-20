package com.kh.ourtrip.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.ourtrip.planner.model.service.PlannerServiceSDS;


@Controller
public class HomeController {

	@Autowired
	public PlannerServiceSDS plannerServiceSDS;
	
	@RequestMapping("/")
	public String intro() {
		
		System.out.println("홈 이동 정상 동작");
		return "index";
	}
}
