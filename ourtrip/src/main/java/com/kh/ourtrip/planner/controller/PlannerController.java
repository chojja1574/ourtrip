package com.kh.ourtrip.planner.controller;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.service.PlannerService;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.SmallArea;

@Controller
@SessionAttributes({ "msg", "loginMember" })
@RequestMapping("/planner/*")
public class PlannerController {

	@Autowired
	PlannerService plannerService;

	@RequestMapping("create")
	public String createPlannerform(HttpServletRequest request, Member loginMember, Model model) {
		String beforUrl = request.getHeader("referer"); // 이전페이지 주소

		try {
			List<LargeArea> largeNmList = plannerService.selectLargeNmList();
			List<SmallArea> smallNmList = plannerService.selectsmallNmList();
			model.addAttribute("largeNmList", largeNmList);
			model.addAttribute("smallNmList", smallNmList);

			if (loginMember == null) {
				model.addAttribute("msg", "로그인후 이용해 주세요");
				return "common/errorPage";
			} else {
				return "planner/createForm";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "생성페이지 이동중 오류발생");
			return "common/errorPage";
		}
	}

	@RequestMapping("createPlanner")
	public String createPlanner(Planner planner, Model model, String locationList) {

		if (planner.getPlannerPublicYN().equals("on")) {
			planner.setPlannerPublicYN("Y");
		} else {
			planner.setPlannerPublicYN("N");
		}

		String url = "";
		for (int i = 1; i < 16; i++) {
			double random = Math.random();
			int ranDomInt = (int) (random * 10);
			url += ranDomInt;
		}
		planner.setPlannerUrl(url);

		Member member = (Member) (model.getAttribute("loginMember"));
		
		try {
			
			
			
			// JSON Parse 선언
			JSONParser jsonParser = new JSONParser();
			// 오브젝트에 담음
			Object obj = jsonParser.parse(locationList);
			// Json 으로 변경
			// JSONObject jsonObj = (JSONObject)obj;
			JSONArray jarr = (JSONArray) obj;
			// jarr.add(jsonObj);
			int plannerNo = plannerService.createPlanner(planner);
			int result = 0;

			if (plannerNo > 0) {
				result = plannerService.createLocation(jarr, plannerNo);
				result *= plannerService.createMember(plannerNo, member.getMemberNo());
				if (result != 0) {

					model.addAttribute("플래너 생성이 완료되었습니다");
					return "planner/myPlanner";
				}
			}

			model.addAttribute("플래너 생성이 실패하였습니다");
			return "planner/plannerForm";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "플래너 생성과정 중 오류발생");
			return "common/errorPage";
		}

	}

}
