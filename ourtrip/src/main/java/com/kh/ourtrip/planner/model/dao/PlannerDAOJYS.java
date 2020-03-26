package com.kh.ourtrip.planner.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.SmallArea;

@Repository
public class PlannerDAOJYS {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
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
		return sqlSession.selectList("plannerCardMapper2.selectPlannerMember", memberNo);
	}

	/** 플래너 목록 지역이름 조회용 DAO
	 * @param noList
	 * @return areaNames
	 * @throws Exception
	 */
	public List<AreaName> selectAreaNames(List<Integer> noList) throws Exception{
		return sqlSession.selectList("plannerCardMapper2.selectAreaNames", noList);
	}

	/** 수정중인 플래너 목록 조회용 DAO
	 * @param memberNo
	 * @return uPlannerList
	 * @throws Exception
	 */
	public List<PlannerCard> updatePlannerList(int memberNo) throws Exception{
		return sqlSession.selectList("plannerCardMapper2.updatePlannerList", memberNo);
	}

	/** 완료된 플래너 목록 조회용 DAO
	 * @param memberNo
	 * @return cPlannerList
	 * @throws Exception
	 */
	public List<PlannerCard> completePlannerList(int memberNo) throws Exception{
		return sqlSession.selectList("plannerCardMapper2.completePlannerList", memberNo);
	}

	/** 플래너 권한 조회용 DAO
	 * @param delPlanner
	 * @return memberPermission
	 * @throws Exception
	 */
	public String selectPlannerPerm(PlannerMember delPlanner) throws Exception{
		return sqlSession.selectOne("plannerCardMapper2.selectPlannerPerm", delPlanner);
	}
	
	/** 플래너 삭제용 DAO
	 * @param delPlanner
	 * @return result
	 * @throws Exception
	 */
	public int delPlanner(PlannerMember delPlanner) throws Exception{
		return sqlSession.update("plannerCardMapper2.delPlanner", delPlanner);
	}

	/** 대지역 목록 조회용 DAO
	 * @return largeNmList
	 * @throws Exception
	 */
	public List<LargeArea> selectLargeNmList() throws Exception{
		return sqlSession.selectList("plannerCardMapper2.selectLargeNmList");
	}

	/** 소지역 목록 조회용 DAO
	 * @return smallNmList
	 * @throws Exception
	 */
	public List<SmallArea> selectSmallNmList() throws Exception{
		return sqlSession.selectList("plannerCardMapper2.selectSmallNmList");
	}

}
