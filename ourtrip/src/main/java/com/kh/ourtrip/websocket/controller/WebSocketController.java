package com.kh.ourtrip.websocket.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/websocket/*")
public class WebSocketController {
	
	//@Autowired
	
	@RequestMapping("chattingRoom")
	public String chattingRoom() {
		return "chattingRoom";
	}
	
	@RequestMapping("chattingForm")
	public String chattingForm(Model model, Integer no, RedirectAttributes rdAttr, String userId, String selectRoom) {
		model.addAttribute("userId", userId);
		model.addAttribute("selectRoom", selectRoom);
		
		return "websocket-echo";
	}
	
	@RequestMapping("chattingForm2")
	public String chattingForm2(Model model, Integer no, RedirectAttributes rdAttr, String userId, String selectRoom) {
		model.addAttribute("userId", userId);
		model.addAttribute("selectRoom", selectRoom);
		
		return "planner/updatePlanner";
	}
}
