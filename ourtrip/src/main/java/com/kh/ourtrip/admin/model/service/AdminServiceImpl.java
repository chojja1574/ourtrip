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
		
		for(int i=0; i<weekVisitCount.length; i++) {
			System.out.println("날짜별 방문수 : " + weekVisitCount[i]);
		}
		
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
		
		for(int i=0; i<weekVisitCount.length; i++) {
			System.out.println("날짜별 플래너 수 : " + weekPlannerCount[i]);
		}
		
		// 총 플래너수 
		Integer pTotal = pList.size();
		dashBoardData.put("weekPlannerCount", weekPlannerCount);
		dashBoardData.put("pMonthCount", pMonthCount);
		dashBoardData.put("pTotal", pTotal);
		
		System.out.println(dashBoardData);
		//System.out.println(pList);
		
		return dashBoardData;
		
		// 오늘날짜 - 지난날짜 / 하루 = 경과시간
		// long calTime = (today.getTime()-date.getTime())/(24*60*60*1000);
		// calTime = Math.abs(calTime);
		
		// 각 경과 날짜를 카운트 하여 최근꺼부터 뒤부터 저장
//			if(calTime == 0) weekVisitCount[6]++;
//			else if(calTime == 1) weekVisitCount[5]++;
//			else if(calTime == 2) weekVisitCount[4]++;
//			else if(calTime == 3) weekVisitCount[3]++;
//			else if(calTime == 4) weekVisitCount[2]++;
//			else if(calTime == 5) weekVisitCount[1]++;
//			else if(calTime == 6) weekVisitCount[0]++;
//		
//		System.out.println("이달방문수 : " + monthCount);
//		
//		System.out.println("총방문수 : " + vList.size());
//		
//		
//		System.out.println("map에 담긴 일주일 방문자수 : " + visitCounts.get("weekVisitCount"));
//		System.out.println("map에 담긴 한달 방문자수 : " + visitCounts.get("monthCount"));
//		System.out.println("map에 담긴 총 방문자수 : " + visitCounts.get("vTotal"));
//		
		
//		// 오늘날짜
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//
//        // 어제날짜
//        cal.add(Calendar.DATE, -1);
//		
//        
//		Date sqlTime = new Date(cal.getTimeInMillis());
//		String sqlTime2 = format1.format(sqlTime);
//		//int today = Integer.parseInt(format1.format(time));
//		System.out.println("sqltime : " + sqlTime2);
//		List<Date> vList = adminDAO.getVisitLog();
//		for(int i=0; i<vList.size(); i++) {
//			System.out.println(vList.get(i));
//			if(sqlTime2.equals(format1.format(vList.get(i)))) {
//				System.out.println("같을떄 : " + format1.format(vList.get(i)));
//			}
//		}
//		
//		if(!vList.isEmpty() && vList!=null) {
//			for(java.sql.Date date : vList) {
//				fixList.add(new Date(date.getTime()));
//				System.out.println(fixList);
//			}
//			
//			for(Date date : fixList) {
//				System.out.println(date);
//			}
//		}
		
		
//		List<Integer> fixList = new ArrayList<Integer>();
//		String fix = null;
//		System.out.println("가져올떄값 : " + vList);
//		System.out.println("vList 갯수 : " + vList.size());
//		for(int i=0; i<vList.size(); i++) {
//			fix = vList.get(i).replace("-", "").substring(0, 8);
//			
//			System.out.println("for문 : " + fix);
//			fixList.add(Integer.parseInt(fix));
//		}
//		System.out.println("수정후값 : " + fixList);
//		int totalCount = 0;
//		int monthCount = 0;
//		List<Integer> dayCount = new ArrayList<Integer>();
//						
//		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
//		Date time = new Date();
//		int today = Integer.parseInt(format1.format(time));
//		System.out.println("오늘풀날짜 : " + today);
//		int month = today / 100;
//		System.out.println("오늘달 : " + month);
//		
//		//총합
//		totalCount = fixList.size();
//		//달
//		for(Integer list : fixList) {
//			if(list/100 == month) {
//				monthCount++;
//			}
//		}
//		//일 
//		int count = 0;
//		for(Integer list : fixList) {
//			for(int i=0; i<7; i++) {
//				if(list%100 == (today-i)%100) {
//					count++;
//				} 
//			}
//			dayCount.add(i, count);
//			count = 0;
//		}
//		
//		
//		
//		System.out.println("총합 : " + totalCount);
//		System.out.println("이번달갯수 : " + monthCount);
//		for(int i=0; i<dayCount.size(); i++) {
//			System.out.println(i+"번째 : " + dayCount.get(i));
//		}
//		//System.out.println("이번주갯수 : " + dayCount);
//		
		
		
	}

}
