package com.kh.ourtrip.admin.model.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ourtrip.admin.model.dao.AdminHunDAO;
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
public class AdminHunServiceimpl implements AdminHunService {

	@Autowired
	AdminHunDAO adminHunDAO;
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * 회원수 전체조회용 service
	 * 
	 * @return listFullCount
	 * @throws Exception
	 */
	@Override
	public int getListFullCount() throws Exception {

		return adminHunDAO.getListFullCount();
	}

	/**
	 * 회원 전체조회용 service
	 * 
	 * @param pInf
	 * @return memberList
	 * @throws Exception
	 */
	@Override
	public List<Member> selectFullList(PageInfo pInf) throws Exception {

		return adminHunDAO.selectFUllList(pInf);
	}

	/**
	 * 회원 목록 조회용 service
	 * 
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	@Override
	public int getListCount(Map<String, String> map) throws Exception {

		return adminHunDAO.getListCount(map);
	}

	/**
	 * 회원 목록조회용 service
	 * 
	 * @param map
	 * @param pInf
	 * @return memberList
	 * @throws Exception
	 */
	@Override
	public List<Member> selectList(Map<String, String> map, PageInfo pInf) throws Exception {

		return adminHunDAO.selectList(map, pInf);
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

		return adminHunDAO.detail(no);
	}

	/**
	 * 플래너 넘버 조회용 service
	 * 
	 * @param no
	 * @return plannerList
	 * @throws Exception
	 */
	@Override
	public List<Integer> plannerList(int no) throws Exception {

		return adminHunDAO.plannerList(no);
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

		return adminHunDAO.plannerInfo(plannerList, pInf);
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
		return adminHunDAO.plannerArea(plannerList);
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
		return adminHunDAO.selectProfileImage(no);
	}

	/**
	 * 플래너 목록조회용 DAO
	 * 
	 * @return totalList
	 * @throws Exception
	 */
	@Override
	public List<PlannerInfo> plannerTotal(PageInfo pInf) throws Exception {
		return adminHunDAO.plannerTotal(pInf);
	}

	/**
	 * 플래너 개수 조회용 service
	 * 
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int plannerCount() throws Exception {
		return adminHunDAO.plannerCount();
	}

	/**
	 * 여행일자 조회용 service
	 * 
	 * @return list<day>
	 * @throws Exception
	 */
	@Override
	public List<Day> dayList() throws Exception {

		return adminHunDAO.dayList();
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
		return adminHunDAO.plannerDetail(no);
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
		return adminHunDAO.areaDetail(no);
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
		return adminHunDAO.deletePlanner(plannerNo);
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
		return adminHunDAO.reason(pdr);
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
		return adminHunDAO.recoveryPlanner(plannerNo);
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

		result = adminHunDAO.memberDelete(memberNo);

		if (result > 0) {
			String setfrom = "khourtrip@gmail.com";

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(email); // 받는사람 이메일
			messageHelper.setSubject("OurTrip 플래너 삭제 사유"); // 메일제목은 생략이 가능하다
			messageHelper.setText(delBecause); // 메일 내용

			mailSender.send(message);
		}

		return result;
	}

	/**
	 * 대지역명 조회용 service
	 * 
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<LargeArea> selectLargeNmList() throws Exception {
		return adminHunDAO.selectLargeNmList();
	}

	/**
	 * 소지역명 조회용 service
	 * 
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<SmallArea> selectsmallNmList() throws Exception {
		return adminHunDAO.selectsmallNmList();
	}

	/**
	 * 전체 플래너 리스트 지역명 조회용 service
	 * 
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<AreaName> totalAList() throws Exception {
		return adminHunDAO.totalAList();
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
		return adminHunDAO.searchList(keyword);
	}
	
	/** areaInfo 검색용 service
	 * @param keyword
	 * @return areaInfo
	 * @throws Exception
	 */
	@Override
	public List<AreaName> areaInfo(Map<String, Object> keyword) throws Exception {
		return adminHunDAO.areaInfo(keyword);
	}
	
	/** 검색결과, 페이징 처리용 serivce
	 * @param keyword
	 * @param pInf
	 * @return plannerInfo
	 * @throws Exception
	 */
	@Override
	public List<PlannerInfo> plannerInfo(Map<String, Object> keyword, PageInfo pInf) throws Exception {
		return adminHunDAO.plannerInfo(keyword,pInf);
	}

}