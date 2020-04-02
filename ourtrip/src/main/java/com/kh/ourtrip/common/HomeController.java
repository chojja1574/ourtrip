package com.kh.ourtrip.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.ourtrip.planner.model.service.PlannerService;
import com.kh.ourtrip.planner.model.vo.PlannerCard;


@Controller
public class HomeController {

	@Autowired
	public PlannerService plannerService;
	
	@RequestMapping("/")
	public String intro(Model model) {
		try {
			
			// 추천플래너 카드 조회
			List<PlannerCard> recommendPCList = plannerService.selectRecommendPCList();
			if(!recommendPCList.isEmpty()) {
				model.addAttribute("recommendPCList", recommendPCList);
			} else {
				model.addAttribute("msg", "조회내용이 없습니다");
			}
			
			return "index";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "메인페이지 이동과정중 오류");
			return "common/errorPage";
		}
	}
}
