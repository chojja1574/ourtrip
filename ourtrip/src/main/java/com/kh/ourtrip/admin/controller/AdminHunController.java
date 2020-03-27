package com.kh.ourtrip.admin.controller;

import java.sql.Date;
import java.util.ArrayList;
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
import com.kh.ourtrip.admin.model.vo.PlannerDeleteReason;
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
	public String memberDetail(Model model, int no, Integer currentPage, HttpServletRequest request) {

		String beforUrl = request.getHeader("referer"); // 이전페이지 주소

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
			if (!plannerList.isEmpty()) {
				plannerInfo = adminHunService.plannerInfo(plannerList, pInf);
				plannerArea = adminHunService.plannerArea(plannerList);

				for (PlannerInfo infList : plannerInfo) {
					for (AreaName areaList : plannerArea) {
						if (infList.getPlannerNo() == areaList.getPlannerNo()) {
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
			} else {
				model.addAttribute("msg", "회원정보 상세조회 실패 .");
				return "redirect:" + beforUrl;
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원 상세정보 조회중 오류 발생");
			return "common/errorPage";
		}

	}

	@RequestMapping("memberDelete")
	public String memberDelete(int memberNo, String email, String delBecause, Model model, Integer currentPage) {

		try {
			int result = adminHunService.memberDelete(memberNo, email, delBecause);

			/*Member detailMem = adminHunService.detail(memberNo);
			ProfileImage pi = adminHunService.selectProfileImage(memberNo);

			List<Integer> plannerList = adminHunService.plannerList(memberNo);

			if (currentPage == null) {
				currentPage = 1;
			}
			PageInfo pInf = Pagination.getPageInfo(8, 10, currentPage, plannerList.size());

			List<PlannerInfo> plannerInfo = new ArrayList<PlannerInfo>();
			List<AreaName> plannerArea = new ArrayList<AreaName>();
			List<AreaName> areaNames = new ArrayList<AreaName>();
			if (!plannerList.isEmpty()) {
				plannerInfo = adminHunService.plannerInfo(plannerList, pInf);
				plannerArea = adminHunService.plannerArea(plannerList);

				for (PlannerInfo infList : plannerInfo) {
					for (AreaName areaList : plannerArea) {
						if (infList.getPlannerNo() == areaList.getPlannerNo()) {
							areaNames.add(areaList);
						}
					}
					infList.setAreaNames(areaNames);
				}
				*/
				if (result > 0) {
					model.addAttribute("msg" ,"회원 삭제에 성공하셨습니다.");
					return "admin/memberDetail";
				}else {
					return "admin/memberDetail";
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원 강퇴중 오류 발생");
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
			for (PlannerInfo infList : totalList) {
				for (AreaName infoarea : areaList) {
					if (infList.getPlannerNo() == infoarea.getPlannerNo()) {
						areaNames.add(infoarea);
					}
				}
				infList.setAreaNames(areaNames);
			}

			for (PlannerInfo infList : totalList) {
				for (Day infodays : dayList) {
					if (infList.getPlannerNo() == infodays.getPlannerNo()) {
						countDay = new Date(
								infList.getPlannerStartDT().getTime() + infodays.getTripDate() * (24 * 60 * 60 * 1000));
					}
				}
				infList.setPlannerEndDate(countDay);
			}

			model.addAttribute("plannerList", totalList);
			model.addAttribute("pInfom", pInf);
			return "admin/adminplannerList";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "플래너목록 조회 실패 .");
			return "redirect:" + beforUrl;
		}

	}

	@RequestMapping("searchPlanner")
	public String searchadminPlanner(Model model, HttpServletRequest request, Integer currentPage,
			@RequestParam(value = "searchKey", required = false) String searchKey,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "searchGroup", required = false) List<Integer> searchGroup,
			@RequestParam(value = "searchArea", required = false) String searchArea,
			@RequestParam(value = "searchLocal", required = false) String searchLocal,
			@RequestParam(value = "deleted", required = false) String deleted,
			@RequestParam(value = "startTrip", required = false) String startTrip,
			@RequestParam(value = "endTrip", required = false) String endTrip) {

		String beforUrl = request.getHeader("referer");
		Map<String, Object> keyword = new HashMap<String, Object>();
		keyword.put("searchKey", searchKey);
		keyword.put("searchValue", searchValue);
		keyword.put("searchGroup", searchGroup);
		keyword.put("searchArea", searchArea);
		keyword.put("searchLocal", searchLocal);
		keyword.put("deleted", deleted);
		keyword.put("startTrip", startTrip);
		keyword.put("endTrip", endTrip);

		try {
			List<Integer> searchResultcount = adminHunService.resultCount(keyword);
			System.out.println("searchResultcount 검색한 리스트 수 = " + searchResultcount);
			if (currentPage == null) {
				currentPage = 1;
			}
			PageInfo pInf = Pagination.getPageInfo(10, 10, currentPage, searchResultcount.size());

			List<PlannerInfo> searchResult = adminHunService.searchResult(pInf, keyword);
			System.out.println("searchResult 검색한 결과 플래너 = " + searchResult);
			List<AreaName> areaList = adminHunService.resultArea(searchResultcount);
			System.out.println("areaList 플래너별 지역 조회 = " + areaList);
			List<Day> dayList = adminHunService.resultDay(searchResultcount);
			System.out.println("dayList 플래너별 날짜 조회" + dayList);
			Date countDay = null;
			List<AreaName> areaNames = new ArrayList<AreaName>();
			for (PlannerInfo infList : searchResult) {
				for (AreaName infoarea : areaList) {
					if (infList.getPlannerNo() == infoarea.getPlannerNo()) {
						areaNames.add(infoarea);
					}
				}
				infList.setAreaNames(areaNames);
			}

			for (PlannerInfo infList : searchResult) {
				for (Day infodays : dayList) {
					if (infList.getPlannerNo() == infodays.getPlannerNo()) {
						countDay = new Date(infList.getPlannerStartDT().getTime()
								+ (infodays.getTripDate() * (24 * 60 * 60 * 1000)));
					}
				}
				infList.setPlannerEndDate(countDay);
			}

			model.addAttribute("plannerList", searchResult);
			model.addAttribute("pInfom", pInf);
			return "admin/adminplannerList";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "플래너 검색 실패 .");
			return "redirect:" + beforUrl;
		}

	}

	@RequestMapping("plannerDetail")
	public String plannerDetail(int no, HttpServletRequest request, Model model) {
		String beforUrl = request.getHeader("referer");

		try {
			PlannerInfo plannerInfo = adminHunService.plannerDetail(no);
			List<AreaName> plannerArea = adminHunService.areaDetail(no);

			Date addDate = new Date(
					plannerInfo.getPlannerStartDT().getTime() + plannerInfo.getTripDate() * (24 * 60 * 60 * 1000));

			plannerInfo.setPlannerEndDate(addDate);

			model.addAttribute("plannerinfo", plannerInfo);
			model.addAttribute("plannerArea", plannerArea);
			return "admin/adminplannerDetail";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "플래너 조회 실패 .");
			return "redirect:" + beforUrl;
		}

	}

	@RequestMapping("plannerDelete")
	public String plannerDelte(PlannerDeleteReason pdr, String email, Model model, HttpServletRequest request) {
		String beforUrl = request.getHeader("referer");

		try {
			int result = adminHunService.deletePlanner(pdr.getPlannerNo());
			int reason = adminHunService.reason(pdr);
			if (result > 0 && reason > 0) {
				PlannerInfo plannerInfo = adminHunService.plannerDetail(pdr.getPlannerNo());
				List<AreaName> plannerArea = adminHunService.areaDetail(pdr.getPlannerNo());

				Date addDate = new Date(
						plannerInfo.getPlannerStartDT().getTime() + plannerInfo.getTripDate() * (24 * 60 * 60 * 1000));
				plannerInfo.setPlannerEndDate(addDate);

				adminHunService.sendEmail(email, pdr);

				model.addAttribute("msg", "플래너 삭제를 성공하였습니다");
				model.addAttribute("plannerinfo", plannerInfo);
				model.addAttribute("plannerArea", plannerArea);

			} else {
				model.addAttribute("msg", "플래너 삭제를 실패하였습니다");
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "플래너 삭제 중 오류발생 .");
			return "redirect:" + beforUrl;
		}
		return "admin/adminplannerDetail";

	}

	@RequestMapping("plannerRecovery")
	public String plannerRecovery(Model model, int plannerNo, HttpServletRequest request) {
		String beforUrl = request.getHeader("referer");

		try {
			int result = adminHunService.recoveryPlanner(plannerNo);

			if (result > 0) {

				PlannerInfo plannerInfo = adminHunService.plannerDetail(plannerNo);
				List<AreaName> plannerArea = adminHunService.areaDetail(plannerNo);

				Date addDate = new Date(
						plannerInfo.getPlannerStartDT().getTime() + plannerInfo.getTripDate() * (24 * 60 * 60 * 1000));
				plannerInfo.setPlannerEndDate(addDate);

				model.addAttribute("msg", "플래너 복구에 성공하였습니다");
				model.addAttribute("plannerinfo", plannerInfo);
				model.addAttribute("plannerArea", plannerArea);

				return "admin/adminplannerDetail";

			} else {
				model.addAttribute("msg", "플래너 복구에 실패하였습니다");
				return "admin/adminplannerDetail";
			}

		} catch (Exception e) {
			model.addAttribute("msg", "플래너 삭제 중 오류발생 .");
			return "redirect:" + beforUrl;
		}

	}

}
