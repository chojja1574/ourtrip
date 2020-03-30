package com.kh.ourtrip.notice.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.notice.model.vo.Notice;


@Repository
public class NoticeDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 전체 게시글 수 조회용 DAO
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("noticeMapper.getListCount", map);
	}
	
	/**
	 * @param map
	 * @param pInf
	 * @return list
	 * @throws Exception
	 */
	public List<Notice> selectList(Map<String, Object> map, PageInfo pInf) throws Exception{
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		return sqlSession.selectList("noticeMapper.selectList", map, rowBounds);
	}

	/** 게시글 상세조회용 DAO
	 * @param no
	 * @return notice
	 * @throws Exception
	 */
	public Notice getNoticeDetail(int no) throws Exception{
		return sqlSession.selectOne("noticeMapper.getNoticeDetail", no);
	}
	
	/** 게시글 상세조회용 DAO
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int increaseNoticeCount(int no) throws Exception{
		return sqlSession.update("noticeMapper.increaseNoticeCount", no);
	}

	public int selectNextNo() throws Exception {
		return sqlSession.selectOne("noticeMapper.selectNextNo");
	}

	public int insertNotice(Notice notice) throws Exception{
		return sqlSession.insert("noticeMapper.insertNotice", notice);
	}
}
