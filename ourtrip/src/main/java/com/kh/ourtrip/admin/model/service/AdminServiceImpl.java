package com.kh.ourtrip.admin.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ourtrip.admin.model.dao.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	public AdminDAO adminDAO;
	
	/** 방문정보조회용 Service
	 * @return visitCounts
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getVisitCount() throws Exception {
		//List<Date> fixList = new ArrayList<Date>();
		
		// 포맷형식
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		
		// DB에서 가져온 정보(java.util.Date타입)
		List<Date> vList = adminDAO.getVisitLog();
		
		Date today = new Date();
		String strToday = monthFormat.format(today);
		
		
		Integer[] weekVisitCount = {0,0,0,0,0,0,0};
		Integer monthCount = 0;
		for(Date date : vList) {
			if(strToday.equals(monthFormat.format(date))) {
				monthCount++;
			}
			
			long calTime = (today.getTime()-date.getTime())/(24*60*60*1000);
			calTime = Math.abs(calTime);
			
			if(calTime == 0) weekVisitCount[6]++;
			else if(calTime == 1) weekVisitCount[5]++;
			else if(calTime == 2) weekVisitCount[4]++;
			else if(calTime == 3) weekVisitCount[3]++;
			else if(calTime == 4) weekVisitCount[2]++;
			else if(calTime == 5) weekVisitCount[1]++;
			else if(calTime == 6) weekVisitCount[0]++;
		}
		
		for(int i=0; i<weekVisitCount.length; i++) {
			System.out.println("날짜별 방문수 : " + weekVisitCount[i]);
		}
		
		System.out.println("이달방문수 : " + monthCount);
		
		System.out.println("총방문수 : " + vList.size());
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
		
		return null;
	}

}
