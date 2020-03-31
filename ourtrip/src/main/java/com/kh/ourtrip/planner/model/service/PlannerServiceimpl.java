package com.kh.ourtrip.planner.model.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.dao.PlannerDAO;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.Planner;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.SmallArea;

@Service
public class PlannerServiceimpl implements PlannerService {

	@Autowired
	PlannerDAO plannerDAO;

	PlannerMember pMember;

	/**
	 * 플레너 생성용 Service
	 * 
	 * @param planner member
	 * @return result
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createPlanner(Planner planner) throws Exception {

		int result = 0;
		int plannerNo = plannerDAO.selectNextNo();

		planner.setPlannerNo(plannerNo);

		result = plannerDAO.createPlanner(planner);
		if (result > 0) {
			result = plannerNo;
		}
		
		return result;
	}

	/**
	 * 플래너 지역입력용 service
	 * 
	 * @param jarr
	 * @return result
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createLocation(JSONArray jarr, int plannerNo) throws Exception {

		List<AreaName> aName = new ArrayList<AreaName>();

		JSONObject jobj = new JSONObject();
		for (int i = 0; i < jarr.size(); i++) {
			jobj = (JSONObject) jarr.get(i);
			AreaName areaName = new AreaName();
			areaName.setSmallAreaCode(Integer.parseInt(jobj.get("small").toString()));
			areaName.setLargeAreaCode(Integer.parseInt(jobj.get("large").toString()));
			areaName.setPlannerNo(plannerNo);
			aName.add(areaName);
		}

		return plannerDAO.createLocation(aName);
	}

	/**
	 * 플래너_맴버 service
	 * 
	 * @param plannerNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createMember(int plannerNo, int memberNo) throws Exception {

		PlannerMember pMember = new PlannerMember();
		pMember.setMemberNo(memberNo);
		pMember.setPlannerNo(plannerNo);
		
		return plannerDAO.createpMember(pMember);
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
