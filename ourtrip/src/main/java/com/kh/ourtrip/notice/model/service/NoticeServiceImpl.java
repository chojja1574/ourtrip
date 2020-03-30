package com.kh.ourtrip.notice.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.notice.model.dao.NoticeDAO;
import com.kh.ourtrip.notice.model.vo.Notice;


@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeDAO noticeDAO;

	/** 전체 게시글 수 조회용 Service
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	@Override
	public int getListCount(Map<String, Object> map) throws Exception {
		return noticeDAO.getListCount(map);
	}

	/** 게시글 목록 조회용 Service
	 * @param map
	 * @param pInf
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Notice> selectList(Map<String, Object> map, PageInfo pInf) throws Exception {
		return noticeDAO.selectList(map, pInf);
	}
	
	

	@Override
	public Notice getNoticeDetail(int no) throws Exception {
		return noticeDAO.getNoticeDetail(no);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int increaseNoticeCount(int no) throws Exception {
		return noticeDAO.increaseNoticeCount(no);
	}

	@Override
	public int selectNextNo() throws Exception {
		return noticeDAO.selectNextNo();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertNotice(Notice notice) throws Exception {
		return noticeDAO.insertNotice(notice);
	}

}
