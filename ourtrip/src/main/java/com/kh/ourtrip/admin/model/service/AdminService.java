package com.kh.ourtrip.admin.model.service;

import java.util.Map;

public interface AdminService {

	/** 방문정보조회용 Service
	 * @return visitCount
	 * @throws Exception
	 */
	public abstract Map<String, Object> getVisitCount() throws Exception;

}
