package com.kh.ourtrip.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.ourtrip.admin.model.service.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Autowired
	public AdminService adminService;
	
	// 관리자 main 페이지 이동 + 방문 데이터 조회
	@RequestMapping("main")
	public String adminMain() {
		try {
			Map<String, Object> visitCounts = adminService.getVisitCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
