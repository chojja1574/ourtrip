package com.kh.ourtrip.planner.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ourtrip.planner.model.dao.PlannerDAOSDS;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.PlannerCard;

@Service
public class PlannerServiceImplSDS implements PlannerServiceSDS{

	@Autowired
	public PlannerDAOSDS plannerDAOSDS;
	
	@Override
	public List<PlannerCard> selectRecommendPCList() throws Exception {
		List<PlannerCard> recommendPCList = plannerDAOSDS.selectRecommendPCList();
		System.out.println("service rList : " + recommendPCList);
		List<Integer> rListNo = new ArrayList<Integer>();
		if(!recommendPCList.isEmpty()) {
			for(PlannerCard card : recommendPCList) {
				rListNo.add(card.getPlannerNo());
				System.out.println(card);
			}
			List<AreaName> aList = plannerDAOSDS.selectAreaNames(rListNo);
			for(AreaName list : aList) {
				
				System.out.println(list);
			}
			
			for(PlannerCard card : recommendPCList) {
				
				List<AreaName> areaNames = new ArrayList<AreaName>();
				
				for(AreaName list : aList) {
					if(card.getPlannerNo() == list.getPlannerNo()) {
						areaNames.add(list);
					}
				}
				
				card.setareaNames(areaNames);
				System.out.println("잘들어갔남? : " + card.getareaNames());
			}
			
		}
		return recommendPCList;
	}

}
