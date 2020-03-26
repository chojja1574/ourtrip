package com.kh.ourtrip.planner.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ourtrip.planner.model.dao.PlannerDAOJYS;
import com.kh.ourtrip.planner.model.vo.AreaName;
import com.kh.ourtrip.planner.model.vo.LargeArea;
import com.kh.ourtrip.planner.model.vo.PlannerCard;
import com.kh.ourtrip.planner.model.vo.PlannerMember;
import com.kh.ourtrip.planner.model.vo.SmallArea;

@Service
public class PlannerServiceImplJYS implements PlannerServiceJYS{

	@Autowired
	private PlannerDAOJYS plannerDAO;

	/** 회원 수정중인 플래너 수 조회용 Service
	 * @param memberNo
	 * @return updatePlannerCount
	 * @throws Exception
	 */
	@Override
	public int updatePlannerCount(int memberNo) throws Exception {
		return plannerDAO.updatePlannerCount(memberNo);
	}

	/** 회원이 참여하고있는 플래너 번호 목록 조회용 Service
	 * @param memberNo
	 * @return plannerNoList
	 * @throws Exception
	 */
	@Override
	public List<PlannerMember> selectPlannerMember(int memberNo) throws Exception {
		return plannerDAO.selectPlannerMember(memberNo);
	}

	/** 수정중인 플래너 목록 조회용 Service
	 * @param memberNo
	 * @return uPlannerList
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> updatePlannerList(int memberNo) throws Exception {
		return plannerDAO.updatePlannerList(memberNo);
	}

	/** 완료된 플래너 목록 조회용 Service
	 * @param memberNo
	 * @return cPlannerList
	 * @throws Exception
	 */
	@Override
	public List<PlannerCard> completePlannerList(int memberNo) throws Exception {
		return plannerDAO.completePlannerList(memberNo);
	}

	/** 플래너 지역이름 조회용 Service
	 * @param noList
	 * @return areaNames
	 * @throws Exception
	 */
	@Override
	public List<AreaName> selectAreaNames(List<Integer> noList) throws Exception {
		return plannerDAO.selectAreaNames(noList);
	}

	/** 플래너 삭제용 Service
	 * @param delPlanner
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int delPlanner(PlannerMember delPlanner) throws Exception {
		// 회원이 삭제하려는 플래너에 맞는 권한인지 확인
		String permission = plannerDAO.selectPlannerPerm(delPlanner);
		
		int result = 0;
		if(permission.equals("3")) {
			delPlanner.setPlannerPermission(Integer.parseInt(permission));
			result = plannerDAO.delPlanner(delPlanner);
		}
		
		return result;
	}
	/** 대지역 목록 조회용 Service
	 * @return largeNmList
	 * @throws Exception
	 */

	@Override
	public List<LargeArea> selectLargeNmList() throws Exception {
		return plannerDAO.selectLargeNmList();
	}

	/** 소지역 목록 조회용 Service
	 * @return smallNmList
	 * @throws Exception
	 */
	@Override
	public List<SmallArea> selectsmallNmList() throws Exception {
		return plannerDAO.selectSmallNmList();
	}
	
	
	
}
