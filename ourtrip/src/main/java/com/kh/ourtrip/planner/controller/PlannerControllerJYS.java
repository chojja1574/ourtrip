package com.kh.ourtrip.planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.service.PlannerServiceJYS;

@Controller
@RequestMapping("/planner/*")
public class PlannerControllerJYS {
	
	@Autowired
	private PlannerServiceJYS plannerService;
	
	@RequestMapping("myPlanner")
	public String myPlanner(Model model) {
		
		int memberNo = ((Member)model.getAttribute("loginMember")).getMemberNo();
		
		try {
			// 수정 플래너 수 조회
			int updatePlannerCount = plannerService.updatePlannerCount(memberNo);
			
			// 완료 플래너 수 조회
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "나의 플래너 목록 조회 중 오류 발생");
			return "common/errorPage";
		}
		
		return "planner/myPlanner";
	}

}
