package com.kh.ourtrip.admin.model.service;

import java.util.List;
import java.util.Map;

import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.member.model.vo.ProfileImage;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerInfo;

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
	public abstract List<PlannerInfo> plannerInfo(List<Integer> plannerList, PageInfo pInf)throws Exception;

	/** 지역조회용 service
	 * @param plannerList
	 * @return plannerArea
	 * @throws Exception
	 */
	public abstract List<AreaName> plannerArea(List<Integer> plannerList) throws Exception;

	/**프로필 이미지 조회용 service
	 * @param no
	 * @return pi
	 * @throws Exception
	 */
	public abstract ProfileImage selectProfileImage(int no)throws Exception;

	/**플래너 목록조회용 service
	 * @param pInf 
	 * @return totalList
	 * @throws Exception
	 */
	public abstract List<PlannerInfo> plannerTotal(PageInfo pInf)throws Exception;

	/**플래너 개수 조회용 service
	 * @return int 
	 * @throws Exception
	 */
	public abstract int plannerCount()throws Exception;

	/**플래너 위치조회용 service
	 * @return list<areaName>
	 * @throws Exception
	 */
	public abstract List<AreaName> areaList()throws Exception;

	
	/**여행일자 조회용 service
	 * @return list<day>
	 * @throws Exception
	 */
	public abstract List<Day> dayList()throws Exception;

	
	/** 검색후 플래너 count용 service
	 * @param keyword
	 * @return searchResultcount
	 * @throws Exception
	 */
	public abstract List<Integer> resultCount(Map<String, Object> keyword)throws Exception;

	/** 검색결과 조회 service
	 * @param pInf
	 * @param keyword
	 * @return List searchResult
	 * @throws Exception
	 */
	public abstract List<PlannerInfo> searchResult(PageInfo pInf, Map<String, Object> keyword)throws Exception;

	/** planner별 검색 지역명 조회용 service
	 * @param searchResultcount
	 * @param keyword
	 * @return AreaList
	 * @throws Exception
	 */
	public abstract List<AreaName> resultArea(List<Integer> searchResultcount) throws Exception;

	/** planner별 검색 날짜 조회용 service
	 * @param searchResultcount
	 * @return List<Day> dayList
	 * @throws Exception
	 */
	public abstract List<Day> resultDay(List<Integer> searchResultcount)throws Exception;

	
	



}
