package com.kh.ourtrip.admin.model.service;

import java.util.Map;

public interface AdminService {

	/** dashBoard 정보 조회용 Service
	 * @return dashBoardData
	 * @throws Exception
	 */
	public abstract Map<String, Object> getDashBoardData() throws Exception;

}
