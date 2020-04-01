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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ourtrip.admin.model.service.AdminHunService;
import com.kh.ourtrip.admin.model.vo.PlannerDeleteReason;
import com.kh.ourtrip.common.Pagination;
import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.member.model.vo.ProfileImage;
import com.kh.ourtrip.planner.model.service.PlannerServiceSDS;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerInfo;
import com.kh.ourtrip.planner.model.vo.SmallArea;

@SessionAttributes({ "memberList", "msg" })
@Controller
@RequestMapping("/admin1/*")
public class AdminHunController {

	@Autowired
	private AdminHunService adminHunService;

	@Autowired
	private PlannerServiceSDS plannerService;
	
	
	/**
	 * 전체회원 조회용 Controller
	 * @param model
	 * @param currentPage
	 * @return path
	 */
	@RequestMapping("list")
	public String memberFullList(Model model,
			@RequestParam(value="currentPage", required=false)
			Integer currentPage,
			@RequestParam(value="searchKey", required=false)
			String searchKey,
			@RequestParam(value="searchValue", required=false)
			String searchValue) {
		
		try {

			Map<String, Object> map = null;
			if(searchKey != null && searchValue != null) {
				map = new HashMap<String, Object>();
				map.put("searchKey", searchKey);
				map.put("searchValue", searchValue);
			}
			
			int listCount = adminHunService.getListCount(map);
			
			//System.out.println("회원 리스트수 : " + listCount);
			
			if (currentPage == null) {
				currentPage = 1;
			}
			
			//System.out.println("map 확인 : " + map);
			PageInfo pInf = Pagination.getPageInfo(10, 10, currentPage, listCount);
			
			// 전체 + 검색 회원 목록 조회
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
	 * 회원 플래너까지
	 * @param model
	 * @param no
	 * @param currentPage
	 * @param request
	 * @return
	 */
	@RequestMapping("detail")
	public String memberDetail(Model model, int no, HttpServletRequest request) {

		String beforeUrl = request.getHeader("referer"); // 이전페이지 주소

		try {
			Member detailMem = adminHunService.detail(no);
			ProfileImage pi = adminHunService.selectProfileImage(no);
			List<PlannerCard> plannerList = adminHunService.plannerList(no);
			List<Integer> plannerNo = new ArrayList<Integer>();
			
			for(PlannerCard item : plannerList) {
				plannerNo.add(item.getPlannerNo());
			}
			
			List<AreaName> aList = null;
			
			if(!plannerNo.isEmpty()) {
				
				aList = plannerService.selectAreaNames(plannerNo);
			
			
				for(AreaName name : aList) {
					
					for(PlannerCard item : plannerList) {
						if(item.getPlannerNo() == name.getPlannerNo()) {
							if(item.getareaNames() == null) {
								List<AreaName> temp = new ArrayList<AreaName>();
								temp.add(name);
								item.setareaNames(temp);
							} else {
								item.getareaNames().add(name);
							}
							
							break;
						}
							
					}
				}
			}
			if (detailMem != null) {
				model.addAttribute("plannerList", plannerList);
				model.addAttribute("detailMember", detailMem);
				model.addAttribute("pi", pi);
				model.addAttribute("beforeUrl", beforeUrl);
				return "admin/memberDetail";
			} else {
				model.addAttribute("msg", "회원정보 상세조회 실패 .");
				return "redirect:" + beforeUrl;
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원 상세정보 조회중 오류 발생");
			return "common/errorPage";
		}

	}

	/**
	 * 회원삭제용 삭제용 controller
	 * 
	 * @param memberNo
	 * @param email
	 * @param delBecause
	 * @param model
	 * @param currentPage
	 * @return
	 */
	@RequestMapping("memberDelete")
	public String memberDelete(int memberNo, String email, String delBecause, Model model,
								RedirectAttributes rdattr) {

		try {
			int result = adminHunService.memberDelete(memberNo, email, delBecause);

			if(result > 0) 	rdattr.addFlashAttribute("msg", "회원 삭제에 성공하셨습니다.");
			else rdattr.addFlashAttribute("msg", "회원 삭제에 실패하였습니다.");
			return "redirect:list";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원 삭제 중 오류 발생");
			return "common/errorPage";
		}

	}
	
	/** 회원복구용 Controller
	 * @param memberNo
	 * @param model
	 * @param rdattr
	 * @return result
	 */
	@RequestMapping("memberRecovery")
	public String memberRecovery(int memberNo, Model model,
								RedirectAttributes rdattr) {
		try {
			
			int result = adminHunService.memberRecovery(memberNo);
			if(result>0) rdattr.addFlashAttribute("msg", "회원 복구 성공!");
			else rdattr.addFlashAttribute("msg", "회원복구중 실패");
			
			return "redirect:list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원 복구 중 오류 발생");
			return "common/errorPage";
		}
	}

	///////////////////////////////////////////회원목록끝/////////////////////////////////////////////
	
	/**
	 * 플래너 전체 리스트 조회용 controller
	 * 
	 * @param model
	 * @param request
	 * @param currentPage
	 * @return
	 */
	
	@RequestMapping("plannerList")
	public String plannerList(Model model, Integer currentPage) {

		try {
			int plannerCount = adminHunService.plannerCount();
			List<LargeArea> largeNmList = adminHunService.selectLargeNmList();
			List<SmallArea> smallNmList = adminHunService.selectsmallNmList();
			model.addAttribute("largeNmList", largeNmList);
			model.addAttribute("smallNmList", smallNmList);

			if (currentPage == null) {
				currentPage = 1;
			}
			PageInfo pInf = Pagination.getPageInfo(10, 5, currentPage, plannerCount);

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
			return "common/errorPage";
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
	public String searchadminPlanner(Model model, Integer currentPage,
			@RequestParam(value = "searchKey", required = false) String searchKey,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "searchGroup", required = false) List<Integer> searchGroup,
			@RequestParam(value = "largeArea", required = false) String largeArea,
			@RequestParam(value = "smallArea", required = false) String smallArea,
			@RequestParam(value = "deleted", required = false) String deleted,
			@RequestParam(value = "startTrip", required = false) String startTrip,
			@RequestParam(value = "endTrip", required = false) String endTrip) {

		Map<String, Object> keyword = new HashMap<String, Object>();
		keyword.put("searchKey", searchKey);
		keyword.put("searchValue", searchValue);
		keyword.put("searchGroup", searchGroup);
		keyword.put("largeArea", largeArea);
		keyword.put("smallArea", smallArea);
		keyword.put("deleted", deleted);
		keyword.put("startTrip", startTrip);
		keyword.put("endTrip", endTrip);
		
		
		model.addAttribute("searchGroup", searchGroup);
		model.addAttribute("param", keyword);

		try {
			List<LargeArea> largeNmList = adminHunService.selectLargeNmList();
			List<SmallArea> smallNmList = adminHunService.selectsmallNmList();
			model.addAttribute("largeNmList", largeNmList);
			model.addAttribute("smallNmList", smallNmList);
			
			List<Integer> pNoList = adminHunService.getPlannerListCount(keyword);
			
			keyword.put("pNoList", pNoList);
			
			if (currentPage == null) {
				currentPage = 1;
			}
			PageInfo pInf = Pagination.getPageInfo(10, 10, currentPage, pNoList.size());
			
			List<PlannerCard> pInfoList = new ArrayList<PlannerCard>();
			if(!pNoList.isEmpty()) {
				
				pInfoList = adminHunService.selectPlannerList(keyword, pInf);
				
				// endDate 계산
				for (PlannerCard plannerInfo : pInfoList) {
					plannerInfo.setPlannerEndDT(new Date(plannerInfo.getPlannerStartDT().getTime()
							+ plannerInfo.getTripDate() * (24 * 60 * 60 * 1000)));
				}
			}
			model.addAttribute("pInf", pInf);
			model.addAttribute("plannerList", pInfoList);

			return "admin/adminplannerList";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "플래너 검색 실패");
			return "common/errorPage";
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

			model.addAttribute("beforeUrl", beforUrl);
			model.addAttribute("plannerinfo", plannerInfo);
			model.addAttribute("plannerArea", plannerArea);
			return "admin/adminplannerDetail";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "플래너 상세조회 실패 .");
			return "common/errorPage";
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
