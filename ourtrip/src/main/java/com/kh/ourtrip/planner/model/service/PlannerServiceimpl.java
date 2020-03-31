package com.kh.ourtrip.planner.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.dao.PlannerDAO;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.SmallArea;

@Service
public class PlannerServiceimpl implements PlannerService{
	
	@Autowired
	PlannerDAO plannerDAO;
	
	PlannerMember pMember;


	/**플레너 생성용 Service
	 * @param planner member
	 * @return result
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createPlanner(Planner planner,Member member, String multiArea) throws Exception {
		
		pMember = new PlannerMember();
		int plannerNo = plannerDAO.selectNextNo();
		
		int result = 0;
		int insertPlanner = 0 ;
		int locationInsert = 0;
		int resultPMember = 0;
		
		String[] area = multiArea.split("/");
		
		System.out.println("area" + area);
		
		
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


	@Override
	public List<LargeArea> selectLargeNmList() throws Exception {
		return plannerDAO.selectLargeNmList();
	}


	@Override
	public List<SmallArea> selectsmallNmList() throws Exception {
		return plannerDAO.selectsmallNmList();
	}

	
	
}
