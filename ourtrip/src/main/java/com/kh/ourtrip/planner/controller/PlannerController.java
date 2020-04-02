package com.kh.ourtrip.planner.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
import com.kh.ourtrip.planner.model.service.PlannerService;
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
@SessionAttributes({ "msg", "loginMember" })
@RequestMapping("/planner/*")
public class PlannerController {

	@Autowired
	private PlannerService plannerService;

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
		
		
		if(planner.getPlannerPublicYN() == null){
			planner.setPlannerPublicYN("Y");
		}
		if (planner.getPlannerPublicYN().equals("on")) {
			planner.setPlannerPublicYN("N");
		} 

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		String url = strToday;
		for (int i = 1; i < 8; i++) {
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
				int nextDateNo = plannerService.getNextDateNo();
				int nextScheNo = plannerService.getNextScheduleNo();
				Day day = new Day();
				day.setDateNo(nextDateNo);
				day.setPlannerNo(plannerNo);
				day.setTripDate(0);
				Schedule schedule = new Schedule();
				schedule.setScheduleNo(nextScheNo);
				schedule.setDateNo(nextDateNo);
				schedule.setScheduleCost(0);
				schedule.setScheduleLat(0);
				schedule.setScheduleLng(0);
				schedule.setScheduleLocationNM("미정");
				schedule.setScheduleMemo("");
				schedule.setScheduleTime("0000");
				schedule.setScheduleTitle("제목없음");
				result *= plannerService.insertDate(day);
				result *= plannerService.insertSchedule(schedule);
				
				if (result != 0) {
					model.addAttribute("플래너 생성이 완료되었습니다");
					return "redirect:myPlanner";
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
	@RequestMapping("editplanner")
	public String chattingForm(Model model, String no, RedirectAttributes rdAttr) {
		
		JSONObject jsonObj = null;
		JSONParser jsonParser = new JSONParser();
		JSONArray chatArray = new JSONArray();
		JSONArray joinUserArray = new JSONArray();
		JSONArray locationArray = new JSONArray();
		JSONArray groupArray = new JSONArray();
		Planner selectedPlanner = null;
		List<LargeArea> largeNmList = null;
		List<SmallArea> smallNmList = null;
		List<AreaName> areaNameList = null;
		
		System.out.println("editplanner");
		try {
			
			selectedPlanner = plannerService.selectPlannerView(no);
			int plannerNo = selectedPlanner.getPlannerNo();
			jsonObj = (JSONObject) jsonParser.parse(selectedPlanner.toJsonString());
			
			// 채팅내역 얻어와서 jsonString으로 변환
			List<ChattingLogView> chatList = null;
			chatList = plannerService.selectChatList(plannerNo);
			for(ChattingLogView cl : chatList) {
				jsonObj = (JSONObject) jsonParser.parse(cl.toJsonString());
				chatArray.add(jsonObj);
			}
			
			// 참여중인 유저 목록과 권한 전달
			List<PlannerMemberView> pmList = plannerService.selectPlannerMemeberListUsePlannerNo(plannerNo);
			
			for(PlannerMemberView tpm : pmList) {
				jsonObj = (JSONObject) jsonParser.parse(tpm.toJsonString());
				joinUserArray.add(jsonObj);
			}
			
			largeNmList = plannerService.selectLargeNmList();
			smallNmList = plannerService.selectsmallNmList();
			
			areaNameList = plannerService.selectPlannerLocationName(plannerNo);
			System.out.println("areaNameList : " + areaNameList);
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "플래너 수정화면 로드중 오류발생");
			return "common/errorPage";
		}

		model.addAttribute("largeNmList", largeNmList);
		model.addAttribute("smallNmList", smallNmList);
		model.addAttribute("plannerInfo", selectedPlanner.toJsonString());
		model.addAttribute("plannerTitle", selectedPlanner.getPlannerTitle());
		model.addAttribute("chatList", chatArray);
		model.addAttribute("joinUserArray",joinUserArray);
		model.addAttribute("areaNameList",areaNameList);
		System.out.println("editplannerend");
		
		return "planner/editPlanner";
	}
	
	@RequestMapping("plannerDetail")
	public String mobileView(Model model, String no, RedirectAttributes rdAttr, String userId) {
		
		JSONObject jsonObj = null;
		JSONParser jsonParser = new JSONParser();
		JSONArray locationArray = new JSONArray();
		JSONArray tempArray = new JSONArray();
		Planner selectedPlanner = null;
		System.out.println("mobileView");
		try {
			
			selectedPlanner = plannerService.selectPlannerView(no);
			
			List<Day> dList = selectedPlanner.getDays();
			
			for(int i = 0; i < dList.size(); i++) {
				Day td = dList.get(i);
				tempArray = new JSONArray();
				for(int j = 0; j < td.getSchedules().size(); j++) {
					Schedule ts = td.getSchedules().get(j);
					String scheduleLocation = "{";
					scheduleLocation += "\"sno\":\"" + ts.getScheduleNo() ;
					scheduleLocation += "\",\"location\":\"" + ts.getScheduleLocationNM() ;
					scheduleLocation += "\",\"lat\":\"" + ts.getScheduleLat() ;
					scheduleLocation += "\",\"lng\":\"" + ts.getScheduleLng() ;
					scheduleLocation += "\"}";
					jsonObj = (JSONObject) jsonParser.parse(scheduleLocation);
					tempArray.add(jsonObj);
				}
				jsonObj = (JSONObject) jsonParser.parse("{\"dno\":\"" + td.getDateNo() + "\"}");
				jsonObj.put("schedules", tempArray);
				locationArray.add(jsonObj);
			}
			
			List<Day> days = selectedPlanner.getDays();
			
			for(int i = days.size()-1; i > 0; i--) {
				for(int j = 0; j < i; j++) {
					if(days.get(j).getTripDate() > days.get(j+1).getTripDate()) {
						Day temp = days.get(j);
						days.set(j, days.get(j+1));
						days.set(j+1, temp);
					}
				}
			}
			
			selectedPlanner.setDays(days);
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "플래너 조회중 오류발생");
			return "common/errorPage";
		}
		System.out.println(selectedPlanner.getDays());
		
		model.addAttribute("plannerInfoJson", selectedPlanner.toJsonString());
		model.addAttribute("plannerInfo", selectedPlanner);
		model.addAttribute("locationArray", locationArray);
		System.out.println("mobileViewEnd");
		
		return "planner/plannerDetail";
	}
	
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
	
	@RequestMapping("guide")
	public String plannerGuide() {
		return "planner/plannerGuide";
	}
}
