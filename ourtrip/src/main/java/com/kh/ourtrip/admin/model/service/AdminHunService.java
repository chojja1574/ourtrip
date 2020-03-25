package com.kh.ourtrip.admin.model.service;

import java.util.List;
import java.util.Map;

import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerCard;

public interface AdminHunService {

	
	
	
	/** 회원수 전체조회용 service
	 * @return listFullCount
	 * @throws Exception
	 */
	public abstract int getListFullCount()throws Exception;
	
	/** 회원 전체조회용 service
	 * @param pInf
	 * @return memberList
	 * @throws Exception
	 */
	public abstract List<Member> selectFullList(PageInfo pInf) throws Exception;
	
	
	/** 회원수 조회용 service
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	public abstract int getListCount(Map<String, String> map)throws Exception;

	/** 회원 목록조회용 service
	 * @param map
	 * @param pInf
	 * @return memberList
	 * @throws Exception
	 */
	public abstract List<Member> selectList(Map<String, String> map, PageInfo pInf)throws Exception;

	/** 회원 상세조회용 service
	 * @param no
	 * @return Member
	 * @throws Exception
	 */
	public abstract Member detail(int no)throws Exception;


	
	/** 플래너 넘버 조회용 service
	 * @param no
	 * @return plannerList
	 * @throws Exception
	 */
	public abstract List<Integer> plannerList(int no)throws Exception;

	/** 플래너 카드 조회용 service
	 * @param plannerList
	 * @param pInf
	 * @return plannerInfo
	 * @throws Exception
	 */
	public abstract List<Planner> plannerInfo(List<Integer> plannerList, PageInfo pInf)throws Exception;

	/** 지역조회용 service
	 * @param plannerList
	 * @return plannerArea
	 * @throws Exception
	 */
	public abstract List<PlannerCard> plannerArea(List<Integer> plannerList) throws Exception;

	



}
