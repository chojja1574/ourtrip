package com.kh.ourtrip.planner.model.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.common.vo.PageInfo;
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

@Repository
public class PlannerDAOSDS {
	
	@Autowired
	public SqlSessionTemplate sqlSession;
	
	// @author 신덕수
	/** 추천리스트 조회용 DAO
	 * @return
	 * @throws Exception
	 */
	public List<PlannerCard> selectRecommendPCList() throws Exception{
		return sqlSession.selectList("plannerCardMapper.selectRecommendPCList");
	}

	/** 지역리스트 호출용 DAO
	 * @param rListNo
	 * @return aListNo
	 * @throws Exception
	 */
	public List<AreaName> selectAreaNames(List<Integer> rListNo) throws Exception {
		return sqlSession.selectList("plannerCardMapper.selectAreaNames", rListNo);
	}

	/** 플래너 번호조회용 + 검색(제목, 그룹) DAO
	 * @param map
	 * @return pList
	 * @throws Exception
	 */
	public List<Integer> getPListNo(Map<String, Object> map) throws Exception{
		return sqlSession.selectList("plannerCardMapper.getPListNo", map);
	}

	
	
	/** 플래너 번호조회용 + 검색(대지역, 소지역) DAO
	 * @param pListNo
	 * @return aList
	 * @throws Exception
	 */
	public List<AreaName> getAList(Map<String, Object> map) throws Exception{
		return sqlSession.selectList("plannerCardMapper.getAList", map);
	}

	/** 경유여부체크되어있을경우 지역필터링
	 * @param map
	 * @return rListNo
	 * @throws Exception
	 */
	public List<Integer> getRListNo(Map<String, Object> map) throws Exception {
		return sqlSession.selectList("plannerCardMapper.getRList", map);
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
		return sqlSession.selectList("plannerCardMapper.selectPList", map, rowBounds);
	}
	
	// @author 조유상
	/** 대지역 목록 조회용 DAO
	 * @return largeNmList
	 * @throws Exception
	 */
	public List<LargeArea> selectLargeNmList() throws Exception{
		return sqlSession.selectList("plannerCardMapper.selectLargeNmList");
	}

	/** 소지역 목록 조회용 DAO
	 * @return smallNmList
	 * @throws Exception
	 */
	public List<SmallArea> selectSmallNmList() throws Exception{
		return sqlSession.selectList("plannerCardMapper.selectSmallNmList");
	}
	
	/** 회원 수정중인 플래너 수 조회용DAO
	 * @param memberNo
	 * @return updatePlannerCount
	 * @throws Exception
	 */
	public int updatePlannerCount(int memberNo) throws Exception{
		return sqlSession.selectOne("plannerCardMapper.updatePlannerCount", memberNo);
	}

	/** 회원이 참여하고있는 플래너 번호 목록 조회용 DAO
	 * @param memberNo
	 * @return plannerNoList
	 * @throws Exception
	 */
	public List<PlannerMember> selectPlannerMember(int memberNo) throws Exception{
		return sqlSession.selectList("plannerCardMapper.selectPlannerMember", memberNo);
	}

	/** 수정중인 플래너 목록 조회용 DAO
	 * @param memberNo
	 * @return uPlannerList
	 * @throws Exception
	 */
	public List<PlannerCard> updatePlannerList(int memberNo) throws Exception{
		return sqlSession.selectList("plannerCardMapper.updatePlannerList", memberNo);
	}

	/** 완료된 플래너 목록 조회용 DAO
	 * @param memberNo
	 * @return cPlannerList
	 * @throws Exception
	 */
	public List<PlannerCard> completePlannerList(int memberNo) throws Exception{
		return sqlSession.selectList("plannerCardMapper.completePlannerList", memberNo);
	}

	/** 플래너 권한 조회용 DAO
	 * @param delPlanner
	 * @return memberPermission
	 * @throws Exception
	 */
	public String selectPlannerPerm(PlannerMember delPlanner) throws Exception{
		return sqlSession.selectOne("plannerCardMapper.selectPlannerPerm", delPlanner);
	}
	
	/** 플래너 삭제용 DAO
	 * @param delPlanner
	 * @return result
	 * @throws Exception
	 */
	public int delPlanner(PlannerMember delPlanner) throws Exception{
		return sqlSession.update("plannerCardMapper.delPlanner", delPlanner);
	}

	/** 플래너 나가기용 DAO
	 * @param outPlanner
	 * @return result
	 * @throws Exception
	 */
	public int outPlanner(PlannerMember outPlanner) throws Exception{
		return sqlSession.delete("plannerCardMapper.outPlanner", outPlanner);
	}
	
	// @author 박지현
	public List<PlannerView> selectPlannerView(int no) throws Exception{
		return sqlSession.selectList("planner1Mapper.selectPlannerView",no);
	}

	public int getNextDateNo() throws Exception {
		return sqlSession.selectOne("planner1Mapper.selectDateNo");
	}
	public int getNextScheduleNo() throws Exception {
		return sqlSession.selectOne("planner1Mapper.selectScheduleNo");
	}

	public int insertDate(Day day) throws Exception {
		return sqlSession.insert("planner1Mapper.insertDate", day);
	}

	public int updateTripDate(List<Day> dayList) throws Exception {
		return sqlSession.update("planner1Mapper.updateTripDate", dayList);
	}

	public int insertDefaultSchedule(Schedule schedule) throws Exception {
		return sqlSession.insert("planner1Mapper.insertDefaultSchedule", schedule);
	}

	public int deleteDate(int dateNo) throws Exception {
		return sqlSession.delete("planner1Mapper.deleteDate", dateNo);
	}

	public int updateSchedule(Schedule sche) throws Exception {
		return sqlSession.update("planner1Mapper.updateSchedule", sche);
	}

	public int insertSchedule(Schedule sche) throws Exception {
		return sqlSession.insert("planner1Mapper.insertSchedule", sche);
	}

	public int deleteSchedule(int sno) throws Exception {
		return sqlSession.delete("planner1Mapper.deleteSchedule", sno);
	}

	public int insertChattingLog(ChattingLogView chatLog) throws Exception {
		return sqlSession.insert("planner1Mapper.insertChattingLog", chatLog);
	}

	public List<ChattingLogView> selectChatList(int no) throws Exception {
		return sqlSession.selectList("planner1Mapper.selectChatList", no);
	}

	public List<PlannerMemberView> selectPlannerMemeberListUsePlannerNo(int pno) {
		return sqlSession.selectList("planner1Mapper.selectPlanerMemeberListUsePlannerNo", pno);
	}
	
	public int selectPlannerMemeberExist(PlannerMemberView pm) {
		return sqlSession.selectOne("planner1Mapper.selectPlanerMemeberExist", pm);
	}

	public int insertPlannerMemeber(PlannerMember pm) {
		return sqlSession.insert("planner1Mapper.insertPlannerMember", pm);
	}

	public int updatePermission(PlannerMember pm) {
		return sqlSession.update("planner1Mapper.updatePermission", pm);
	}

	public int updateSumCost(Planner p) {
		return sqlSession.update("planner1Mapper.updateSumCost", p);
	}

	public int updateStartDate(Planner p) {
		return sqlSession.update("planner1Mapper.updateStartDate", p);
	}

}
