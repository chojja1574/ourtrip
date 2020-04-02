package com.kh.ourtrip.planner.model.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.dao.PlannerDAO;
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

@Service
public class PlannerServiceimpl implements PlannerService {

	@Autowired
	private PlannerDAO plannerDAO;

	/**
	 * 플레너 생성용 Service
	 * 
	 * @param planner member
	 * @return result
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createPlanner(Planner planner) throws Exception {

		int result = 0;
		int plannerNo = plannerDAO.selectNextNo();

		planner.setPlannerNo(plannerNo);

		result = plannerDAO.createPlanner(planner);
		if (result > 0) {
			result = plannerNo;
		}
		
		return result;
	}

	/**
	 * 플래너 지역입력용 service
	 * 
	 * @param jarr
	 * @return result
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createLocation(JSONArray jarr, int plannerNo) throws Exception {

		List<AreaName> aName = new ArrayList<AreaName>();

		JSONObject jobj = new JSONObject();
		for (int i = 0; i < jarr.size(); i++) {
			jobj = (JSONObject) jarr.get(i);
			AreaName areaName = new AreaName();
			areaName.setSmallAreaCode(Integer.parseInt(jobj.get("small").toString()));
			areaName.setLargeAreaCode(Integer.parseInt(jobj.get("large").toString()));
			areaName.setPlannerNo(plannerNo);
			aName.add(areaName);
		}

		return plannerDAO.createLocation(aName);
	}

	/**
	 * 플래너_맴버 service
	 * 
	 * @param plannerNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createMember(int plannerNo, int memberNo) throws Exception {

		PlannerMember pMember = new PlannerMember();
		pMember.setMemberNo(memberNo);
		pMember.setPlannerNo(plannerNo);
		
		return plannerDAO.createpMember(pMember);
	}

	@Override
	public List<LargeArea> selectLargeNmList() throws Exception {
		return plannerDAO.selectLargeNmList();
	}

	@Override
	public List<SmallArea> selectsmallNmList() throws Exception {
		return plannerDAO.selectsmallNmList();
	}
	
	
	// @author 신덕수
	/** 추천플래너카드 조회
	 * @return recommendPCList
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> selectRecommendPCList() throws Exception {

		// 추천플래너카드 리스트 조회

		List<PlannerCard> recommendPCList = plannerDAO.selectRecommendPCList();
		//System.out.println("service rList : " + recommendPCList);
		

		// 추천플래너 카드 번호를 담을 리스트
		List<Integer> rListNo = new ArrayList<Integer>();
		if (!recommendPCList.isEmpty()) {
			// 추천리스트에서 번호 가져와서 번호 리스트에 담음
			for (PlannerCard card : recommendPCList) {
				rListNo.add(card.getPlannerNo());
				// System.out.println(card);
			}
			// 지역리스트 호출
			List<AreaName> aList = plannerDAO.selectAreaNames(rListNo);
//			for(AreaName list : aList) {
//				
//				//System.out.println(list);
//			}

			// 추천리스트에 지역리스트를 담음
			for (PlannerCard card : recommendPCList) {

				List<AreaName> areaNames = new ArrayList<AreaName>();

				for (AreaName list : aList) {
					if (card.getPlannerNo() == list.getPlannerNo()) {
						areaNames.add(list);
					}
				}

				card.setareaNames(areaNames);
				// System.out.println("잘들어갔남? : " + card.getareaNames());
			}

		}
		return recommendPCList;
	}

	/**
	 * 전체 플래너 수 + 검색 조회
	 * 
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	@Override
	public int getListCount(Map<String, Object> map) throws Exception {
		
		// 갯수 저장 객체
		int getListCount = 0;
		// 플래너 리스트(plannerTitle + groupName) 필터링1
		List<Integer> pListNo = plannerDAO.getPListNo(map);
		//System.out.println("서비스단 검색된 플래너 리스트 번호: " + pListNo);
		
		// 필터링2 시작
		// 지역이 전체 전체 검색일 경우 갯수
		getListCount = pListNo.size();

		// 지역 검색 조건이 있을 경우
		if((Integer)map.get("largeArea")!=0 && !pListNo.isEmpty()) {

			map.put("pListNo", pListNo);
			// 검색된 플래너번호에 맞는 지역명 가져오기

			List<AreaName> aList = plannerDAO.getAList(map);
			
			// 지역명 가져오기
			// 경유 여부가 체크되지 않았다면(지역이 완전같아야함)
			if(map.get("viaCheck")==null) {
				// 지역명이 다른 플래너번호 리스트 저장
				Set<Integer> deleteList = new HashSet<Integer>();
				
				// 대지역명으로만 검색했을경우
				if((Integer)map.get("largeArea")!=0&&(Integer)map.get("smallArea")==0) {
					for(AreaName item : aList) {
						if(item.getLargeAreaCode()!=(Integer)(map.get("largeArea"))||
							item.getSmallAreaCode()!=(Integer)(map.get("smallArea"))) {
							deleteList.add(item.getPlannerNo());
						}
					}
				} 
				// 대지역명 소지역명으로 검색했을경우
				else if((Integer)map.get("largeArea")!=0&&(Integer)map.get("smallArea")!=0) {
					for(AreaName item : aList) {
						if(item.getLargeAreaCode()!=(Integer)(map.get("largeArea"))||
							item.getSmallAreaCode()!=(Integer)(map.get("smallArea"))) {
							deleteList.add(item.getPlannerNo());
						}
					}
				}

				// 가져온 aList와 deleteList와 플래너번호가 같을 경우 삭제 aList에서 삭제
				for (Iterator<Integer> it = pListNo.iterator(); it.hasNext();) {
					int value = it.next();
					for (int delNo : deleteList) {
						if (value == delNo) {
							it.remove();
						}
					}
				}

				// 리스트 출력시
				// aList<AreaName>에서 deleteList의 plannerNo를 지운값이 지역명

				// 경유 안할때 결과크기
				getListCount = pListNo.size();
			} else { ////// 경유 할경우 viaCheck == on;
				// 지역 필터링된 번호를 얻어오기

				List<Integer> viaListNo = plannerDAO.getRListNo(map);
				//System.out.println("List인상태 경유할 경우 결과 : " + viaListNo);

				Set<Integer> rListNo = new HashSet<Integer>();
				for (Integer item : viaListNo) {
					rListNo.add(item);
				}

				getListCount = rListNo.size();

			}
		}
		return getListCount;
	}

	/**
	 * 플래너 조회 ( + 검색)
	 * 
	 * @param map
	 * @param pInf
	 * @return pList
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> selectPList(Map<String, Object> map, PageInfo pInf) throws Exception {

		// 플래너 리스트(plannerTitle + groupName) 필터링1
		List<Integer> pListNo = plannerDAO.getPListNo(map);
		
		// 필터링2 
		map.put("pListNo", pListNo);

		// 반환된 값이 없으면
		if (pListNo.isEmpty()) {
			return null;
		}

		// 검색된 플래너번호에 맞는 지역명 가져오기
		List<AreaName> aList = plannerDAO.getAList(map);

		// 지역 검색 조건이 있을 경우
		if ((Integer) map.get("largeArea") != 0) {

			// 지역명 가져오기
			// 경유 여부가 체크되지 않았다면(지역이 완전같아야함)
			if(map.get("viaCheck")==null) {
				// 지역명이 다른 플래너번호 리스트 저장
				Set<Integer> deleteList = new HashSet<Integer>();
				
				// 대지역명으로만 검색했을경우
				if(((Integer)map.get("largeArea")!=0&&(Integer)map.get("smallArea")==0) || 
						((Integer)map.get("largeArea")!=0&&(Integer)map.get("smallArea")!=0))  {
					for(AreaName item : aList) {
						if(item.getLargeAreaCode()!=(Integer)(map.get("largeArea"))||
							item.getSmallAreaCode()!=(Integer)(map.get("smallArea"))) {
							deleteList.add(item.getPlannerNo());
						}
					}
				} 
				
				// 가져온 aList와 deleteList와 플래너번호가 같을 경우 삭제 aList에서 삭제
				for (Iterator<Integer> it = pListNo.iterator(); it.hasNext();) {
					int value = it.next();
					for (int delNo : deleteList) {
						if (value == delNo)
							it.remove();
					}
				}

				// ------------------------------------------------------//
			} else { ////// 경유 할경우 viaCheck == on;
				// 지역 필터링된 번호를 얻어오기
				List<Integer> viaListNo = plannerDAO.getRListNo(map);
				//System.out.println("List인상태 경유할 경우 결과 : " + viaListNo);
				

				// 중복제거를 위한 set에 담기
				Set<Integer> rListNo = new HashSet<Integer>();
				for (Integer item : viaListNo) {
					rListNo.add(item);
				}
				
				pListNo.clear();
				// 중복제거된 부분을 다시 리스트에 담기
				for (Integer item : rListNo) {
					pListNo.add(item);
				}
			}
		}

		// 검색지역이 일치하는게 없을경우
		if (pListNo.isEmpty()) {
			return null;
		}
		// searchListNo(검색된 플래너 번호)
	
		map.put("searchListNo", pListNo);
		
		// 최종 목록 가져오기
		List<PlannerCard> pList = plannerDAO.selectPList(map, pInf);
		
		// 위에서 지역정보를 담고 있는 aList를 이용하여 지역명 담기
		// 추천리스트에 지역리스트를 담음
		for (PlannerCard card : pList) {

			List<AreaName> areaNames = new ArrayList<AreaName>();

			for (AreaName list : aList) {
				if (card.getPlannerNo() == list.getPlannerNo())
					areaNames.add(list);

			}

			card.setareaNames(areaNames);
			// System.out.println("잘들어갔남? : " + card.getareaNames());
		}
		return pList;
	}

	
	// @author 조유상
	
	/**
	 * 회원 수정중인 플래너 수 조회용 Service
	 * 
	 * @param memberNo
	 * @return updatePlannerCount
	 * @throws Exception
	 */
	@Override
	public int updatePlannerCount(int memberNo) throws Exception {
		return plannerDAO.updatePlannerCount(memberNo);
	}

	/**
	 * 회원이 참여하고있는 플래너 번호 목록 조회용 Service
	 * 
	 * @param memberNo
	 * @return plannerNoList
	 * @throws Exception
	 */
	@Override
	public List<PlannerMember> selectPlannerMember(int memberNo) throws Exception {
		return plannerDAO.selectPlannerMember(memberNo);
	}

	/**
	 * 수정중인 플래너 목록 조회용 Service
	 * 
	 * @param memberNo
	 * @return uPlannerList
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> updatePlannerList(int memberNo) throws Exception {
		return plannerDAO.updatePlannerList(memberNo);
	}

	/**
	 * 완료된 플래너 목록 조회용 Service
	 * 
	 * @param memberNo
	 * @return cPlannerList
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> completePlannerList(int memberNo) throws Exception {
		return plannerDAO.completePlannerList(memberNo);
	}

	/**
	 * 플래너 지역이름 조회용 Service
	 * 
	 * @param noList
	 * @return areaNames
	 * @throws Exception
	 */
	@Override
	public List<AreaName> selectAreaNames(List<Integer> noList) throws Exception {
		return plannerDAO.selectAreaNames(noList);
	}

	/**
	 * 플래너 삭제용 Service
	 * 
	 * @param delPlanner
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int delPlanner(PlannerMember delPlanner) throws Exception {
		// 회원이 삭제하려는 플래너에 맞는 권한인지 확인

		String permission = plannerDAO.selectPlannerPerm(delPlanner);
		int result = 0;
		if (permission.equals("3")) {
			delPlanner.setPlannerPermission(Integer.parseInt(permission));
			result = plannerDAO.delPlanner(delPlanner);
		}

		return result;
	}

	/** 플래너 나가기용 Service
	 * @param outPlanner
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int outPlanner(PlannerMember outPlanner) throws Exception {
		// 회원이 나가려는 플래너에 맞는 권한인지 확인
		String permission = plannerDAO.selectPlannerPerm(outPlanner);
		
		int result = 0;
		if(!permission.equals("3")) {
			outPlanner.setPlannerPermission(Integer.parseInt(permission));
			result = plannerDAO.outPlanner(outPlanner);
		}
		
		return result;
	}
	
	/** 플래너 복사용 Service
	 * @param no
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int plannerCopy(int no, int memberNo) throws Exception {
		
		int result = 0;
		
		// 1. DB에서 플래너에 관한 상세정보 조회
		List<PlannerView> plannerDetail = plannerDAO.selectPlannerViewUseNo(no);
		
		// 해당 플래너의 지역정보 조회
		List<AreaName> areaNameList = plannerDAO.selectAreaNamePlanner(no);
		System.out.println("names : " + areaNameList);
		
		if(!plannerDetail.isEmpty()) {
			// 2. DB에서 플래너 다음번호 생성 후 값 붙여넣고 DB에 날림
			int plannerNextNo = plannerDAO.selectNextNo2();
			// url 생성
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar c1 = Calendar.getInstance();
			String strToday = sdf.format(c1.getTime());
			String url = strToday;
			for (int i = 1; i < 8; i++) {
				double random = Math.random();
				int ranDomInt = (int) (random * 10);
				url += ranDomInt;
			}
			
			Planner copyPlanner = new Planner(plannerNextNo, plannerDetail.get(0).getPlannerTitle() + "_복사본", plannerDetail.get(0).getPlannerCost(),
					"Y", plannerDetail.get(0).getPlannerExpiry(), url, plannerDetail.get(0).getGroupCode());
			
			System.out.println("service planner : " + copyPlanner);
			result = plannerDAO.createPlanner2(copyPlanner);
			if(result <= 0) throw new Exception("플래너 복사 후 생성 과정중 오류 발생");
			
			result = plannerDAO.insertPlannerMember(new PlannerMember(copyPlanner.getPlannerNo(), memberNo, 3));
			if(result <= 0) throw new Exception("플래너 생성 후 플래너 회원 테이블에 insert과정중 오류 발생");
			
			// 플래너 복사 성공 시 가져왔던 DB 추가
			for(AreaName areaName : areaNameList) {
				areaName.setPlannerNo(plannerNextNo);
				result = plannerDAO.insertAreaName(areaName);
				System.out.println("result : " + result);
			}
			
			// 3. DB에서 날짜번호 생성 후 값 붙여넣고 DB에 날림
			
			// DateNo에 대한 중복제거
			Set<Integer> dateNoList = new HashSet<Integer>();
			for(PlannerView pd : plannerDetail) dateNoList.add(pd.getDateNo());
			
			List<Day> dayList = new ArrayList<Day>();
			List<Schedule> scheduleList = new ArrayList<Schedule>();
			
			// 중복제거된 번호를 이용하여 Date테이블에 넣을 값 복사
			for(Integer dateNo : dateNoList) {
				boolean isPass = false; // 똑같은 날짜인지 검사
				
				int dateNextNo = 0; // 하나의 날짜에 여러개의 스케쥴을 담기위한 날짜 가져오기용 변수
				// 플래너 순환
				for(PlannerView pd : plannerDetail) {
					if(dateNo == pd.getDateNo()) { // 중복제거된 번호랑 순환되는 번호랑 같으면
						if(!isPass) { // 중복되는 날짜가 없었다면
							dateNextNo = plannerDAO.getNextDateNo();
							dayList.add(new Day(dateNextNo, pd.getTripDate(), plannerNextNo));
							scheduleList.add(new Schedule(plannerDAO.getNextScheduleNo(), pd.getScheduleTitle(), pd.getScheduleCost(), pd.getScheduleTime(),
									pd.getScheduleMemo(), pd.getScheduleLocationNM(), pd.getScheduleLat(), pd.getScheduleLng(), dateNextNo));
							isPass = true;
							
						}else {
							scheduleList.add(new Schedule(plannerDAO.getNextScheduleNo(), pd.getScheduleTitle(), pd.getScheduleCost(), pd.getScheduleTime(),
									pd.getScheduleMemo(), pd.getScheduleLocationNM(), pd.getScheduleLat(), pd.getScheduleLng(), dateNextNo));
						}
					}
				}
			}
			
			// 복사된 플래너 날짜 DB에 삽입
			for(Day day : dayList) {
				result = plannerDAO.copyDate(day);
				if(result <= 0) throw new Exception("플래너 날짜 복사 과정중 오류 발생");
			}
			
			// 4. DB에서 스케줄번호 생성 후 값 붙여넣고 DB에 날림
			for(Schedule schedule : scheduleList) {
				result = plannerDAO.insertSchedule(schedule);
				if(result <= 0) throw new Exception("플래너 스케쥴 복사 과정중 오류 발생");
			}
			
		}
		
		return result;
	}
	
	// @author 박지현

	/** PlannerView에서 플래너 번호를 이용하여 플래너 정보를 가져오는 Service
	 * @param no
	 * @return pList
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Planner selectPlannerView(String no) throws Exception {
		
		Planner selectedPlanner = null;
		
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
		
		List<PlannerView> selectPlannerView = plannerDAO.selectPlannerView(no);
		
		System.out.println(selectPlannerView);
		Map<Integer,List<PlannerView>> dateMap = new HashMap<Integer,List<PlannerView>>();
		for(PlannerView pv : selectPlannerView) {
			if(inputPvVal) {
				plannerNo = pv.getPlannerNo();
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
		    Day oneDay = new Day(dateNo,tripDate,plannerNo,scheduleList);
		    dayList.add(oneDay);
		    
		}
		selectedPlanner = new Planner(plannerNo, plannerTitle, plannerPwd, plannerCost, 
				plannerCreateDT, plannerModifyDT, plannerStartDT, plannerPublicYN, plannerDeleteYN, 
				plannerExpiry, plannerCount, plannerUrl, groupCode, dayList);
		
		if(selectPlannerView != null) {
			int result = plannerDAO.increasePlannerCount(plannerNo);
		}
		return selectedPlanner;
	}

	/** PLANNER_DATE 테이블의 다음 DATE_NO를 가져오는 Service
	 * @return result
	 * @throws Exception
	 */
	@Override
	public int getNextDateNo() throws Exception {
		return plannerDAO.getNextDateNo();
	}
	
	/** SCHEDULE 테이블의 다음 SCHEDULE_NO를 가져오는 Service
	 * @return result
	 * @throws Exception
	 */
	@Override
	public int getNextScheduleNo() throws Exception {
		return plannerDAO.getNextScheduleNo();
	}

	/** 플래너 일차 삽입용 Service
	 * @param day
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertDate(Day day) throws Exception {
		return plannerDAO.insertDate(day);
	}

	/** 플래너 일차 순서(TRIP_DATE) 수정용 Service
	 * @param dayList
	 * @return -1
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateTripDate(List<Day> dayList) throws Exception {
		return plannerDAO.updateTripDate(dayList);
	}

	/** 플래너 일차 생성 시 기본 일정 생성 Service
	 * @param schedule
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertDefaultSchedule(Schedule schedule) throws Exception {
		return plannerDAO.insertDefaultSchedule(schedule);
	}

	/** 플래너 일차 삭제 Service
	 * @param dateNo
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteDate(int dateNo) throws Exception {
		return plannerDAO.deleteDate(dateNo);
	}

	/** 플래너 일정 수정용 Service
	 * @param sche
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateSchedule(Schedule sche) throws Exception {
		return plannerDAO.updateSchedule(sche);
	}

	/** 플래너 일정 추가용 Service
	 * @param sche
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertSchedule(Schedule sche) throws Exception {
		return plannerDAO.insertSchedule(sche);
	}

	/** 플래너 일정 삭제용 Service
	 * @param sno
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteSchedule(int sno) throws Exception {
		return plannerDAO.deleteSchedule(sno);
	}

	/** 플래너 채팅내역 저장용 Service
	 * @param chatLog
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertChattingLog(ChattingLogView chatLog) throws Exception {
		return plannerDAO.insertChattingLog(chatLog);
	}

	/** 플래너 번호를 이용하여 해당플래너 채팅내역 불러오는 Service
	 * @param no
	 * @return cList
	 * @throws Exception
	 */
	@Override
	public List<ChattingLogView> selectChatList(int no) throws Exception {
		return plannerDAO.selectChatList(no);
	}

	/** 플래너 번호를 이용하여 플래너에 참여중인 회원을 불러오는 Service
	 * @param pno
	 * @return pmList
	 * @throws Exception
	 */
	@Override
	public List<PlannerMemberView> selectPlannerMemeberListUsePlannerNo(int pno) throws Exception {
		return plannerDAO.selectPlannerMemeberListUsePlannerNo(pno);
	}

	/** 플래너에 해당 회원이 존재하는지 검사하는 Service
	 * @param pm
	 * @return pmList
	 * @throws Exception
	 */
	@Override
	public int selectPlannerMemeberExist(PlannerMemberView pm) throws Exception {
		return plannerDAO.selectPlannerMemeberExist(pm);
	}

	/** PLANNER_MEMBER 테이블에 새로운 값 추가 Service
	 * @param pm
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertPlannerMember(PlannerMember pm) throws Exception {
		return plannerDAO.insertPlannerMember(pm);
	}
	
	/** PLANNER_MEMBER 테이블의 permission값 수정 Service
	 * @param pm
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updatePermission(PlannerMember pm) throws Exception{
		return plannerDAO.updatePermission(pm);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateSumCost(Planner p) throws Exception {
		return plannerDAO.updateSumCost(p);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateStartDate(Planner p) throws Exception {
		return plannerDAO.updateStartDate(p);
	}

	@Override
	public int countDate(int pno) throws Exception {
		return plannerDAO.countDate(pno);
	}
	
	@Override
	public int countSchedule(int dno) throws Exception {
		return plannerDAO.countSchedule(dno);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateTitle(Planner planner) throws Exception {
		return plannerDAO.updateTitle(planner);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updatePassword(Planner planner) throws Exception {
		return plannerDAO.updatePassword(planner);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updatePublic(Planner planner) throws Exception {
		return plannerDAO.updatePublic(planner);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int clearUserList(int no) throws Exception {
		return plannerDAO.clearUserList(no);
	}

	@Override
	public List<AreaName> selectPlannerLocationName(Integer no) throws Exception {
		return plannerDAO.selectPlannerLocationName(no);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateLocationList(List<AreaName> locationList) throws Exception {
		plannerDAO.deletePlannerLocation(locationList.get(0).getPlannerNo());
		return plannerDAO.updateLocationList(locationList);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateGroup(Planner planner) throws Exception {
		return plannerDAO.updateGroup(planner);
	}


}
