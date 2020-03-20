package com.kh.ourtrip.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kh.ourtrip.admin.model.service.AdminService;

@SessionAttributes({"loginMember","Msg"})
@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Autowired
	public AdminService adminService;
	
	// 관리자 main 페이지 이동 + dashBoard 정보 조회용 Service
	@RequestMapping("main")
	public String adminMain(Model model) {
		try {
			Map<String, Object> dashBoardData = adminService.getDashBoardData();
			if(dashBoardData.isEmpty()) {
				model.addAttribute("dashBoardData", dashBoardData);
				System.out.println("대시보드 성공");
			} else {
				model.addAttribute("Msg", "데쉬보드 정보 조회 실패");
			}
			return "admin/main";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "대쉬보드 정보 조회 과정에서 오류");
			return "common/errorPage";
		}
	}
}
