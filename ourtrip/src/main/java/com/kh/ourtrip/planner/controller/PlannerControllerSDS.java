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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.ourtrip.common.Pagination;
import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.service.PlannerServiceSDS;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.SmallArea;

@Controller
@SessionAttributes({"loginMember", "msg"})
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
			
			// 검색된 조건이 없으면 널전달
			if(pList==null) {
				return null;
			} else {
				
				System.out.println("Controller pList 목록 : " + pList);
				for(PlannerCard item : pList) {
					System.out.println(item);
				}
				
				Map<String, Object> result = new HashMap<String, Object>();
				
				result.put("pList", pList);
				result.put("pInf", pInf);
				
				Gson gson = new GsonBuilder().setDateFormat(
						"yyyy-MM-dd").create();
				
				// Gson gson = new Gson();
				// return gson.toJson(pList);
				
				return gson.toJson(result);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "검색과정중에러");
			return "common/errorPage";
		}
		
	}
	
	// 나의 플래너 조회
	@RequestMapping("myPlanner")
	public String myPlanner(Model model) {
		
		if(model.getAttribute("loginMember") == null) {
			model.addAttribute("msg", "로그인 후 이용가능합니다.");
			return "redirect:/";
		}
		
		int memberNo = ((Member)model.getAttribute("loginMember")).getMemberNo();
		
		try {
			// 수정중인 플래너 목록 조회
			List<PlannerCard> uPlannerList = plannerServiceSDS.updatePlannerList(memberNo);

			// 완료된 플래너 목록 조회
			List<PlannerCard> cPlannerList = plannerServiceSDS.completePlannerList(memberNo);
			
			// 지역이름을 조회하기 위해 리스트에 플래너 번호를 담음
			List<Integer> noList = new ArrayList<Integer>();
			
			// 수정중, 완료된 플래너 목록에 플래너 번호가 겹치지 않아서 따로 조건두지 않음
			for(PlannerCard p : uPlannerList) noList.add(p.getPlannerNo());
			for(PlannerCard p : cPlannerList) noList.add(p.getPlannerNo());
			
			List<AreaName> areaNames = new ArrayList<AreaName>();
			if(!noList.isEmpty()) {
				// 플래너별 지역이름들 조회
				areaNames = plannerServiceSDS.selectAreaNames(noList);
			}
			
			// 지역이름이 있을 경우
			if(!areaNames.isEmpty()) {
				for(AreaName name : areaNames) {
					
					// 불필요한 반북문을 넘기기위한 변수
					boolean isPass = false;
					
					// 수정중인 플래너 목록들중 번호가 같을 경우
					for(PlannerCard uPlanner : uPlannerList) {
						if(name.getPlannerNo() == uPlanner.getPlannerNo()) {
							if(uPlanner.getareaNames() == null) { // 객체 없을 시 생성후 삽입
								List<AreaName> tempList = new ArrayList<AreaName>();
								tempList.add(name);
								uPlanner.setareaNames(tempList);
							}else {
								uPlanner.getareaNames().add(name);
							}
							isPass = true;
							break;
						}
					}
					
					if(!isPass) {
						for(PlannerCard cPlanner : cPlannerList) {
							if(name.getPlannerNo() == cPlanner.getPlannerNo()) {
								if(cPlanner.getareaNames() == null) {
									List<AreaName> tempList = new ArrayList<AreaName>();
									tempList.add(name);
									cPlanner.setareaNames(tempList);
								}else {
									cPlanner.getareaNames().add(name);
								}
								break;
							}
						}
					}
				}
			}
			
			model.addAttribute("uPlannerList", uPlannerList);
			model.addAttribute("cPlannerList", cPlannerList);
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "나의 플래너 목록 조회 중 오류 발생");
			return "common/errorPage";
		}
		
		return "planner/myPlanner";
	}
	
	// 플래너 삭제
	@RequestMapping("delPlanner")
	public String delPlanner(PlannerMember delPlanner, Model model, RedirectAttributes rdAttr) {
		int memberNo = ((Member)model.getAttribute("loginMember")).getMemberNo();
		delPlanner.setMemberNo(memberNo);
		
		String msg = null;
		try {
			int result = plannerServiceSDS.delPlanner(delPlanner);
			
			if(result > 0) msg = "플래너가 삭제되었습니다.";
			else msg = "플래너 삭제에 실패하였습니다.";
			
			rdAttr.addFlashAttribute("msg", msg);
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "플래너 삭제 과정 중 오류 발생");
			return "common/errorPage";
		}
		
		return "redirect:myPlanner";
	}
	
}

