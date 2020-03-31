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
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerInfo;
import com.kh.ourtrip.planner.model.vo.SmallArea;

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

	/**
	 * 회원 검색용 controller
	 * 
	 * @param model
	 * @param searchKey
	 * @param searchValue
	 * @param currentPage
	 * @return
	 */
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

	/**
	 * 회원 상세조회용 controller
	 * 
	 * @param model
	 * @param no
	 * @param currentPage
	 * @param request
	 * @return
	 */
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

			List<PlannerCard> plannerInfo = new ArrayList<PlannerCard>();
			List<AreaName> areaNames = new ArrayList<AreaName>();

			if (!plannerList.isEmpty()) {
				plannerInfo = adminHunService.plannerInfo(plannerList, pInf);
				areaNames = adminHunService.plannerArea(plannerList);

				for (PlannerCard infList : plannerInfo) {
					for (AreaName areaList : areaNames) {
						if (infList.getPlannerNo() == areaList.getPlannerNo()) {
							infList.setareaNames(areaNames);
						}
					}
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

	/**
	 * 맴버 삭제용 controller
	 * 
	 * @param memberNo
	 * @param email
	 * @param delBecause
	 * @param model
	 * @param currentPage
	 * @return
	 */
	@RequestMapping("memberDelete")
	public String memberDelete(int memberNo, String email, String delBecause, Model model, Integer currentPage) {

		try {
			int result = adminHunService.memberDelete(memberNo, email, delBecause);

			Member detailMem = adminHunService.detail(memberNo);
			ProfileImage pi = adminHunService.selectProfileImage(memberNo);

			List<Integer> plannerList = adminHunService.plannerList(memberNo);

			if (currentPage == null) {
				currentPage = 1;
			}
			PageInfo pInf = Pagination.getPageInfo(8, 10, currentPage, plannerList.size());

			List<PlannerCard> plannerInfo = new ArrayList<PlannerCard>();
			List<AreaName> areaNames = new ArrayList<AreaName>();

			if (!plannerList.isEmpty()) {
				plannerInfo = adminHunService.plannerInfo(plannerList, pInf);
				areaNames = adminHunService.plannerArea(plannerList);

				for (PlannerCard infList : plannerInfo) {
					for (AreaName areaList : areaNames) {
						if (infList.getPlannerNo() == areaList.getPlannerNo()) {
							infList.setareaNames(areaNames);
						}
					}
				}

			}

			if (result > 0) {
				model.addAttribute("msg", "회원 삭제에 성공하셨습니다.");
				model.addAttribute("pInfom", pInf);
				return "admin/memberDetail";
			} else {
				return "admin/memberDetail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원 강퇴중 오류 발생");
			return "common/errorPage";
		}

	}

	/**
	 * 플래너 전체 리스트 조회용 controller
	 * 
	 * @param model
	 * @param request
	 * @param currentPage
	 * @return
	 */
	@RequestMapping("plannerList")
	public String plannerList(Model model, HttpServletRequest request, Integer currentPage) {
		String beforUrl = request.getHeader("referer");

		try {
			int plannerCount = adminHunService.plannerCount();
			List<LargeArea> largeNmList = adminHunService.selectLargeNmList();
			List<SmallArea> smallNmList = adminHunService.selectsmallNmList();
			model.addAttribute("largeNmList", largeNmList);
			model.addAttribute("smallNmList", smallNmList);

			if (currentPage == null) {
				currentPage = 1;
			}
			PageInfo pInf = Pagination.getPageInfo(10, 10, currentPage, plannerCount);

			List<PlannerInfo> totalList = adminHunService.plannerTotal(pInf);
			List<AreaName> totalAList = adminHunService.totalAList();

			for (PlannerInfo infList : totalList) {
				for (AreaName infoarea : totalAList) {
					if (infList.getPlannerNo() == infoarea.getPlannerNo()) {
						infList.setAreaNames(totalAList);
					}
				}
			}

			List<Day> dayList = adminHunService.dayList();
			Date countDay = null;
			for (PlannerInfo infList : totalList) {
				for (Day infodays : dayList) {
					if (infList.getPlannerNo() == infodays.getPlannerNo()) {
						countDay = new Date(
								infList.getPlannerStartDT().getTime() + infodays.getTripDate() * (24 * 60 * 60 * 1000));
						infList.setPlannerEndDate(countDay);
					}
				}
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

	/**
	 * 플래너 검색용 controller
	 * 
	 * @param model
	 * @param request
	 * @param currentPage
	 * @param searchKey
	 * @param searchValue
	 * @param searchGroup
	 * @param searchArea
	 * @param searchLocal
	 * @param deleted
	 * @param startTrip
	 * @param endTrip
	 * @return
	 */
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
			List<LargeArea> largeNmList = adminHunService.selectLargeNmList();
			List<SmallArea> smallNmList = adminHunService.selectsmallNmList();
			model.addAttribute("largeNmList", largeNmList);
			model.addAttribute("smallNmList", smallNmList);

			
			
			List<PlannerInfo> searchList = adminHunService.searchList(keyword);// 지역이외 검색 후 검색된 플래너리스트
			List<AreaName> areaInfo = adminHunService.areaInfo(keyword);
			if (currentPage == null) {
				currentPage = 1;
			}
			PageInfo pInf = Pagination.getPageInfo(10, 10, currentPage, searchList.size());
			
			List<PlannerInfo> plannerInfo = adminHunService.plannerInfo( keyword,pInf);

			if (plannerInfo.isEmpty()) {// 지역이외 검색 결과가 없을경우
				model.addAttribute("plannerList", null);
				
			} else {// 지역이외 검색결과 있을경우
				if (!areaInfo.isEmpty()) {// 지역검색결과 있을경우 
					for (PlannerInfo infList : plannerInfo) {
						for (AreaName infoarea : areaInfo) {
							if (infList.getPlannerNo() == infoarea.getPlannerNo()) {
								infList.setAreaNames(areaInfo);
							}
						}
					}
					List<Day> dayList = adminHunService.dayList();
					Date countDay = null;
					for (PlannerInfo infList : plannerInfo) {
						for (Day infodays : dayList) {
							if (infList.getPlannerNo() == infodays.getPlannerNo()) {
								countDay = new Date(infList.getPlannerStartDT().getTime()
										+ infodays.getTripDate() * (24 * 60 * 60 * 1000));
								infList.setPlannerEndDate(countDay);
							}
						}
					}
					model.addAttribute("plannerList", plannerInfo);
				}else {//지역이외 검색결과 있고 , 지역검색결과가 없는경우 
					model.addAttribute("plannerList", null);
				}
			}

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
			model.addAttribute("msg", "플래너 상세조회 실패 .");
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
			model.addAttribute("msg", "플래너 복구 중 오류발생 .");
			return "redirect:" + beforUrl;
		}

	}

}
