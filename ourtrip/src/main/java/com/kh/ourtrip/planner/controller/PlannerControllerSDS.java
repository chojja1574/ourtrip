package com.kh.ourtrip.planner.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.ourtrip.planner.model.service.PlannerServiceSDS;
import com.kh.ourtrip.planner.model.vo.PlannerCard;


@Controller
@RequestMapping("/planner/*")
public class PlannerControllerSDS {
	
	@Autowired
	public PlannerServiceSDS plannerServiceSDS;
	
	@RequestMapping("search")
	public String searchForm(Model model) {
		try {
			// 추천리스트 조회
			List<PlannerCard> recommendPCList = plannerServiceSDS.selectRecommendPCList();
			// System.out.println("탐색화면 추천리스트 : " + recommendPCList);
			if(!recommendPCList.isEmpty()) {
				model.addAttribute("recommendPCList", recommendPCList);
			} else {
				model.addAttribute("msg", "추천리스트가 비어있습니다");
			}
			
			return "planner/searchPlanner";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "탐색화면이동중에러");
			return "common/error";
		}
		
	}
	
	@RequestMapping("searchPlanner")
	public String searchPlanner(Model model,
			@RequestParam(value="searchTitle", required=false)
			String searchTitle,
			@RequestParam(value="groupName", required=false)
			String groupName,
			@RequestParam(value="largeArea", required=false)
			String largeArea,
			@RequestParam(value="smallArea", required=false)
			String smallArea,
			@RequestParam(value="viaCheck", required=false)
			String viaCheck, 
			@RequestParam(value="currentPage", required=false)
			String currentPage ){
		
		System.out.println("searchTitle : " + searchTitle);
		System.out.println("groupName : " + groupName);
		System.out.println("largeArea : " + largeArea);
		System.out.println("smallArea : " + smallArea);
		System.out.println("viaCheck : " + viaCheck);
		System.out.println("currentPage : " + currentPage);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchTitle", searchTitle);
		map.put("groupName" , groupName);			
		map.put("largeArea", largeArea);
		map.put("smallArea", smallArea);
		map.put("viaCheck", viaCheck);
		
		try {
			// 전체 플래너 수 조회
			int listCount = plannerServiceSDS.getListCount(map);
			//List<PlannerCard> pList = 
					//plannerServiceSDS.selectSearchPList();
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "검색과정중에러");
			return "common/errorPage";
		}
		return null;
	}
	
}

