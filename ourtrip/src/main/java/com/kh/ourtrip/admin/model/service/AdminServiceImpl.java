package com.kh.ourtrip.admin.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ourtrip.admin.model.dao.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	public AdminDAO adminDAO;
	
	/** dashBoard 정보 조회용 Service
	 * @return dashBoardData
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getDashBoardData() throws Exception {
				
		// 방문수와 플래너 갯수를 담을 객체
		Map<String, Object> dashBoardData = new HashMap<String, Object>();
		
		// 포맷형식
		// SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		
		// 방문자 전체 날짜 가져오기(java.util.Date타입)
		List<Date> vList = adminDAO.getVisitDateList();
		
		// 오늘날짜 생성
		Date today = new Date();
		
		// MM만 나오게 포맷
		String strMonth = monthFormat.format(today);
		// dd만 나오게 포맷
		String strDay = dayFormat.format(today);
		
		// dd를 int타입으로 형변환
		int intDay = Integer.parseInt(strDay);
		
		// 날짜별 방문수 담을 배열 생성
		Integer[] weekVisitCount = {0,0,0,0,0,0,0};
		
		// 이번달 방문수 담을 객체
		Integer vMonthCount = 0;
		
		for(Date date : vList) {
			// 달부분이 같은 경우 이번달 방문수 증가
			if(strMonth.equals(monthFormat.format(date))) {
				vMonthCount++;
			}
			
			// 일일 방문 통계 계산
			
			// 날짜만 추출
			String strDate = dayFormat.format(date);
			// 비교계산을 위해 숫자형으로 형변환
			int intDate = Integer.parseInt(strDate);
			// 현재날짜 - 데이터날짜  = 경과시간
			int result = intDay - intDate;
			
			switch (result) {
			case 0: weekVisitCount[6]++; break;
			case 1: weekVisitCount[5]++; break;
			case 2: weekVisitCount[4]++; break;
			case 3: weekVisitCount[3]++; break;
			case 4: weekVisitCount[2]++; break;
			case 5: weekVisitCount[1]++; break;
			case 6: weekVisitCount[0]++; break;
			}

		}
		// 날짜별 방문수 확인
//		for(int i=0; i<weekVisitCount.length; i++) {
//			System.out.println("날짜별 방문수 : " + weekVisitCount[i]);
//		}
		
		// 총 방문수 
		Integer vTotal = vList.size();
		dashBoardData.put("weekVisitCount", weekVisitCount);
		dashBoardData.put("vMonthCount", vMonthCount);
		dashBoardData.put("vTotal", vTotal);
		
		///////////////////////////////////////////////////////
		// 플래너 생성 전체 날짜  가져오기
		List<Date> pList = adminDAO.getPlannerDateList();
		
		// 이번달 플래너수 담을 객체
		Integer pMonthCount = 0;
		
		// 날짜별 planner 담을 배열 생성
		Integer[] weekPlannerCount = {0,0,0,0,0,0,0};
		
		for(Date date : pList) {
			
			// 달부분이 같은 경우 이번달 플래너수 증가
			if(strMonth.equals(monthFormat.format(date))) {
				pMonthCount++;
			}
			
			// 일일 방문 통계 계산
			
			// 날짜만 추출
			String strDate = dayFormat.format(date);
			// 비교계산을 위해 숫자형으로 형변환
			int intDate = Integer.parseInt(strDate);
			// 현재날짜 - 데이터날짜  = 경과시간
			int result = intDay - intDate;
			
			switch (result) {
			case 0: weekPlannerCount[6]++; break;
			case 1: weekPlannerCount[5]++; break;
			case 2: weekPlannerCount[4]++; break;
			case 3: weekPlannerCount[3]++; break;
			case 4: weekPlannerCount[2]++; break;
			case 5: weekPlannerCount[1]++; break;
			case 6: weekPlannerCount[0]++; break;
			}

		}
		// 날짜별 플래너 수 확인
//		for(int i=0; i<weekVisitCount.length; i++) {
//			System.out.println("날짜별 플래너 수 : " + weekPlannerCount[i]);
//		}
		
		// 총 플래너수 
		Integer pTotal = pList.size();
		dashBoardData.put("weekPlannerCount", weekPlannerCount);
		dashBoardData.put("pMonthCount", pMonthCount);
		dashBoardData.put("pTotal", pTotal);
		
		//System.out.println(dashBoardData);
		//System.out.println(pList);
		
		return dashBoardData;
		
	}

}
