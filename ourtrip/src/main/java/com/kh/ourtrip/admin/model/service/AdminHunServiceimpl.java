package com.kh.ourtrip.admin.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.ourtrip.admin.model.dao.AdminHunDAO;
import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerCard;

@Service
public class AdminHunServiceimpl implements AdminHunService {
	
	@Autowired
	AdminHunDAO adminHunDAO;
	
	/** 회원수 전체조회용 service
	 * @return listFullCount
	 * @throws Exception
	 */
	@Override
	public int getListFullCount() throws Exception {

		return adminHunDAO.getListFullCount();
	}
	
	/** 회원 전체조회용 service
	 * @param pInf
	 * @return memberList
	 * @throws Exception
	 */
	@Override
	public List<Member> selectFullList(PageInfo pInf) throws Exception {

		return adminHunDAO.selectFUllList(pInf);
	}

	/** 회원 목록 조회용 service
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	@Override
	public int getListCount(Map<String, String> map)throws Exception {
		
		return adminHunDAO.getListCount(map);
	}

	/** 회원 목록조회용 service
	 * @param map
	 * @param pInf
	 * @return memberList
	 * @throws Exception
	 */
	@Override
	public List<Member> selectList(Map<String, String> map, PageInfo pInf) throws Exception {

		return adminHunDAO.selectList(map, pInf);
	}

	/** 회원 상세조회용 service
	 * @param no
	 * @return Member
	 * @throws Exception
	 */
	@Override
	public Member detail(int no) throws Exception {
		
		return adminHunDAO.detail(no);
	}


	/** 플래너 넘버 조회용 service
	 * @param no
	 * @return plannerList
	 * @throws Exception
	 */
	@Override
	public List<Integer> plannerList(int no) throws Exception {
		
		return adminHunDAO.plannerList(no);
	}

	/** 플래너 카드 조회용 service
	 * @param plannerList
	 * @param pInf
	 * @return plannerInfo
	 * @throws Exception
	 */
	@Override
	public List<Planner> plannerInfo(List<Integer> plannerList, PageInfo pInf) throws Exception {
		
		return adminHunDAO.plannerInfo(plannerList,pInf);
	}

	/** 지역조회용 service
	 * @param plannerList
	 * @return plannerArea
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> plannerArea(List<Integer> plannerList) throws Exception {
		return adminHunDAO.plannerArea(plannerList);
	}



}
