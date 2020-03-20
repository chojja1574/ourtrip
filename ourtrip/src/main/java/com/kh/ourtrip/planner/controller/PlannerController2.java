package com.kh.ourtrip.planner.controller;

import java.sql.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ourtrip.planner.model.service.PlannerService2;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerView;
import com.kh.ourtrip.planner.model.vo.Schedule;

@Controller
@RequestMapping("/planner1/*")
public class PlannerController2 {
	
	@Autowired
	PlannerService2 plannerService2;
	
	@RequestMapping("testInput")
	public String chattingRoom() {
		return "planner/chattingRoom";
	}
	
	@RequestMapping("editplanner")
	public String chattingForm(Model model, Integer no, RedirectAttributes rdAttr, String userId, String selectRoom) {
		
		JSONObject jsonObj = null;
		JSONParser jsonParser = new JSONParser();
		Planner selectedPlanner = null;
		System.out.println("editplanner");
		try {
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
			
			List<PlannerView> selectPlannerView = plannerService2.selectPlannerView(no);
			
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
			    			pv.getScheduleNo(), pv.getPlannerTitle(), pv.getScheduleCost(),
			    			pv.getScheduleTime(), pv.getScheduleMemo(), pv.getScheduleLocationNM(),
			    			pv.getScheduleLat(),pv.getScheduleLng(),pv.getDateNo());
			    	scheduleList.add(schedule);
			    	System.out.println("pvpvpv");
			    }
			   
			    Day oneDay = new Day(dateNo,tripDate,no,scheduleList);
			    dayList.add(oneDay);
			    System.out.println("key : " + entry.getKey() + " / value : " + entry.getValue());
			    
			}
			selectedPlanner = new Planner(no, plannerTitle, plannerPwd, plannerCost, 
					plannerCreateDT, plannerModifyDT, plannerStartDT, plannerPublicYN, plannerDeleteYN, 
					plannerExpiry, plannerCount, plannerUrl, groupCode, dayList);
			//selectedPlanner.toString()
			jsonObj = (JSONObject) jsonParser.parse(selectedPlanner.toString());
			System.out.println("json : " + selectedPlanner.toString());
			
		}catch(Exception e) {
			
		}
		model.addAttribute("userId", userId);
		model.addAttribute("selectRoom", selectRoom);
		model.addAttribute("plannerInfo", selectedPlanner.toJsonString());
		model.addAttribute("plannerTitle", selectedPlanner.getPlannerTitle());
		System.out.println("editplannerend");
		
		return "planner/editPlanner";
	}
	
	@RequestMapping("chattingForm2")
	public String chattingForm2(Model model, Integer no, RedirectAttributes rdAttr, String userId, String selectRoom) {
		model.addAttribute("userId", userId);
		model.addAttribute("selectRoom", selectRoom);
		
		return "websocket-echo";
	}
}
