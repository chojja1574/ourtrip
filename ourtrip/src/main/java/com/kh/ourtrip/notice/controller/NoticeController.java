package com.kh.ourtrip.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kh.ourtrip.notice.model.service.NoticeService;

@Controller
@SessionAttributes({"loginMember","msg"})
@RequestMapping("/notice/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("")
	public String noticeList() {
		
		
		
		return "";
	}
	
}
