package com.kh.ourtrip.planner.model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.planner.model.dao.PlannerDAOSDS;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.SmallArea;

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
		
		System.out.println("------------------------서비스단 정보------------------------");
		// 갯수 저장 객체
		int getListCount = 0;
		// 플래너 리스트(plannerTitle + groupName) 필터링1
		List<Integer> pListNo = plannerDAOSDS.getPListNo(map);
		//System.out.println("서비스단 검색된 플래너 리스트 번호: " + pListNo);
		
		// 필터링2 시작
		// 지역이 전체 전체 검색일 경우 갯수
		getListCount = pListNo.size();
		
		// 지역 검색 조건이 있을 경우
		if((Integer)map.get("largeArea")!=0) {
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
				if((Integer)map.get("largeArea")!=0&&(Integer)map.get("smallArea")==0) {
					System.out.println("대지역만 검색 조건 들어옴");
					for(AreaName item : aList) {
						if(item.getLargeAreaCode()!=(Integer)(map.get("largeArea"))||
							item.getSmallAreaCode()!=(Integer)(map.get("smallArea"))) {
							deleteList.add(item.getPlannerNo());
						}
					}
					System.out.println("대지역 중복삭제할리스트 : " + deleteList);
				} 
				// 대지역명 소지역명으로 검색했을경우
				else if((Integer)map.get("largeArea")!=0&&(Integer)map.get("smallArea")!=0) {
					System.out.println("대소지역만 검색 조건 들어옴");
					for(AreaName item : aList) {
						if(item.getLargeAreaCode()!=(Integer)(map.get("largeArea"))||
							item.getSmallAreaCode()!=(Integer)(map.get("smallArea"))) {
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
				
				// 리스트 출력시 
				// aList<AreaName>에서 deleteList의 plannerNo를 지운값이 지역명
				
				System.out.println("삭제결과 : " + pListNo);
				// 경유 안할때 결과크기
				getListCount = pListNo.size();
			} else { ////// 경유 할경우 viaCheck == on;
				System.out.println("경유 할경우 들어온거 확인");
				System.out.println("경유 할경우 맵 확인 : " + map);
				// 지역 필터링된 번호를 얻어오기
				List<Integer> viaListNo = plannerDAOSDS.getRListNo(map);
				//System.out.println("List인상태 경유할 경우 결과 : " + viaListNo);
				Set<Integer> rListNo = new HashSet<Integer>();
				for(Integer item : viaListNo) {
					rListNo.add(item);
				}
				System.out.println("Set상태인 경유할 경우 결과 : " + rListNo);
				
				getListCount = rListNo.size();
				
			}
		}
		System.out.println("총개수 : " + getListCount);
		System.out.println("---------------------서비스단 정보 끝---------------------");
		return getListCount;
	}

	/** 플래너 조회 ( + 검색)
	 * @param map
	 * @param pInf
	 * @return pList
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> selectPList(Map<String, Object> map, PageInfo pInf) throws Exception {
		System.out.println("-----------플래너 조회 서비스단 정보------------------------");
		
		System.out.println("플래너 정보 map : " + map);
		// 플래너 리스트(plannerTitle + groupName) 필터링1
		List<Integer> pListNo = plannerDAOSDS.getPListNo(map);
		
		// 필터링2 
		map.put("pListNo", pListNo);
		System.out.println("map확인 : " + map);

		// 반환된 값이 없으면
		if(pListNo.isEmpty()) {
			return null;
		}
		
		// 검색된 플래너번호에 맞는 지역명 가져오기
		List<AreaName> aList = plannerDAOSDS.getAList(map);
		System.out.println("조회된플래너지역 : " + aList);
		
		// 지역 검색 조건이 있을 경우
		if((Integer)map.get("largeArea")!=0) {
			
			// 지역명 가져오기
			// 경유 여부가 체크되지 않았다면(지역이 완전같아야함)
			if(map.get("viaCheck")==null) {
				Set<Integer> deleteList = new HashSet<Integer>();
				// 지역명이 다른 플래너번호 리스트 저장
				System.out.println("경유 안할 경우 들어온거 확인");
				// 대지역명으로만 검색했을경우
				if(((Integer)map.get("largeArea")!=0&&(Integer)map.get("smallArea")==0) || 
						((Integer)map.get("largeArea")!=0&&(Integer)map.get("smallArea")!=0))  {
					System.out.println("대지역만 검색 조건 들어옴");
					for(AreaName item : aList) {
						if(item.getLargeAreaCode()!=(Integer)(map.get("largeArea"))||
							item.getSmallAreaCode()!=(Integer)(map.get("smallArea"))) {
							deleteList.add(item.getPlannerNo());
						}
					}
					System.out.println("대지역 중복삭제할리스트 : " + deleteList);
				} 
				
				System.out.println("삭제전 : " + pListNo);
				// 가져온 aList와 deleteList와 플래너번호가 같을 경우 삭제 aList에서 삭제
				for(Iterator<Integer> it = pListNo.iterator(); it.hasNext(); ) {
					int value = it.next();
					for(int delNo : deleteList) {
						if(value==delNo) it.remove();
					}
				}
				
								
			//------------------------------------------------------//	
			} else { ////// 경유 할경우 viaCheck == on;
				System.out.println("경유 할경우 들어온거 확인");
				System.out.println("경유 할경우 맵 확인 : " + map);
				// 지역 필터링된 번호를 얻어오기
				List<Integer> viaListNo = plannerDAOSDS.getRListNo(map);
				//System.out.println("List인상태 경유할 경우 결과 : " + viaListNo);
				
				// 중복제거를 위한 set에 담기
				Set<Integer> rListNo = new HashSet<Integer>();
				for(Integer item : viaListNo) {
					rListNo.add(item);
				}
				System.out.println("Set상태인 경유할 경우 결과 : " + rListNo);
				
				pListNo.clear();
				// 중복제거된 부분을 다시 리스트에 담기
				for(Integer item : rListNo) {
					pListNo.add(item);
				}
				
				System.out.println("경유할 경우 searchListNo : " + pListNo);
				
			}
		}
		
		// 검색지역이 일치하는게 없을경우
		if(pListNo.isEmpty()) {
			return null;
		}
		// searchListNo(검색된 플래너 번호)
		
		System.out.println("");
		map.put("searchListNo", pListNo);
		System.out.println("dao전달되는map : " + map);
		
		
		// 최종 목록 가져오기
		List<PlannerCard> pList = plannerDAOSDS.selectPList(map, pInf);
		
		// 위에서 지역정보를 담고 있는 aList를 이용하여 지역명 담기
		// 추천리스트에 지역리스트를 담음
		for(PlannerCard card : pList) {
			
			List<AreaName> areaNames = new ArrayList<AreaName>();
			
			for(AreaName list : aList) {
				if(card.getPlannerNo() == list.getPlannerNo()) areaNames.add(list);
				
			}
			
			card.setareaNames(areaNames);
			//System.out.println("잘들어갔남? : " + card.getareaNames());
		}
		System.out.println("------------------------플래너 조회 서비스단 정보----------------------------");
		return pList;
	}
	
	/** 대지역 목록 조회용 Service
	 * @return largeNmList
	 * @throws Exception
	 */

	@Override
	public List<LargeArea> selectLargeNmList() throws Exception {
		return plannerDAOSDS.selectLargeNmList();
	}

	/** 소지역 목록 조회용 Service
	 * @return smallNmList
	 * @throws Exception
	 */
	@Override
	public List<SmallArea> selectsmallNmList() throws Exception {
		return plannerDAOSDS.selectSmallNmList();
	}

	/** 회원 수정중인 플래너 수 조회용 Service
	 * @param memberNo
	 * @return updatePlannerCount
	 * @throws Exception
	 */
	@Override
	public int updatePlannerCount(int memberNo) throws Exception {
		return plannerDAOSDS.updatePlannerCount(memberNo);
	}

	/** 회원이 참여하고있는 플래너 번호 목록 조회용 Service
	 * @param memberNo
	 * @return plannerNoList
	 * @throws Exception
	 */
	@Override
	public List<PlannerMember> selectPlannerMember(int memberNo) throws Exception {
		return plannerDAOSDS.selectPlannerMember(memberNo);
	}

	/** 수정중인 플래너 목록 조회용 Service
	 * @param memberNo
	 * @return uPlannerList
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> updatePlannerList(int memberNo) throws Exception {
		return plannerDAOSDS.updatePlannerList(memberNo);
	}

	/** 완료된 플래너 목록 조회용 Service
	 * @param memberNo
	 * @return cPlannerList
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> completePlannerList(int memberNo) throws Exception {
		return plannerDAOSDS.completePlannerList(memberNo);
	}

	/** 플래너 지역이름 조회용 Service
	 * @param noList
	 * @return areaNames
	 * @throws Exception
	 */
	@Override
	public List<AreaName> selectAreaNames(List<Integer> noList) throws Exception {
		return plannerDAOSDS.selectAreaNames(noList);
	}

	/** 플래너 삭제용 Service
	 * @param delPlanner
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int delPlanner(PlannerMember delPlanner) throws Exception {
		// 회원이 삭제하려는 플래너에 맞는 권한인지 확인
		String permission = plannerDAOSDS.selectPlannerPerm(delPlanner);
		
		int result = 0;
		if(permission.equals("3")) {
			delPlanner.setPlannerPermission(Integer.parseInt(permission));
			result = plannerDAOSDS.delPlanner(delPlanner);
		}
		
		return result;
	}
	
}
