package com.kh.ourtrip.admin.model.dao;

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
public class AdminHunDAO {
	
	@Autowired
	public SqlSessionTemplate sqlSession;

	/** 회원수 총 조회용 DAO
	 * @return listFullCount
	 * @throws Exception
	 */
	public int getListFullCount() throws Exception{
		return sqlSession.selectOne("adminhunMapper.listFullCount");
	}
	
	/**
	 * @param pInf
	 * @return memberList
	 */
	public List<Member> selectFUllList(PageInfo pInf) {
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		return sqlSession.selectList("adminhunMapper.selectFullList",rowBounds);
	}
	
	
	/** 회원 수 조회용 DAO
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Map<String, String> map) throws Exception{
		
		return sqlSession.selectOne("adminhunMapper.listCount", map);
	}

	/** 회원 목록 조회용 DAO
	 * @param map
	 * @param pInf
	 * @return memberList
	 */
	public List<Member> selectList(Map<String, String> map, PageInfo pInf) {
		
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		
		return sqlSession.selectList("adminhunMapper.selectList",map,rowBounds);
	}

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
	public List<Integer> plannerList(int no) throws Exception{
		
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

	


}
