package com.kh.ourtrip.websocket.controller;

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
	
	@Autowired
	
	@RequestMapping("cattingForm")
	public String chattingForm(Model model, Integer no, RedirectAttributes rdAttr ) {
		
		System.out.println(1);
		
		return "/websocket-echo";
	}
}
