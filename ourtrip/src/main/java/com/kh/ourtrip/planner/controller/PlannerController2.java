package com.kh.ourtrip.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ourtrip.planner.model.service.PlannerService2;
import com.kh.ourtrip.planner.model.vo.PlannerView;

@Controller
@RequestMapping("/planner1/*")
public class PlannerController2 {
	
	@Autowired
	PlannerService2 plannerService2;
	
	@RequestMapping("testInput")
	public String chattingRoom() {
		return "planner/chattingRoom";
	}
	
	@RequestMapping("editplanner")
	public String chattingForm(Model model, Integer no, RedirectAttributes rdAttr, String userId, String selectRoom) {
		
		try {
			List<PlannerView> selectPlannerView = plannerService2.selectPlannerView(no);
		}catch(Exception e) {
			
		}
		model.addAttribute("userId", userId);
		model.addAttribute("selectRoom", selectRoom);
		
		return "planner/editPlanner";
	}
	
	@RequestMapping("chattingForm2")
	public String chattingForm2(Model model, Integer no, RedirectAttributes rdAttr, String userId, String selectRoom) {
		model.addAttribute("userId", userId);
		model.addAttribute("selectRoom", selectRoom);
		
		return "websocket-echo";
	}
}
