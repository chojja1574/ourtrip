package com.kh.ourtrip.admin.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


@Repository
public class AdminDAO {

	@Autowired
	public SqlSessionTemplate sqlSession;
	
	/** 방문자 날짜 조회용 DAO
	 * @return vList
	 * @throws Exception
	 */
	public List<Date> getVisitDateList() throws Exception{
		return sqlSession.selectList("adminMapper.getVisitDateList");
	}

	/** 플래너 생성 날짜 조회용 DAO
	 * @return pList
	 * @throws Exception
	 */
	public List<Date> getPlannerDateList() throws Exception{
		return sqlSession.selectList("adminMapper.getPlannerDateList");
	}
	
	/** 총 회원 수 조회용 DAO
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Map<String, Object> map) throws Exception{
		
		return sqlSession.selectOne("adminhunMapper.getlistCount", map);
	}
	
	// 삭제 예정
	/** 회원수 총 조회용 DAO + 검색
	 * @return listFullCount
	 * @throws Exception
	 */
	public int getListFullCount() throws Exception{
		return sqlSession.selectOne("adminhunMapper.listFullCount");
	}
	
	/** 회원목록 조회용 + 검색
	 * @param pInf
	 * @return memberList
	 */
	public List<Member> selectList(Map<String, Object> map, PageInfo pInf) throws Exception{
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		return sqlSession.selectList("adminhunMapper.selectList", map, rowBounds);
	}
	
	
	// 삭제 예정
	/** 회원 목록 조회용 DAO
	 * @param map
	 * @param pInf
	 * @return memberList
	 */
	/*
	 * public List<Member> selectList(Map<String, String> map, PageInfo pInf) {
	 * 
	 * int offset = (pInf.getCurrentPage()-1) * pInf.getLimit(); RowBounds rowBounds
	 * = new RowBounds(offset, pInf.getLimit());
	 * 
	 * return sqlSession.selectList("adminhunMapper.selectList",map,rowBounds); }
	 */

	/** 회원 정보 조회용 DAO
	 * @param no
	 * @return detailMember
	 * @throws Exception
	 */
	public Member detail(int no) throws Exception{
		return sqlSession.selectOne("adminhunMapper.detail", no);
	}

	
	/** 회원 플래너 조회용 DAO
	 * @param no
	 * @return plannerList
	 * @throws Exception
	 */
	public List<PlannerCard> plannerList(int no) throws Exception{
		
		return sqlSession.selectList("adminhunMapper.plannerList",no);
	}

	/** 플래너 페이징 처리용 DAO
	 * @param plannerList
	 * @param pInf
	 * @return list
	 * @throws Exception
	 */
	public List<PlannerCard> plannerInfo(List<Integer> plannerList, PageInfo pInf) throws Exception{
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		
		return sqlSession.selectList("adminhunMapper.plannerInfo", plannerList ,rowBounds);
	}

	
	/**지역조회용 DAO
	 * @param plannerList
	 * @return list PlannerCard
	 * @throws Exception
	 */
	public List<AreaName> plannerArea(List<Integer> plannerList) throws Exception{
		
		return sqlSession.selectList("adminhunMapper.plannerArea" , plannerList);
	}

	/** 프로필 이미지 조회용 DAO
	 * @param no
	 * @return pi
	 * @throws Exception
	 */
	public ProfileImage selectProfileImage(int no) throws Exception{
		return sqlSession.selectOne("adminhunMapper.selectProfileImage" , no);
	}

	/** 전체 플래너 리스트 조회용 DAO
	 * @param pInf
	 * @return list<PlannerInfo>
	 */
	public List<PlannerInfo> plannerTotal(PageInfo pInf) {
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		return sqlSession.selectList("adminhunMapper.plannerTotal" , rowBounds);
	}

	
	/** 플래너 카운트 조회용 DAO
	 * @return int 
	 * @throws Exception
	 */
	public int plannerCount() throws Exception{
		return sqlSession.selectOne("adminhunMapper.plannerCount");
	}


	/** 플래너 날짜 조회용 DAO
	 * @return List<Day>
	 * @throws Exception
	 */
	public List<Day> dayList()throws Exception{
		return sqlSession.selectList("adminhunMapper.dayList");
	}

	public List<AreaName> resultArea(List<Integer> searchResultcount) throws Exception{
		return sqlSession.selectList("adminhunMapper.resultArea", searchResultcount);
	}

	public List<Day> resultDay(List<Integer> searchResultcount) throws Exception{
		return sqlSession.selectList("adminhunMapper.resultDay", searchResultcount);
	}

	public PlannerInfo plannerDetail(int no)throws Exception {
		return sqlSession.selectOne("adminhunMapper.plannerDetail" ,no);
	}

	public List<AreaName> areaDetail(int no) throws Exception{
		return sqlSession.selectList("adminhunMapper.areaDetail" , no);
	}

	public int deletePlanner(int plannerNo) throws Exception{
		return sqlSession.update("adminhunMapper.deletePlanner", plannerNo);
	}

	public  int reason(PlannerDeleteReason pdr)throws Exception {
		return sqlSession.insert("adminhunMapper.reason" , pdr);
	}

	public int recoveryPlanner(int plannerNo) {
		return sqlSession.update("adminhunMapper.recovery", plannerNo);
	}
	public int memberDelete(int memberNo) {
		return sqlSession.update("adminhunMapper.memberDelete" , memberNo);
	}

	public List<LargeArea> selectLargeNmList() {
		return sqlSession.selectList("adminhunMapper.LargeNmList");
	}

	public List<SmallArea> selectsmallNmList() {
		return sqlSession.selectList("adminhunMapper.SmallNmList");
	}
	public List<AreaName> totalAList() {
		return sqlSession.selectList("adminhunMapper.totalAList");
	}

	public List<PlannerInfo> searchList(Map<String, Object> keyword) {
		return sqlSession.selectList("adminhunMapper.searchList",keyword);
	}

	public List<AreaName> areaInfo(Map<String, Object> keyword) {
		return sqlSession.selectList("adminhunMapper.areaInfo", keyword);
	}

	public List<PlannerInfo> plannerInfo(Map<String, Object> keyword, PageInfo pInf) {
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		return sqlSession.selectList("adminhunMapper.plannerInfomaion" , keyword ,rowBounds);
	}

	/** 회원 복구용 DAO
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int memberRecovery(int memberNo) throws Exception {
		return sqlSession.update("adminhunMapper.memberRecovery", memberNo);
	}

	/** 플래너 개수 조회용 DAO
	 * @param keyword
	 * @return pList
	 * @throws Exception
	 */
	public List<Integer> getPlannerList(Map<String, Object> keyword) throws Exception{
		return sqlSession.selectList("adminhunMapper.getPlannerListCount", keyword);
	}

	/** 플래너에 맞는 지역명 조회용 DAO
	 * @param pListNo
	 * @return areaNameList
	 * @throws Exception
	 */
	public List<AreaName> getAreaNameList(Map<String, Object> keyword) throws Exception{
		return sqlSession.selectList("adminhunMapper.getAreaNameList", keyword);
	}

	/** 플래너 지역명 필터 조회용 DAO
	 * @param keyword
	 * @return areaFilterList
	 * @throws Exception
	 */
	public List<Integer> getAreaFilterList(Map<String, Object> keyword) throws Exception{
		return sqlSession.selectList("adminhunMapper.getAreaFilterList", keyword);
	}

	/** 플래너 Info 조회용 DAO
	 * @param keyword
	 * @param pInf
	 * @return pInfoList
	 * @throws Exception
	 */
	public List<PlannerCard> selectPlannerList(Map<String, Object> keyword, PageInfo pInf) throws Exception{
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		return sqlSession.selectList("adminhunMapper.selectPlannerList", keyword, rowBounds);
	}


}
