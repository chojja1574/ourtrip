package com.kh.ourtrip.admin.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	public Map<String, Integer> getVisitCount() throws Exception {
		List<String> vList = adminDAO.getVisitLog();
		List<Integer> fixList = new ArrayList<Integer>();
		String fix = null;
		System.out.println("가져올떄값 : " + vList);
		System.out.println("vList 갯수 : " + vList.size());
		for(int i=0; i<vList.size(); i++) {
			fix = vList.get(i).replace("-", "").substring(0, 8);
			
			System.out.println("for문 : " + fix);
			fixList.add(Integer.parseInt(fix));
		}
		System.out.println("수정후값 : " + fixList);
		int totalCount = 0;
		int monthCount = 0;
		int dayCount = 0;
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		Date time = new Date();
		int today = Integer.parseInt(format1.format(time));
		System.out.println("오늘풀날짜 : " + today);
		int month = today / 100;
		System.out.println("오늘달 : " + month);
		
		//총합
		totalCount = fixList.size();
		//달
		for(Integer list : fixList) {
			if(list/100 == month) {
				monthCount++;
			}
		}
		//일
		for(Integer list : fixList) {
			if(list%100 == today%100) {
				dayCount++;
			}
		}
		
		
		System.out.println("총합 : " + totalCount);
		System.out.println("이번달갯수 : " + monthCount);
		System.out.println("일일갯수 : " + dayCount);
		
		
		return null;
	}

}
