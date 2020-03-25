package com.kh.ourtrip.planner.model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
		
		int result = 0;
		// 플래너 리스트(plannerTitle + groupName)
		List<Integer> pListNo = plannerDAOSDS.getPListNo(map);
		//System.out.println("서비스단 검색된 플래너 리스트 번호: " + pListNo);
		
		
		if(!pListNo.isEmpty()) {
			map.put("pListNo", pListNo);
			System.out.println("map확인 : " + map);
			// 검색된 플래너번호에 맞는 지역명 가져오기
			List<AreaName> aList = plannerDAOSDS.getAList(map);
						
			System.out.println("조회된플래너지역 : " + aList);
			
			// 지역명 가져오기
			// 경유 여부가 체크되지 않았다면(지역이 완전같아야함)
			if(map.get("viaCheck")==null) {
				Set<Integer> deleteList = new HashSet<Integer>();
				// 지역명이 다른 플래너번호 리스트 저장
				System.out.println("경유 안할 경우 들어온거 확인");
				// 대지역명으로만 검색했을경우
				if(!map.get("largeArea").equals("전체")&&map.get("smallArea").equals("전체")) {
					System.out.println("대지역만 검색 조건 들어옴");
					for(AreaName item : aList) {
						if(!item.getLargeAreaName().equals(map.get("largeArea"))||
							!item.getSmallAreaName().equals(map.get("smallArea"))) {
							deleteList.add(item.getPlannerNo());
						}
					}
					System.out.println("대지역 중복삭제할리스트 : " + deleteList);
				} 
				// 대지역명 소지역명으로 검색했을경우
				else if(!map.get("largeArea").equals("전체")&&!map.get("smallArea").equals("전체")) {
					System.out.println("대소지역만 검색 조건 들어옴");
					for(AreaName item : aList) {
						if(!item.getLargeAreaName().equals(map.get("largeArea"))||
							!item.getSmallAreaName().equals(map.get("smallArea"))) {
							deleteList.add(item.getPlannerNo());
						}
					}
					System.out.println("대소지역 중복삭제할 리스트 : " + deleteList);
				}
				
				System.out.println("삭제전 : " + pListNo);
				// 가져온 aList와 deleteList와 플래너번호가 같을 경우 삭제 aList에서 삭제
				for(Iterator<Integer> it = pListNo.iterator(); it.hasNext(); ) {
					int value = it.next();
					for(int delNo : deleteList) {
						if(value==delNo) {
							it.remove();
						}
					}
				}
				
				System.out.println("삭제결과 : " + pListNo);
				// 경유 안할때 결과크기
				result = pListNo.size();
			} else { ////// 경유 할경우 viaCheck == on;
				System.out.println("경유 할경우 들어온거 확인");
				// 대지역명으로만 검색했을 경우
				
			}
		}
		return 0;
				
	}

	
	
}
