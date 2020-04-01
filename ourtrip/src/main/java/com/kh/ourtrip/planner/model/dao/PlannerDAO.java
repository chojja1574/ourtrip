package com.kh.ourtrip.planner.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.ChattingLogView;
import com.kh.ourtrip.planner.model.vo.Day;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.PlannerMemberView;
import com.kh.ourtrip.planner.model.vo.PlannerView;
import com.kh.ourtrip.planner.model.vo.Schedule;
import com.kh.ourtrip.planner.model.vo.SmallArea;

@Repository("PlannerDAO")
public class PlannerDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/** 플래너 번호 조회용 DAO
	 * @return palnnerNo test
	 * @throws Exception
	 */
	public int selectNextNo() throws Exception{
		
		return sqlSessionTemplate.selectOne("plannerMapper.selectNextNo");
	}
	/**플래너생성용 DAO
	 * @param planner
	 * @return result 
	 * @throws Exception
	 */
	public int createPlanner(Planner planner) throws Exception{
		return sqlSessionTemplate.insert("plannerMapper.createPlanner",planner);
	}
	
	
	/** 로케이션 입력용 DAO
	 * @param aName
	 * @return locationInsert
	 * @throws Exception
	 */
	public int createLocation(List<AreaName> aName) throws Exception{
		return sqlSessionTemplate.insert("plannerMapper.createLocation",aName);
	}
	
	/** 멤버 플레너 입력용 DAO
	 * @param pMember
	 * @returnresultPMember
	 * @throws Exception
	 */
	public int createpMember(PlannerMember pMember)throws Exception{
		return sqlSessionTemplate.insert("plannerMapper.createpMember",pMember);
	}
	
	public List<LargeArea> selectLargeNmList()throws Exception {
		return sqlSessionTemplate.selectList("plannerMapper.LargeNmList");
	}
	
	public List<SmallArea> selectsmallNmList()throws Exception {
		return sqlSessionTemplate.selectList("plannerMapper.SmallNmList");
	}
	// @author 신덕수
	/** 추천리스트 조회용 DAO
	 * @return
	 * @throws Exception
	 */
	public List<PlannerCard> selectRecommendPCList() throws Exception{
		return sqlSessionTemplate.selectList("plannerCardMapper.selectRecommendPCList");
	}

	/** 지역리스트 호출용 DAO
	 * @param rListNo
	 * @return aListNo
	 * @throws Exception
	 */
	public List<AreaName> selectAreaNames(List<Integer> rListNo) throws Exception {
		return sqlSessionTemplate.selectList("plannerCardMapper.selectAreaNames", rListNo);
	}

	/** 플래너 번호조회용 + 검색(제목, 그룹) DAO
	 * @param map
	 * @return pList
	 * @throws Exception
	 */
	public List<Integer> getPListNo(Map<String, Object> map) throws Exception{
		return sqlSessionTemplate.selectList("plannerCardMapper.getPListNo", map);
	}

	
	
	/** 플래너 번호조회용 + 검색(대지역, 소지역) DAO
	 * @param pListNo
	 * @return aList
	 * @throws Exception
	 */
	public List<AreaName> getAList(Map<String, Object> map) throws Exception{
		return sqlSessionTemplate.selectList("plannerCardMapper.getAList", map);
	}

	/** 경유여부체크되어있을경우 지역필터링
	 * @param map
	 * @return rListNo
	 * @throws Exception
	 */
	public List<Integer> getRListNo(Map<String, Object> map) throws Exception {
		return sqlSessionTemplate.selectList("plannerCardMapper.getRList", map);
	}

	/** 검색된 플래너 목록 조회
	 * @param map
	 * @param pInf
	 * @return pList
	 * @throws Exception
	 */
	public List<PlannerCard> selectPList(Map<String, Object> map, PageInfo pInf) throws Exception{
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		return sqlSessionTemplate.selectList("plannerCardMapper.selectPList", map, rowBounds);
	}

	/** 소지역 목록 조회용 DAO
	 * @return smallNmList
	 * @throws Exception
	 */
	public List<SmallArea> selectSmallNmList() throws Exception{
		return sqlSessionTemplate.selectList("plannerCardMapper.selectSmallNmList");
	}
	
	/** 회원 수정중인 플래너 수 조회용DAO
	 * @param memberNo
	 * @return updatePlannerCount
	 * @throws Exception
	 */
	public int updatePlannerCount(int memberNo) throws Exception{
		return sqlSessionTemplate.selectOne("plannerCardMapper.updatePlannerCount", memberNo);
	}

	/** 회원이 참여하고있는 플래너 번호 목록 조회용 DAO
	 * @param memberNo
	 * @return plannerNoList
	 * @throws Exception
	 */
	public List<PlannerMember> selectPlannerMember(int memberNo) throws Exception{
		return sqlSessionTemplate.selectList("plannerCardMapper.selectPlannerMember", memberNo);
	}

	/** 수정중인 플래너 목록 조회용 DAO
	 * @param memberNo
	 * @return uPlannerList
	 * @throws Exception
	 */
	public List<PlannerCard> updatePlannerList(int memberNo) throws Exception{
		return sqlSessionTemplate.selectList("plannerCardMapper.updatePlannerList", memberNo);
	}

	/** 완료된 플래너 목록 조회용 DAO
	 * @param memberNo
	 * @return cPlannerList
	 * @throws Exception
	 */
	public List<PlannerCard> completePlannerList(int memberNo) throws Exception{
		return sqlSessionTemplate.selectList("plannerCardMapper.completePlannerList", memberNo);
	}

	/** 플래너 권한 조회용 DAO
	 * @param delPlanner
	 * @return memberPermission
	 * @throws Exception
	 */
	public String selectPlannerPerm(PlannerMember delPlanner) throws Exception{
		return sqlSessionTemplate.selectOne("plannerCardMapper.selectPlannerPerm", delPlanner);
	}
	
	/** 플래너 삭제용 DAO
	 * @param delPlanner
	 * @return result
	 * @throws Exception
	 */
	public int delPlanner(PlannerMember delPlanner) throws Exception{
		return sqlSessionTemplate.update("plannerCardMapper.delPlanner", delPlanner);
	}

	/** 플래너 나가기용 DAO
	 * @param outPlanner
	 * @return result
	 * @throws Exception
	 */
	public int outPlanner(PlannerMember outPlanner) throws Exception{
		return sqlSessionTemplate.delete("plannerCardMapper.outPlanner", outPlanner);
	}
	
	// @author 박지현
	public List<PlannerView> selectPlannerView(String no) throws Exception{
		return sqlSessionTemplate.selectList("planner1Mapper.selectPlannerView",no);
	}
	
	public List<PlannerView> selectPlannerViewUseNo(int no) throws Exception{
		return sqlSessionTemplate.selectList("planner1Mapper.selectPlannerViewUseNo",no);
	}

	public int getNextDateNo() throws Exception {
		return sqlSessionTemplate.selectOne("planner1Mapper.selectDateNo");
	}
	public int getNextScheduleNo() throws Exception {
		return sqlSessionTemplate.selectOne("planner1Mapper.selectScheduleNo");
	}

	public int insertDate(Day day) throws Exception {
		return sqlSessionTemplate.insert("planner1Mapper.insertDate", day);
	}

	public int updateTripDate(List<Day> dayList) throws Exception {
		return sqlSessionTemplate.update("planner1Mapper.updateTripDate", dayList);
	}

	public int insertDefaultSchedule(Schedule schedule) throws Exception {
		return sqlSessionTemplate.insert("planner1Mapper.insertDefaultSchedule", schedule);
	}

	public int deleteDate(int dateNo) throws Exception {
		return sqlSessionTemplate.delete("planner1Mapper.deleteDate", dateNo);
	}

	public int updateSchedule(Schedule sche) throws Exception {
		return sqlSessionTemplate.update("planner1Mapper.updateSchedule", sche);
	}

	public int insertSchedule(Schedule sche) throws Exception {
		return sqlSessionTemplate.insert("planner1Mapper.insertSchedule", sche);
	}

	public int deleteSchedule(int sno) throws Exception {
		return sqlSessionTemplate.delete("planner1Mapper.deleteSchedule", sno);
	}

	public int insertChattingLog(ChattingLogView chatLog) throws Exception {
		return sqlSessionTemplate.insert("planner1Mapper.insertChattingLog", chatLog);
	}

	public List<ChattingLogView> selectChatList(int no) throws Exception {
		return sqlSessionTemplate.selectList("planner1Mapper.selectChatList", no);
	}

	public List<PlannerMemberView> selectPlannerMemeberListUsePlannerNo(int pno) {
		return sqlSessionTemplate.selectList("planner1Mapper.selectPlanerMemeberListUsePlannerNo", pno);
	}
	
	public int selectPlannerMemeberExist(PlannerMemberView pm) {
		return sqlSessionTemplate.selectOne("planner1Mapper.selectPlanerMemeberExist", pm);
	}

	public int insertPlannerMember(PlannerMember pm) {
		return sqlSessionTemplate.insert("planner1Mapper.insertPlannerMember", pm);
	}

	public int updatePermission(PlannerMember pm) {
		return sqlSessionTemplate.update("planner1Mapper.updatePermission", pm);
	}

	public int updateSumCost(Planner p) {
		return sqlSessionTemplate.update("planner1Mapper.updateSumCost", p);
	}

	public int updateStartDate(Planner p) {
		return sqlSessionTemplate.update("planner1Mapper.updateStartDate", p);
	}
	
	public int countDate(int pno) throws Exception {
		return sqlSessionTemplate.selectOne("planner1Mapper.countDate",pno);
	}
	
	public int countSchedule(int dno) throws Exception {
		return sqlSessionTemplate.selectOne("planner1Mapper.countSchedule",dno);
	}

	public int updateTitle(Planner planner) {
		return sqlSessionTemplate.update("planner1Mapper.updateTitle", planner);
	}
	
	public int updatePassword(Planner planner) {
		return sqlSessionTemplate.update("planner1Mapper.updatePassword", planner);
	}
	
	public int updatePublic(Planner planner) {
		return sqlSessionTemplate.update("planner1Mapper.updatePublic", planner);
	}
	
	public int clearUserList(int no) {
		return sqlSessionTemplate.delete("planner1Mapper.cleanUserList", no);
	}

	public List<AreaName> selectPlannerLocationName(Integer no) {
		return sqlSessionTemplate.selectList("planner1Mapper.selectPlannerLocationName", no);
	}

	public int deletePlannerLocation(int no) {
		return sqlSessionTemplate.delete("planner1Mapper.deletePlannerLocation", no);
	}
	
	public int updateLocationList(List<AreaName> locationList) {
		return sqlSessionTemplate.insert("planner1Mapper.insertLocationList", locationList);
	}

	public int updateGroup(Planner planner) {
		return sqlSessionTemplate.update("planner1Mapper.updateGroup", planner);
	}
	/** 플래너 번호 조회용 DAO
	 * @return palnnerNo test
	 * @throws Exception
	 */
	public int selectNextNo2() throws Exception{
		
		return sqlSessionTemplate.selectOne("plannerCardMapper.selectNextNo");
	}
	/**플래너생성용 DAO
	 * @param planner
	 * @return result 
	 * @throws Exception
	 */
	public int createPlanner2(Planner planner) throws Exception{
		return sqlSessionTemplate.insert("plannerCardMapper.createPlanner",planner);
	}

	/** 플래너 날짜 복사용 DAO
	 * @param day
	 * @return result
	 * @throws Exception
	 */
	public int copyDate(Day day) throws Exception{
		return sqlSessionTemplate.insert("plannerCardMapper.copyDate", day);
	}

	/** 플래너 지역 복사용 DAO
	 * @param no
	 * @return areaNameList
	 * @throws Exception
	 */
	public List<AreaName> selectAreaNamePlanner(int no) throws Exception{
		return sqlSessionTemplate.selectList("plannerCardMapper.selectAreaNamePlanner", no);
	}

	/** 지역이름 추가용 DAO
	 * @param areaName
	 * @return result
	 * @throws Exception
	 */
	public int insertAreaName(AreaName areaName) throws Exception{
		return sqlSessionTemplate.insert("plannerCardMapper.insertAreaName", areaName);
	}
	public int increasePlannerCount(int plannerNo) {
		return sqlSessionTemplate.update("planner1Mapper.increasePlannerCount",plannerNo);
	}


	
	
}
