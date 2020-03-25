package com.kh.ourtrip.planner.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.PlannerCard;

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

}
