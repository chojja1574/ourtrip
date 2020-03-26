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

}
