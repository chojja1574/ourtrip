package com.kh.ourtrip.notice.model.service;

import java.util.List;
import java.util.Map;

import com.kh.ourtrip.notice.model.vo.Notice;
import com.kh.ourtrip.common.vo.PageInfo;

public interface NoticeService {

	/** 전체 게시글 수 조회용 Service
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	public abstract int getListCount(Map<String, Object> map) throws Exception;

	/** 게시글 목록 조회용 Service
	 * @param map
	 * @param pInf
	 * @return list
	 * @throws Exception
	 */
	public abstract List<Notice> selectList(Map<String, Object> map, PageInfo pInf) throws Exception;

	/** 게시글 상세조회용 Service
	 * @param no
	 * @return
	 * @throws Exception
	 */
	public abstract Notice getNoticeDetail(int no) throws Exception;
	
	public abstract int increaseNoticeCount(int no) throws Exception;

	public abstract int selectNextNo() throws Exception;

	public abstract int insertNotice(Notice notice) throws Exception;

	public abstract int updateNotice(Notice notice) throws Exception;

	public abstract int deleteNotice(int no) throws Exception;

}
