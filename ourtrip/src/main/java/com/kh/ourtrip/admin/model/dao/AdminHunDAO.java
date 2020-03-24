package com.kh.ourtrip.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.planner.model.vo.Planner;

@Repository
public class AdminHunDAO {
	
	@Autowired
	public SqlSessionTemplate sqlSession;

	/** 회원수 총 조회용 DAO
	 * @return listFullCount
	 * @throws Exception
	 */
	public int getListFullCount() throws Exception{
		return sqlSession.selectOne("adminhunMapper.listFullCount");
	}
	
	/**
	 * @param pInf
	 * @return memberList
	 */
	public List<Member> selectFUllList(PageInfo pInf) {
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		return sqlSession.selectList("adminhunMapper.selectFullList",rowBounds);
	}
	
	
	/** 회원 수 조회용 DAO
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Map<String, String> map) throws Exception{
		
		return sqlSession.selectOne("adminhunMapper.listCount", map);
	}

	/** 회원 목록 조회용 DAO
	 * @param map
	 * @param pInf
	 * @return memberList
	 */
	public List<Member> selectList(Map<String, String> map, PageInfo pInf) {
		
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		
		return sqlSession.selectList("adminhunMapper.selectList",map,rowBounds);
	}

	public Member detail(int no) throws Exception{
		
		return sqlSession.selectOne("adminhunMapper.detail", no);
	}

	public int plannerCount(int no)throws Exception {
		
		return sqlSession.selectOne("adminhunMapper.plannerCount", no);
	}

	public List<Integer> plannerList(int no) {
		
		return sqlSession.selectList("adminhunMapper.plannerList",no);
	}

	public List<Planner> plannerInfo(List<Integer> plannerList, PageInfo pInf) throws Exception{
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		
		return sqlSession.selectList("adminhunMapper.plannerInfo", plannerList ,rowBounds);
	}



}
