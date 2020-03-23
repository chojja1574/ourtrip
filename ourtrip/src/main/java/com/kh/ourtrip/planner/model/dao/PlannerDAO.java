package com.kh.ourtrip.planner.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerMember;

@Repository("PlannerDAO")
public class PlannerDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

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
	 * @param planner
	 * @return locationInsert
	 * @throws Exception
	 */
	public int createLocation(Planner planner) throws Exception{
		return sqlSessionTemplate.insert("plannerMapper.createLocation",planner);
	}
	
	
	/** 멤버 플레너 입력용 DAO
	 * @param pMember
	 * @returnresultPMember
	 * @throws Exception
	 */
	public int createpMember(PlannerMember pMember)throws Exception{
		return sqlSessionTemplate.insert("plannerMapper.createpMember",pMember);
	}


	
	
}
