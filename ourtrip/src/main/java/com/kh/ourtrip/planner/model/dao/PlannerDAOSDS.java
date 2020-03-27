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
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.SmallArea;

@Repository
public class PlannerDAOSDS {
	
	@Autowired
	public SqlSessionTemplate sqlSession;
	
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

}
