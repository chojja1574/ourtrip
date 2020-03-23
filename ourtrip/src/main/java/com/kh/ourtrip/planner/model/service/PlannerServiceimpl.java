package com.kh.ourtrip.planner.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.dao.PlannerDAO;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerMember;

@Service
public class PlannerServiceimpl implements PlannerService{
	
	@Autowired
	PlannerDAO plannerDAO;
	
	PlannerMember pMember;


	/**플레너 생성용 Service
	 * @param planner
	 * @return result
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createPlanner(Planner planner,Member member) throws Exception {
		
		pMember = new PlannerMember();
		int plannerNo = plannerDAO.selectNextNo();
		int result = 0;
		
		int insertPlanner = 0 ;
		int locationInsert = 0;
		int resultPMember = 0;
		
		planner.setPlannerNo(plannerNo);
		pMember.setMemberNo(member.getMemberNo());
		pMember.setPlannerNo(plannerNo);
		
		if(plannerNo > 0) {
			
			insertPlanner = plannerDAO.createPlanner(planner);
			locationInsert = plannerDAO.createLocation(planner);
			resultPMember = plannerDAO.createpMember(pMember);
		}else {
			return result;
		}
		
		if(insertPlanner >0 && locationInsert >0 && resultPMember>0 ) {
			result = 1;
		}
		
		
		return result;
	}

	
	
}
