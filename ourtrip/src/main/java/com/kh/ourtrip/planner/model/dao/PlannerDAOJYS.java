package com.kh.ourtrip.planner.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerMember;

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

}
