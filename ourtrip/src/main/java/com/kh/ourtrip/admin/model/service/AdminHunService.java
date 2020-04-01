package com.kh.ourtrip.admin.model.service;

import java.util.List;
import java.util.Map;

import com.kh.ourtrip.admin.model.vo.PlannerDeleteReason;
import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.member.model.vo.ProfileImage;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerInfo;
import com.kh.ourtrip.planner.model.vo.SmallArea;

public interface AdminHunService {
	
	
	/** 회원수 전체 + 검색 조회용 service
	 * @return listCount
	 * @throws Exception
	 */
	public abstract int getListCount(Map<String, Object> map) throws Exception;
	
	/** 회원 전체조회용 service
	 * @param pInf
	 * @return memberList
	 * @throws Exception
	 */
	public abstract List<Member> selectList(Map<String, Object> map, PageInfo pInf) throws Exception;
	

	/** 회원 상세조회용 service
	 * @param no
	 * @return Member
	 * @throws Exception
	 */
	public abstract Member detail(int no)throws Exception;

	
	/** 회원 복구용 service
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public abstract int memberRecovery(int memberNo) throws Exception;

	//------------------------회원 --------------------//
	
	/** 플래너 넘버 조회용 service
	 * @param no
	 * @return plannerList
	 * @throws Exception
	 */
	public abstract List<PlannerCard> plannerList(int no)throws Exception;

	/** 플래너 카드 조회용 service
	 * @param plannerList
	 * @param pInf
	 * @return plannerInfo
	 * @throws Exception
	 */
	public abstract List<PlannerCard> plannerInfo(List<Integer> plannerList, PageInfo pInf)throws Exception;

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

	
	/**여행일자 조회용 service
	 * @return list<day>
	 * @throws Exception
	 */
	public abstract List<Day> dayList()throws Exception;



	/** 플래너 상세보기 용 service
	 * @param no
	 * @return plannerInfo
	 * @throws Exception
	 */
	public abstract PlannerInfo plannerDetail(int no)throws Exception;

	/** 지역 조회용 service
	 * @param no
	 * @return areaName
	 * @throws Exception
	 */
	public abstract List<AreaName> areaDetail(int no)throws Exception;

	
	/** 플래너 삭제용 service
	 * @param plannerNo
	 * @return result
	 * @throws Exception
	 */
	public abstract int deletePlanner(int plannerNo)throws Exception;

	/** 삭제 목록등록 service
	 * @param pdr
	 * @return reason
	 * @throws Exception
	 */
	public abstract int reason(PlannerDeleteReason pdr)throws Exception;

	/**  삭제메일 전송용 service
	 * @param email
	 * @param pdr 
	 * @return sendEmail
	 * @throws Exception
	 */
	public abstract void sendEmail(String email, PlannerDeleteReason pdr)throws Exception;

	/** 플래너 복구용 service
	 * @param plannerNo
	 * @return result 
	 * @throws Exception
	 */
	public abstract int recoveryPlanner(int plannerNo) throws Exception;

	/** 회원 강퇴용 service
	 * @param memberNo
	 * @param email
	 * @param delBecause
	 * @return result
	 * @throws Exception
	 */
	public abstract int memberDelete(int memberNo, String email, String delBecause)throws Exception;

	/** 대지역명 조회용 service
	 * @return list
	 * @throws Exception
	 */
	public abstract List<LargeArea> selectLargeNmList()throws Exception;

	/** 소지역명 조회용 service
	 * @return list
	 * @throws Exception
	 */
	public abstract List<SmallArea> selectsmallNmList()throws Exception;
	/** 전체 플래너 리스트 지역명 조회용 service
	 * @return list
	 * @throws Exception
	 */
	public abstract List<AreaName> totalAList()throws Exception;

	/** plannerInfo 검색용 service
	 * @param keyword
	 * @return searchList
	 * @throws Exception
	 */
	public abstract List<PlannerInfo> searchList(Map<String, Object> keyword)throws Exception;

	/** areaInfo 검색용 service
	 * @param keyword
	 * @return areaInfo
	 * @throws Exception
	 */
	public abstract List<AreaName> areaInfo(Map<String, Object> keyword)throws Exception;

	/** 검색결과, 페이징 처리용 serivce
	 * @param keyword
	 * @param pInf
	 * @return plannerInfo
	 * @throws Exception
	 */
	public abstract List<PlannerInfo> plannerInfo(Map<String, Object> keyword, PageInfo pInf)throws Exception;

	/** 플래너 개수 조회용 service
	 * @param keyword
	 * @return pListCount
	 * @throws Exception
	 */
	public abstract List<Integer> getPlannerListCount(Map<String, Object> keyword) throws Exception;

	/** 플래너 목록 조회용 Service
	 * @param keyword
	 * @param pInf
	 * @return plannerList
	 * @throws Exception
	 */
	public abstract List<PlannerCard> selectPlannerList(Map<String, Object> keyword, PageInfo pInf) throws Exception;

}
