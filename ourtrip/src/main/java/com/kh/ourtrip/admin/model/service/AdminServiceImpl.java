package com.kh.ourtrip.admin.model.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ourtrip.admin.model.dao.AdminDAO;
import com.kh.ourtrip.admin.model.vo.PlannerDeleteReason;
import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.member.model.vo.ProfileImage;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerInfo;
import com.kh.ourtrip.planner.model.vo.SmallArea;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	public AdminDAO adminDAO;
	
	@Autowired
	private JavaMailSender mailSender;
	
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
	
	

	/** 회원수 전체 + 검색조회용 service
	 * @return listCount
	 * @throws Exception
	 */
	@Override
	public int getListCount(Map<String, Object> map) throws Exception {

		return adminDAO.getListCount(map);
	}

	/**
	 * 회원 전체조회용 service
	 * 
	 * @param pInf
	 * @return memberList
	 * @throws Exception
	 */
	@Override
	public List<Member> selectList(Map<String, Object> map, PageInfo pInf) throws Exception {

		return adminDAO.selectList(map, pInf);
	}


	/**
	 * 회원 상세조회용 service
	 * 
	 * @param no
	 * @return Member
	 * @throws Exception
	 */
	@Override
	public Member detail(int no) throws Exception {

		return adminDAO.detail(no);
	}

	/**
	 * 플래너 넘버 조회용 service
	 * 
	 * @param no
	 * @return plannerList
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> plannerList(int no) throws Exception {

		return adminDAO.plannerList(no);
	}

	/**
	 * 플래너 카드 조회용 service
	 * 
	 * @param plannerList
	 * @param pInf
	 * @return plannerInfo
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> plannerInfo(List<Integer> plannerList, PageInfo pInf) throws Exception {

		return adminDAO.plannerInfo(plannerList, pInf);
	}

	/**
	 * 지역조회용 service
	 * 
	 * @param plannerList
	 * @return plannerArea
	 * @throws Exception
	 */
	@Override
	public List<AreaName> plannerArea(List<Integer> plannerList) throws Exception {
		return adminDAO.plannerArea(plannerList);
	}

	/**
	 * 프로필 이미지 조회용 service
	 * 
	 * @param no
	 * @return pi
	 * @throws Exception
	 */
	@Override
	public ProfileImage selectProfileImage(int no) throws Exception {
		return adminDAO.selectProfileImage(no);
	}

	/**
	 * 플래너 목록조회용 DAO
	 * 
	 * @return totalList
	 * @throws Exception
	 */
	@Override
	public List<PlannerInfo> plannerTotal(PageInfo pInf) throws Exception {
		return adminDAO.plannerTotal(pInf);
	}

	/**
	 * 플래너 개수 조회용 service
	 * 
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int plannerCount() throws Exception {
		return adminDAO.plannerCount();
	}

	/**
	 * 여행일자 조회용 service
	 * 
	 * @return list<day>
	 * @throws Exception
	 */
	@Override
	public List<Day> dayList() throws Exception {

		return adminDAO.dayList();
	}

	/**
	 * 플래너 상세보기 용 service
	 * 
	 * @param no
	 * @return plannerInfo
	 * @throws Exception
	 */
	@Override
	public PlannerInfo plannerDetail(int no) throws Exception {
		return adminDAO.plannerDetail(no);
	}

	/**
	 * 지역 조회용 service
	 * 
	 * @param no
	 * @return areaName
	 * @throws Exception
	 */
	@Override
	public List<AreaName> areaDetail(int no) throws Exception {
		return adminDAO.areaDetail(no);
	}

	/**
	 * 플래너 삭제용 service
	 * 
	 * @param plannerNo
	 * @return result
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deletePlanner(int plannerNo) throws Exception {
		return adminDAO.deletePlanner(plannerNo);
	}

	/**
	 * 삭제 메일 발송용 service
	 * 
	 * @param pdr
	 * @return reason
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int reason(PlannerDeleteReason pdr) throws Exception {
		return adminDAO.reason(pdr);
	}

	/**
	 * 삭제메일 전송용 service
	 * 
	 * @param email
	 * @return sendEmail
	 * @throws Exception
	 */
	@Override
	public void sendEmail(String email, PlannerDeleteReason pdr) throws Exception {

		String setfrom = "khourtrip@gmail.com";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

		messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
		messageHelper.setTo(email); // 받는사람 이메일
		messageHelper.setSubject("OurTrip 플래너 삭제 사유"); // 메일제목은 생략이 가능하다
		messageHelper.setText(pdr.getDeleteReason()); // 메일 내용

		mailSender.send(message);

	}

	/**
	 * 플래너 복구용 service
	 * 
	 * @param plannerNo
	 * @return result
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int recoveryPlanner(int plannerNo) throws Exception {
		return adminDAO.recoveryPlanner(plannerNo);
	}

	/**
	 * 회원 강퇴용 service
	 * 
	 * @param memberNo
	 * @param email
	 * @param delBecause
	 * @return result
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int memberDelete(int memberNo, String email, String delBecause) throws Exception {
		int result = 0;

		result = adminDAO.memberDelete(memberNo);

		if (result > 0) {
			String setfrom = "khourtrip@gmail.com";

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(email); // 받는사람 이메일
			messageHelper.setSubject("OurTrip 회원 삭제 사유"); // 메일제목은 생략이 가능하다
			messageHelper.setText(delBecause); // 메일 내용

			mailSender.send(message);
		}

		return result;
	}

	/** 회원 복구용 service
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int memberRecovery(int memberNo) throws Exception {
		return adminDAO.memberRecovery(memberNo);
	}
	
	
	/**
	 * 대지역명 조회용 service
	 * 
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<LargeArea> selectLargeNmList() throws Exception {
		return adminDAO.selectLargeNmList();
	}

	/**
	 * 소지역명 조회용 service
	 * 
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<SmallArea> selectsmallNmList() throws Exception {
		return adminDAO.selectsmallNmList();
	}

	/**
	 * 전체 플래너 리스트 지역명 조회용 service
	 * 
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<AreaName> totalAList() throws Exception {
		return adminDAO.totalAList();
	}

	/**
	 * plannerInfo 검색용 service
	 * 
	 * @param keyword
	 * @return searchList
	 * @throws Exception
	 */
	@Override
	public List<PlannerInfo> searchList(Map<String, Object> keyword) throws Exception {
		return adminDAO.searchList(keyword);
	}
	
	/** areaInfo 검색용 service
	 * @param keyword
	 * @return areaInfo
	 * @throws Exception
	 */
	@Override
	public List<AreaName> areaInfo(Map<String, Object> keyword) throws Exception {
		return adminDAO.areaInfo(keyword);
	}
	
	/** 검색결과, 페이징 처리용 serivce
	 * @param keyword
	 * @param pInf
	 * @return plannerInfo
	 * @throws Exception
	 */
	@Override
	public List<PlannerInfo> plannerInfo(Map<String, Object> keyword, PageInfo pInf) throws Exception {
		return adminDAO.plannerInfo(keyword,pInf);
	}

	/** 플래너 개수 조회용 service
	 * @param keyword
	 * @return pListCount
	 * @throws Exception
	 */
	@Override
	public List<Integer> getPlannerListCount(Map<String, Object> keyword) throws Exception {
		// 플래너 리스트(plannerTitle + groupName) 필터링1
		List<Integer> pListNo = adminDAO.getPlannerList(keyword);
		
		if(keyword.get("largeArea") == null) keyword.put("largeArea", 0);
		
		// 지역 검색 조건이 있을 경우
		if(!pListNo.isEmpty()) {
			
			keyword.put("pListNo", pListNo);

//			List<AreaName> aList = adminDAO.getAreaNameList(keyword);
			
			// 지역 필터링된 번호를 얻어오기
			pListNo = adminDAO.getAreaFilterList(keyword);

			Set<Integer> rListNo = new HashSet<Integer>();
			for (Integer item : pListNo) {
				rListNo.add(item);
			}
			pListNo.clear();
			for(Integer item : rListNo) {
				pListNo.add(item);
			}
		}
		
		return pListNo;
		
	}

	/** 플래너 목록 조회용 Service
	 * @param keyword
	 * @param pInf
	 * @return plannerList
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> selectPlannerList(Map<String, Object> keyword, PageInfo pInf) throws Exception {
		
		List<PlannerCard> pInfoList = adminDAO.selectPlannerList(keyword, pInf);
		
		// 검색된 플래너번호에 맞는 지역명 가져오기
		List<AreaName> aList = adminDAO.getAreaNameList(keyword);
		
		// 위에서 지역정보를 담고 있는 aList를 이용하여 지역명 담기
		// 추천리스트에 지역리스트를 담음
		for (PlannerCard planner : pInfoList) {

			List<AreaName> areaNames = new ArrayList<AreaName>();

			for (AreaName areaName : aList) {
				if (planner.getPlannerNo() == areaName.getPlannerNo())
					areaNames.add(areaName);

			}

			planner.setAreaNames(areaNames);
		}
		return pInfoList;
		
	}


}
