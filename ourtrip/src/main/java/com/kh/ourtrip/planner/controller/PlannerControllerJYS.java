package com.kh.ourtrip.planner.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.service.PlannerServiceJYS;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerMember;

@Controller
@SessionAttributes({"loginMember", "msg"})
@RequestMapping("/planner/*")
public class PlannerControllerJYS {
	
	@Autowired
	private PlannerServiceJYS plannerService;
	
	@RequestMapping("myPlanner")
	public String myPlanner(Model model) {
		
		if(model.getAttribute("loginMember") == null) {
			model.addAttribute("msg", "로그인 후 이용가능합니다.");
			return "redirect:/";
		}
		
		int memberNo = ((Member)model.getAttribute("loginMember")).getMemberNo();
		
		try {
			// 수정중인 플래너 목록 조회
			List<PlannerCard> uPlannerList = plannerService.updatePlannerList(memberNo);

			// 완료된 플래너 목록 조회
			List<PlannerCard> cPlannerList = plannerService.completePlannerList(memberNo);
			
			// 지역이름을 조회하기 위해 리스트에 플래너 번호를 담음
			List<Integer> noList = new ArrayList<Integer>();
			
			// 수정중, 완료된 플래너 목록에 플래너 번호가 겹치지 않아서 따로 조건두지 않음
			for(PlannerCard p : uPlannerList) noList.add(p.getPlannerNo());
			for(PlannerCard p : cPlannerList) noList.add(p.getPlannerNo());
			
			List<AreaName> areaNames = new ArrayList<AreaName>();
			if(!noList.isEmpty()) {
				// 플래너별 지역이름들 조회
				areaNames = plannerService.selectAreaNames(noList);
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

}
