package com.kh.ourtrip.admin.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kh.ourtrip.admin.model.service.AdminHunService;
import com.kh.ourtrip.common.Pagination;
import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.member.model.vo.ProfileImage;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.PlannerInfo;

@SessionAttributes({ "memberList", "msg" })
@Controller
@RequestMapping("/admin/*")
public class AdminHunController {

	@Autowired
	AdminHunService adminHunService;

	/**
	 * 전체회원 조회용 Controller
	 * 
	 * @param model
	 * @param currentPage
	 * @return path
	 */
	@RequestMapping("List")
	public String memberFullList(Model model,
			@RequestParam(value = "currentPage", required = false) Integer currentPage) {

		try {

			int listFullCount = adminHunService.getListFullCount();

			if (currentPage == null) {
				currentPage = 1;
			}
			PageInfo pInf = Pagination.getPageInfo(10, 10, currentPage, listFullCount);

			List<Member> memberList = adminHunService.selectFullList(pInf);

			model.addAttribute("memberList", memberList);
			model.addAttribute("pInfom", pInf);
			return "admin/memberList";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원 목록 조회중 오류 발생");
			return "common/errorPage";
		}

	}

	@RequestMapping("search")
	public String memberList(Model model, @RequestParam(value = "searchKey", required = false) String searchKey,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "currentPage", required = false) Integer currentPage) {

		try {

			Map<String, String> map = null;
			if (searchKey != null && searchValue != null) {
				map = new HashMap<String, String>();
				map.put("searchKey", searchKey);
				map.put("searchValue", searchValue);
			}

			int listCount = adminHunService.getListCount(map);

			if (currentPage == null) {
				currentPage = 1;
			}
			PageInfo pInf = Pagination.getPageInfo(10, 10, currentPage, listCount);

			List<Member> memberList = adminHunService.selectList(map, pInf);

			model.addAttribute("memberList", memberList);
			model.addAttribute("pInf", pInf);
			return "admin/memberList";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원 목록 조회중 오류 발생");
			return "common/errorPage";
		}

	}

	@RequestMapping("detail")
	public String memberDetail(Model model, int no, Integer currentPage,HttpServletRequest request) {
		
		String beforUrl = request.getHeader("referer"); //이전페이지 주소

		try {
			Member detailMem = adminHunService.detail(no);
			ProfileImage pi = adminHunService.selectProfileImage(no);
			
			List<Integer> plannerList = adminHunService.plannerList(no);

			if (currentPage == null) {
				currentPage = 1;
			}
			PageInfo pInf = Pagination.getPageInfo(8, 10, currentPage, plannerList.size());

			
			List<PlannerInfo> plannerInfo = new ArrayList<PlannerInfo>();
			List<AreaName> plannerArea = new ArrayList<AreaName>();
			List<AreaName> areaNames = new ArrayList<AreaName>();
			if(!plannerList.isEmpty()) {
				 plannerInfo = adminHunService.plannerInfo(plannerList, pInf);
				 plannerArea = adminHunService.plannerArea(plannerList);
				 
				 for(PlannerInfo infList : plannerInfo) {
					 for(AreaName areaList : plannerArea) {
						 if(infList.getPlannerNo() == areaList.getPlannerNo()) {
							 areaNames.add(areaList);
						 }
					 }
					 infList.setAreaNames(areaNames);
				 }
			}

			if (detailMem != null && plannerInfo != null) {
				
				model.addAttribute("detailMember", detailMem);
				model.addAttribute("pInfom", pInf);
				model.addAttribute("plannerInfo", plannerInfo);
				model.addAttribute("pi", pi);
				return "admin/memberDetail";
			}else {
				model.addAttribute("msg", "회원정보 상세조회 실패 .");
				return "redirect:" + beforUrl;
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원 상세정보 조회중 오류 발생");
			return "common/errorPage";
		}

	}
	
	@RequestMapping("plannerList")
	public String plannerList(Model model, HttpServletRequest request, Integer currentPage) {
		String beforUrl = request.getHeader("referer");
		
		try {
			int plannerCount = adminHunService.plannerCount();
			
			if (currentPage == null) {
				currentPage = 1;
			}
			PageInfo pInf = Pagination.getPageInfo(10, 10, currentPage, plannerCount);
			
			List<PlannerInfo> totalList = adminHunService.plannerTotal(pInf);
			List<AreaName> areaList = adminHunService.areaList();
			List<AreaName> areaNames = new ArrayList<AreaName>();
			List<Day> dayList = adminHunService.dayList();
			 Date countDay = null;
				 for(PlannerInfo infList : totalList) {
					 for(AreaName infoarea : areaList) {
						 if(infList.getPlannerNo() == infoarea.getPlannerNo()) {
							 areaNames.add(infoarea);
						 }
					 }
					 infList.setAreaNames(areaNames);
				 }
				 
				 for(PlannerInfo infList : totalList) {
					 for(Day infodays : dayList) {
						 if(infList.getPlannerNo() == infodays.getPlannerNo()) {
							 countDay = new Date(infList.getPlannerStartDT().getTime() + infodays.getTripDate()*(24*60*60*1000));
						 }
					 }
					 infList.setPlannerEndDate(countDay);
				 }
				 
				
				
				 
				 
				 
		
			model.addAttribute("plannerList" ,totalList);
			model.addAttribute("pInfom", pInf);
			return "admin/adminplannerList";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "플래너정보 조회 실패 .");
			return "redirect:" + beforUrl;
		}
		
		
	}
	
	
	

	

}
