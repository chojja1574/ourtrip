package com.kh.ourtrip.planner.model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ourtrip.planner.model.dao.PlannerDAOSDS;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.PlannerCard;

@Service
public class PlannerServiceImplSDS implements PlannerServiceSDS{

	@Autowired
	public PlannerDAOSDS plannerDAOSDS;
	
	
	/** 추천플래너카드 조회
	 * @return recommendPCList
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> selectRecommendPCList() throws Exception {
		
		// 추천플래너카드 리스트 조회
		List<PlannerCard> recommendPCList = plannerDAOSDS.selectRecommendPCList();
		//System.out.println("service rList : " + recommendPCList);
		
		// 추천플래너 카드 번호를 담을 리스트
		List<Integer> rListNo = new ArrayList<Integer>();
		if(!recommendPCList.isEmpty()) {
			// 추천리스트에서 번호 가져와서 번호 리스트에 담음
			for(PlannerCard card : recommendPCList) {
				rListNo.add(card.getPlannerNo());
				//System.out.println(card);
			}
			// 지역리스트 호출
			List<AreaName> aList = plannerDAOSDS.selectAreaNames(rListNo);
//			for(AreaName list : aList) {
//				
//				//System.out.println(list);
//			}
			
			// 추천리스트에 지역리스트를 담음
			for(PlannerCard card : recommendPCList) {
				
				List<AreaName> areaNames = new ArrayList<AreaName>();
				
				for(AreaName list : aList) {
					if(card.getPlannerNo() == list.getPlannerNo()) {
						areaNames.add(list);
					}
				}
				
				card.setareaNames(areaNames);
				//System.out.println("잘들어갔남? : " + card.getareaNames());
			}
			
		}
		return recommendPCList;
	}

	/** 전체 플래너 수 + 검색 조회
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	@Override
	public int getListCount(Map<String, Object> map) throws Exception {
		
		// 플래너 리스트(plannerTitle + groupName)
		List<Integer> pListNo = plannerDAOSDS.getPListNo(map);
		//System.out.println("서비스단 검색된 플래너 리스트 번호: " + pListNo);
		
		if(!pListNo.isEmpty()) {
			map.put("pListNo", pListNo);
			//System.out.println("map확인 : " + map);
			// 지역명 일치하는 플래너 번호 조회
			List<AreaName> aList = plannerDAOSDS.getAList(map);
			//System.out.println("서비스단 검색된 플래너 지역 정보: " + aList);
//			Set<Integer> deleteList = new HashSet<Integer>();
//			if(map.get("viaCheck")==null) {
//				for(AreaName item : aList) {
//					if(!item.getLargeAreaName().equals(map.get("largeArea"))) {
//						deleteList.add(item.getPlannerNo());
//					}
//					if(!item.getSmallAreaName().equals(map.get("smallArea"))) {
//						deleteList.add(item.getPlannerNo());
//					}
//				}
//			}
		}
		return 0;
				
	}

	
	
}
