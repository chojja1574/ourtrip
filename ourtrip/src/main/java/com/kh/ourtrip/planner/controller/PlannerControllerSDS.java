package com.kh.ourtrip.planner.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import com.kh.ourtrip.planner.model.vo.ChattingLogView;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.PlannerMemberView;
import com.kh.ourtrip.planner.model.vo.PlannerView;
import com.kh.ourtrip.planner.model.vo.Schedule;
import com.kh.ourtrip.planner.model.vo.SmallArea;

@Controller
@SessionAttributes({"loginMember","msg","profilePath", "detailUrl"})
@RequestMapping("/planner/*") // 테스트를 위한 임시 변경
public class PlannerControllerSDS {
	
	@Autowired
	public PlannerServiceSDS plannerService;
	
	@RequestMapping("search")
	public String searchForm(Model model) {
		try {
			// 추천리스트 조회
			List<PlannerCard> recommendPCList = plannerService.selectRecommendPCList();
			
			// 화면에 보여줄 대지역, 중소지역 리스트 조회
			List<LargeArea> largeNmList = plannerService.selectLargeNmList();
			List<SmallArea> smallNmList = plannerService.selectsmallNmList();
			
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
			int listCount = plannerService.getListCount(map);
			//System.out.println("컨트롤러 listCount : " + listCount);
			// 현재 페이지 확인
			if(currentPage == null) currentPage = 1;
			
			// 페이지 정보 저장
			PageInfo pInf = Pagination.
					getPageInfo(4, 5, currentPage, listCount);
			
			// 플래너 목록 조회
			List<PlannerCard> pList = plannerService.selectPList(map, pInf);
			
			// 검색된 조건이 없으면 널전달
			if(pList==null) {
				return null;
			} else {
				
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
	
	// 플래너 삭제
	@RequestMapping("delPlanner")
	public String delPlanner(PlannerMember delPlanner, Model model, RedirectAttributes rdAttr) {
		int memberNo = ((Member)model.getAttribute("loginMember")).getMemberNo();
		delPlanner.setMemberNo(memberNo);
		
		String msg = null;
		try {
			int result = plannerService.delPlanner(delPlanner);
			
			if(result > 0) msg = "플래너가 삭제되었습니다.";
			else msg = "플래너 삭제에 실패하였습니다.";
			
			rdAttr.addFlashAttribute("msg", msg);
			
			return "redirect:/planner/myPlanner";
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "플래너 삭제 과정 중 오류 발생");
			return "common/errorPage";
		}
	}
	
	// 플래너 나가기
	@RequestMapping("outPlanner")
	public String outPlanner(PlannerMember outPlanner, Model model, RedirectAttributes rdAttr) {
		int memberNo = ((Member)model.getAttribute("loginMember")).getMemberNo();
		outPlanner.setMemberNo(memberNo);
		
		String msg = null;
		
		try {
			int result = plannerService.outPlanner(outPlanner);
			
			if(result > 0) msg = "플래너에서 나가기되었습니다.";
			else msg = "플래너 나가기에 실패하였습니다.";
			
			rdAttr.addFlashAttribute("msg", msg);
			
			return "redirect:/planner/myPlanner";
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "플래너 나가기 과정 중 오류 발생");
			return "common/errorPage";
		}
		
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////
	// 필요한건지?
	@RequestMapping("testInput")
	public String chattingRoom() {
		return "planner/chattingRoom";
	}
	
	// 플래너 수정
	@RequestMapping("editplanner")
	public String chattingForm(Model model, Integer no, RedirectAttributes rdAttr) {
		
		JSONObject jsonObj = null;
		JSONParser jsonParser = new JSONParser();
		JSONArray chatArray = new JSONArray();
		JSONArray joinUserArray = new JSONArray();
		Planner selectedPlanner = null;
		try {
			
			// PlannerView에 데이터 통째로 얻어와서 분리작업
			boolean inputPvVal = true; 
			int plannerNo = -1;
			String plannerTitle = null;
			String plannerPwd = null;
			int plannerCost = -1;
			Date plannerCreateDT = null;
			Date plannerModifyDT = null;
			Date plannerStartDT = null;
			String plannerPublicYN = null;
			String plannerDeleteYN = null;
			String plannerExpiry = null;
			int plannerCount = -1;
			String plannerUrl = null;
			int groupCode = -1;
			
			List<PlannerView> selectPlannerView = plannerService.selectPlannerView(no);
			Map<Integer,List<PlannerView>> dateMap = new HashMap<Integer,List<PlannerView>>();
			for(PlannerView pv : selectPlannerView) {
				if(inputPvVal) {
					plannerTitle = pv.getPlannerTitle();
					plannerPwd = pv.getPlannerPwd();
					plannerCost = pv.getPlannerCost();
					plannerCreateDT = pv.getPlannerCreateDT();
					plannerModifyDT = pv.getPlannerModifyDT();
					plannerStartDT = pv.getPlannerStartDT();
					plannerPublicYN = pv.getPlannerPublicYN();
					plannerDeleteYN = pv.getPlannerDeleteYN();
					plannerExpiry = pv.getPlannerExpiry();
					plannerCount = pv.getPlannerCount();
					plannerUrl = pv.getPlannerUrl();
					groupCode = pv.getGroupCode();
				}
				
				if(dateMap.containsKey(pv.getDateNo())) {
					dateMap.get(pv.getDateNo()).add(pv);
				}else {
					List<PlannerView> pvList = new ArrayList<PlannerView>();
					pvList.add(pv);
					dateMap.put(pv.getDateNo(), pvList);
				}
			}
			List<Day> dayList = new ArrayList<Day>();
			Iterator iter = dateMap.entrySet().iterator();
			while (iter.hasNext()) {
				int dateNo = -1;
				int tripDate = -1;
				List<Schedule> scheduleList = new ArrayList<Schedule>();
			    Entry entry = (Entry) iter.next();
			    for(PlannerView pv : (List<PlannerView>)(entry.getValue())) {
			    	if(dateNo == -1) {
			    		dateNo = pv.getDateNo();
			    	}
			    	if(tripDate == -1) {
			    		tripDate = pv.getTripDate();
			    	}
					if(plannerNo == -1) {
						plannerNo = pv.getPlannerNo();
					}
			    	Schedule schedule = new Schedule(
			    			pv.getScheduleNo(), pv.getScheduleTitle(), pv.getScheduleCost(),
			    			pv.getScheduleTime(), pv.getScheduleMemo(), pv.getScheduleLocationNM(),
			    			pv.getScheduleLat(),pv.getScheduleLng(),pv.getDateNo());
			    	scheduleList.add(schedule);
			    }
			    Day oneDay = new Day(dateNo,tripDate,no,scheduleList);
			    dayList.add(oneDay);
			    
			}
			selectedPlanner = new Planner(no, plannerTitle, plannerPwd, plannerCost, 
					plannerCreateDT, plannerModifyDT, plannerStartDT, plannerPublicYN, plannerDeleteYN, 
					plannerExpiry, plannerCount, plannerUrl, groupCode, dayList);
			jsonObj = (JSONObject) jsonParser.parse(selectedPlanner.toJsonString());
			
			// 채팅내역 얻어와서 jsonString으로 변환
			List<ChattingLogView> chatList = null;
			chatList = plannerService.selectChatList(no);
			for(ChattingLogView cl : chatList) {
				jsonObj = (JSONObject) jsonParser.parse(cl.toJsonString());
				chatArray.add(jsonObj);
			}
			
			// 참여중인 유저 목록과 권한 전달
			List<PlannerMemberView> pmList = plannerService.selectPlannerMemeberListUsePlannerNo(no);
			
			for(PlannerMemberView tpm : pmList) {
				jsonObj = (JSONObject) jsonParser.parse(tpm.toJsonString());
				joinUserArray.add(jsonObj);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("plannerInfo", selectedPlanner.toJsonString());
		model.addAttribute("plannerTitle", selectedPlanner.getPlannerTitle());
		model.addAttribute("chatList", chatArray);
		model.addAttribute("joinUserArray",joinUserArray);
		
		return "planner/editPlanner";
	}
	
	// 플래너 상세보기
	@RequestMapping("plannerDetail")
	public String mobileView(Model model, Integer no, RedirectAttributes rdAttr, String userId) {
		
		JSONObject jsonObj = null;
		JSONParser jsonParser = new JSONParser();
		JSONArray locationArray = new JSONArray();
		JSONArray tempArray = new JSONArray();
		Planner selectedPlanner = null;
		try {
			
			// PlannerView에 데이터 통째로 얻어와서 분리작업
			boolean inputPvVal = true; 
			int plannerNo = -1;
			String plannerTitle = null;
			String plannerPwd = null;
			int plannerCost = -1;
			Date plannerCreateDT = null;
			Date plannerModifyDT = null;
			Date plannerStartDT = null;
			String plannerPublicYN = null;
			String plannerDeleteYN = null;
			String plannerExpiry = null;
			int plannerCount = -1;
			String plannerUrl = null;
			int groupCode = -1;
			
			List<PlannerView> selectPlannerView = plannerService.selectPlannerView(no);
			Map<Integer,List<PlannerView>> dateMap = new HashMap<Integer,List<PlannerView>>();
			for(PlannerView pv : selectPlannerView) {
				if(inputPvVal) {
					plannerTitle = pv.getPlannerTitle();
					plannerPwd = pv.getPlannerPwd();
					plannerCost = pv.getPlannerCost();
					plannerCreateDT = pv.getPlannerCreateDT();
					plannerModifyDT = pv.getPlannerModifyDT();
					plannerStartDT = pv.getPlannerStartDT();
					plannerPublicYN = pv.getPlannerPublicYN();
					plannerDeleteYN = pv.getPlannerDeleteYN();
					plannerExpiry = pv.getPlannerExpiry();
					plannerCount = pv.getPlannerCount();
					plannerUrl = pv.getPlannerUrl();
					groupCode = pv.getGroupCode();
					inputPvVal = false;
				}

				if(dateMap.containsKey(pv.getDateNo())) {
					dateMap.get(pv.getDateNo()).add(pv);
				}else {
					List<PlannerView> pvList = new ArrayList<PlannerView>();
					pvList.add(pv);
					dateMap.put(pv.getDateNo(), pvList);
				}
			}
			List<Day> dayList = new ArrayList<Day>();
			Iterator iter = dateMap.entrySet().iterator();
			while (iter.hasNext()) {
				int dateNo = -1;
				int tripDate = -1;				
				
				tempArray = new JSONArray();
				
				List<Schedule> scheduleList = new ArrayList<Schedule>();
			    Entry entry = (Entry) iter.next();
			    for(PlannerView pv : (List<PlannerView>)(entry.getValue())) {
			    	if(dateNo == -1) {
			    		dateNo = pv.getDateNo();
			    	}
			    	if(tripDate == -1) {
			    		tripDate = pv.getTripDate();
			    	}
					if(plannerNo == -1) {
						plannerNo = pv.getPlannerNo();
					}
			    	Schedule schedule = new Schedule(
			    			pv.getScheduleNo(), pv.getScheduleTitle(), pv.getScheduleCost(),
			    			pv.getScheduleTime(), pv.getScheduleMemo(), pv.getScheduleLocationNM(),
			    			pv.getScheduleLat(),pv.getScheduleLng(),pv.getDateNo());
			    	scheduleList.add(schedule);
			    	String scheduleLocation = "{";
					scheduleLocation += "\"sno\":\"" + pv.getScheduleNo() ;
					scheduleLocation += "\",\"location\":\"" + pv.getScheduleLocationNM() ;
					scheduleLocation += "\",\"lat\":\"" + pv.getScheduleLat() ;
					scheduleLocation += "\",\"lng\":\"" + pv.getScheduleLng() ;
					scheduleLocation += "\"}";
					jsonObj = (JSONObject) jsonParser.parse(scheduleLocation);
					tempArray.add(jsonObj);
			    }
			    Day oneDay = new Day(dateNo,tripDate,no,scheduleList);
			    dayList.add(oneDay);
	
			    jsonObj = (JSONObject) jsonParser.parse("{\"dno\":\"" + dateNo + "\"}");
			    jsonObj.put("schedules", tempArray);
			    locationArray.add(jsonObj);
			    
			}
			selectedPlanner = new Planner(no, plannerTitle, plannerPwd, plannerCost, 
					plannerCreateDT, plannerModifyDT, plannerStartDT, plannerPublicYN, plannerDeleteYN, 
					plannerExpiry, plannerCount, plannerUrl, groupCode, dayList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("plannerInfoJson", selectedPlanner.toJsonString());
		model.addAttribute("plannerInfo", selectedPlanner);
		model.addAttribute("locationArray", locationArray);
		
		return "planner/plannerDetail";
	}
	
	@RequestMapping("plannerCopy")
	public String plannerCopy(int no, Model model, RedirectAttributes rdAttr,
							HttpServletRequest request) {
		
		try {
			int result = plannerService.plannerCopy(no, ((Member)model.getAttribute("loginMember")).getMemberNo());
			
			String msg = null;
			
			if(result > 0) msg = "플래너 복사가 완료되었습니다.";
			else msg = "플래너 복사에 실패했습니다.";
			
			rdAttr.addFlashAttribute("msg", msg);
			
			String beforeUrl = (String)request.getHeader("referer");
			
			if(beforeUrl == null) {
				return "redirect:/";
			}else {
				return "redirect:" + beforeUrl;
			}

			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "플래너 복사중 오류 발생");
			return "common/errorPage";
		}
		
	}
	
	
}

