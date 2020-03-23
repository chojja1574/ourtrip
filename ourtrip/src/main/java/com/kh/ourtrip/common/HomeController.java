package com.kh.ourtrip.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.ourtrip.planner.model.service.PlannerServiceSDS;
import com.kh.ourtrip.planner.model.vo.PlannerCard;


@Controller
public class HomeController {

	@Autowired
	public PlannerServiceSDS plannerServiceSDS;
	
	@RequestMapping("/")
	public String intro(Model model) {
		try {
			List<PlannerCard> recommendPCList = plannerServiceSDS.selectRecommendPCList();
			//System.out.println(recommendPCList);
			if(!recommendPCList.isEmpty()) {
				model.addAttribute("recommendPCList", recommendPCList);
			} else {
				model.addAttribute("msg", "조회내용이 없습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("홈 이동 정상 동작");
		return "index";
	}
}
