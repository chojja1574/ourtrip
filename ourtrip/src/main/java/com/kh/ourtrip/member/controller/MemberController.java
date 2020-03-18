package com.kh.ourtrip.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ourtrip.member.model.service.MemberService;
import com.kh.ourtrip.member.model.vo.Member;

@SessionAttributes({"loginMember", "msg", "profilePath"})
@Controller
@RequestMapping(value="/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="loginForm")
	public String loginForm() {
		return "member/login";
	}
	
	@RequestMapping(value="login")
	public String login(Member member, Model model) {
		member.setSignUpRoute("1");
		
		try {
			Member loginMember = memberService.login(member);
			
			String path = null;
			if(loginMember != null) {
				model.addAttribute("loginMember", loginMember);
				path = "redirect:/";
			}else {
				model.addAttribute("msg", "이메일, 비밀번호를 확인해주세요");
				path = "redirect:/loginForm";
			}
			
			return path;
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 과정 중 오류 발생");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="logout")
	public String logout(SessionStatus session) {
		session.setComplete();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="kakaoLogin")
	@ResponseBody
	public String kakaoLogin(Member member, String imagePath,
							Model model) { 
		member.setSignUpRoute("2");
		
		String result = "fail";
		
		try {
			Member loginMember = memberService.kakaoLogin(member, imagePath);
			
			if(loginMember != null){
				model.addAttribute("loginMember", loginMember);
				model.addAttribute("profilePath", imagePath);
				result = "success";
			}else {
				model.addAttribute("msg", "카카오 로그인 과정 중 오류 발생");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping("signUpForm")
	public String signUpForm(String isAgree) {
		if(isAgree != null && isAgree.equals("1")) {
			return "member/signUpForm";
		}else {
			return "member/signUpTerms";
		}
	}
	
	@RequestMapping("emailCertify")
	@ResponseBody
	public String emailCertify(String email) throws Exception{
		int result = 0;
		
		result = memberService.emailCertify(email);
		
		return Integer.toString(result);
		
	}
	
	@RequestMapping("signUp")
	public String signUp(Member member, Model model, RedirectAttributes rdAttr,
			@RequestParam(value = "profileImage", required = false) MultipartFile profileImagge) {
		member.setSignUpRoute("1");
		
		try {
			int result = memberService.signUp(member);
			
			String msg = null;
			String path = null;
			if(result > 0) {
				msg = "회원가입 되었습니다.";
				path = "/member/loginForm";
			}else {
				msg = "회원가입에 실패하였습니다.";
				path = "/member/signUpForm";
			}
			
			rdAttr.addFlashAttribute("msg", msg);
			
			return "redirect:" + path;
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원가입 과정 중 오류 발생");
			return "common/errorPage";
		}
	}
}
