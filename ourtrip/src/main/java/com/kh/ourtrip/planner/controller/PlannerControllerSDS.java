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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.ourtrip.common.Pagination;
import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.planner.model.service.PlannerServiceSDS;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.SmallArea;


@Controller
@RequestMapping("/planner/*") // 테스트를 위한 임시 변경
public class PlannerControllerSDS {
	
	@Autowired
	public PlannerServiceSDS plannerServiceSDS;
	
	@RequestMapping("search")
	public String searchForm(Model model) {
		try {
			// 추천리스트 조회
			List<PlannerCard> recommendPCList = plannerServiceSDS.selectRecommendPCList();
			
			// 화면에 보여줄 대지역, 중소지역 리스트 조회
			List<LargeArea> largeNmList = plannerServiceSDS.selectLargeNmList();
			List<SmallArea> smallNmList = plannerServiceSDS.selectsmallNmList();
			
			if(!recommendPCList.isEmpty()) {
				model.addAttribute("recommendPCList", recommendPCList);
			} else {
				model.addAttribute("msg", "추천리스트가 비어있습니다");
			}
			model.addAttribute("largeNmList", largeNmList);
			model.addAttribute("smallNmList", smallNmList);
			
			return "planner/searchPlanner";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "탐색화면이동중에러");
			return "common/error";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="searchPlanner",
					produces= "application/json; charset=utf-8")
	public String searchPlanner(Model model,
			@RequestParam(value="searchTitle", required=false)
			String searchTitle,
			@RequestParam(value="groupName", required=false)
			String groupName,
			@RequestParam(value="largeArea", required=false)
			Integer largeArea,
			@RequestParam(value="smallArea", required=false)
			Integer smallArea,
			@RequestParam(value="viaCheck", required=false)
			String viaCheck, 
			@RequestParam(value="currentPage", required=false)
			Integer currentPage ){
		
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
		if(viaCheck.equals("")) viaCheck = null;
		map.put("viaCheck", viaCheck);
		map.put("currentPage", currentPage);
		
		try {
			// 전체 플래너 수 조회
			int listCount = plannerServiceSDS.getListCount(map);
			//System.out.println("컨트롤러 listCount : " + listCount);
			// 현재 페이지 확인
			if(currentPage == null) currentPage = 1;
			
			// 페이지 정보 저장
			PageInfo pInf = Pagination.
					getPageInfo(4, 5, currentPage, listCount);
			
			// 플래너 목록 조회
			List<PlannerCard> pList = plannerServiceSDS.selectPList(map, pInf);
			
			if(pList==null) {
				return null;
			}
//			System.out.println("Controller pList 목록 : " + pList);
			for(PlannerCard item : pList) {
				System.out.println(item);
			}
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			result.put("pList", pList);
			result.put("currentPage", currentPage);
			
			
			Gson gson = new GsonBuilder().setDateFormat(
					"yyyy-MM-dd").create();
			
			// Gson gson = new Gson();
			// return gson.toJson(pList);
			return gson.toJson(result);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "검색과정중에러");
			return "common/errorPage";
		}
		
	}
	
}

