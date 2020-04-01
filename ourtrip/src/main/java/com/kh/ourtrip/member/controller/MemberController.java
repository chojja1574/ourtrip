package com.kh.ourtrip.member.controller;

import java.io.File;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ourtrip.common.FileRename;
import com.kh.ourtrip.member.model.service.MemberService;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.member.model.vo.ProfileImage;

@SessionAttributes({"loginMember", "msg", "detailUrl", "profilePath", "state"})
@Controller
@RequestMapping(value="/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	// 로그인 화면 이동
	@RequestMapping(value="loginForm")
	public String loginForm(Model model, HttpServletRequest request, HttpSession session,
			@CookieValue(value = "saveEmail", required = false) String saveEmail) {
		
		if((Member)model.getAttribute("loginMember") != null) return "redirect:/";
		
		String beforeUrl = request.getHeader("referer");
		
		// 이전 페이지의 URL 저장
		if(model.getAttribute("detailUrl") == null) model.addAttribute("detailUrl", beforeUrl);
		else if(!model.getAttribute("detailUrl").equals(beforeUrl)) model.addAttribute("detailUrl", beforeUrl);
		
		if(saveEmail != null) model.addAttribute("saveEmail", saveEmail);
		
		return "member/login";
	}
	
	// 로그인
	@RequestMapping(value="login")
	public String login(Member member, String saveEmail, Model model, RedirectAttributes rdAttr,
			HttpSession session, HttpServletResponse response ) {
		member.setSignUpRoute("1");
		
		try {
			Member loginMember = memberService.login(member);
			String profileImagePath = null;
			String path = null;
			
			if(loginMember != null) {
				// 세션 만료시간 1시간
				session.setMaxInactiveInterval(60 * 60);
				
				// profileImagePath에 프로필사진 경로 저장
				profileImagePath = memberService.getProfileImagePath(loginMember.getMemberNo());
				
				// 로그인 성공 시 아이디를 쿠키에 저장
				// 관리자는 저장하지 않음
				if(loginMember.getMemberGrade().equals("A")) path = "/admin/main";
				else {
					Cookie cookie = new Cookie("saveEmail", member.getMemberEmail());
					
					if(saveEmail != null) cookie.setMaxAge(60 * 60 * 24 * 7); // 세션 유효 기간 7일
					else cookie.setMaxAge(0); // 쿠키 만료
					
					cookie.setPath("/"); // 쿠키 사용할 수 있는 도메인 설정
					response.addCookie(cookie); // 쿠키를 브라우저에 전송
					
					System.out.println("url : " + (String)model.getAttribute("detailUrl"));
					// 로그인 전 화면으로 이동
					if(model.getAttribute("detailUrl") != null) path = (String)model.getAttribute("detailUrl");
					else path = "/";
				}
				
				model.addAttribute("profilePath", profileImagePath);
				model.addAttribute("loginMember", loginMember);
			}else {
				rdAttr.addFlashAttribute("msg", "이메일, 비밀번호를 확인해주세요");
				path = "loginForm";
			}
			
			return "redirect:" + path;
			
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
	
	// 카카오 로그인
	@RequestMapping(value="kakaoLogin")
	@ResponseBody
	public String kakaoLogin(Member member, String imagePath,
							Model model) {
		// 카카오 회원가입은 경로가 2
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
	
	// 네이버 로그인 성공시 callBack 주소
	@RequestMapping("naverCallBack")
	public String naverCallBack() {

		return "member/naverCallBack";
	}
	
	// 네이버 로그인
	@RequestMapping("naverLogin")
	@ResponseBody
	public String naverLogin(Member member, String imagePath,
				Model model) {
		
		member.setSignUpRoute("3");
		
		String result = "fail";
		try {
			
			Member loginMember = memberService.naverLogin(member, imagePath);
			
			if(loginMember != null) {
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
	public String signUpForm(String isAgree, Model model, HttpServletRequest request) {
		if((Member)model.getAttribute("loginMember") != null) return "redirect:/";
		
		String beforeUrl = request.getHeader("referer");
		
		// 이전 페이지의 URL 저장
		if(model.getAttribute("detailUrl") == null) model.addAttribute("detailUrl", beforeUrl);
		else if(!model.getAttribute("detailUrl").equals(beforeUrl)) model.addAttribute("detailUrl", beforeUrl);
		
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
	public String signUp(Member member, Model model, RedirectAttributes rdAttr, HttpServletRequest request,
			@RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {
		// 기존 회원가입은 경로가 1
		member.setSignUpRoute("1");
		
		// 파일 저장 경로
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		String savePath = root + "/profileImages";
		
		// 저장 폴더 선택
		File folder = new File(savePath);

		// 만약 해당 폴더가 없는 경우 -> 폴더 만들기
		if (!folder.exists()) folder.mkdir();
		
		try {
			int result = memberService.signUp(member);
			
			String msg = null;
			String path = null;
			
			// 회원가입 성공 시
			if(result > 0) {
				// 회원이 이미지를 등록 했을 경우
				if(!profileImage.getOriginalFilename().equals("")) {
					String changeFileName = FileRename.rename(profileImage.getOriginalFilename());
					
					ProfileImage pi = new ProfileImage(savePath + "/" + changeFileName, result);
					
					result = memberService.insertProfileImage(pi);
					// DB에 프로필 등록 성공 시
					if(result > 0) {
						profileImage.transferTo(new File(pi.getImagePath()));
					}
				}
				
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
	
	@RequestMapping("updateForm")
	public String updateForm(Model model, HttpServletRequest request) {
		// 회원정보 수정 페이지 진입 시 DB에서 프로필사진 얻어옴
		Member loginMember = (Member)model.getAttribute("loginMember");
		
		if(loginMember == null) return "redirect:/";
		
		try {
			String beforeUrl = request.getHeader("referer");
			
			System.out.println("before : " + beforeUrl);
			System.out.println("detail : " + model.getAttribute("detailUrl"));
			
			// 회원정보 수정관련 주소와 같지 않으면 url저장
			if(	!beforeUrl.contains("/update") &&
				!beforeUrl.contains("/updateForm") &&
				!beforeUrl.contains("/changePwdForm") &&
				!beforeUrl.contains("/changePwd") &&
				!beforeUrl.contains("/secessionForm") &&
				!beforeUrl.contains("/secession")) model.addAttribute("detailUrl", beforeUrl);
			
			ProfileImage pi = memberService.selectProfileImage(loginMember.getMemberNo());
			model.addAttribute("profileImage", pi);
			
			return "member/updateForm";
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원정보 수정 페이지 이동중 에러 발생");
			return "common/errorPage";
		}
	}
	
	@RequestMapping("update")
	public String update(String memberNickName, String isDefault, 
			Model model, RedirectAttributes rdAttr, HttpServletRequest request,
			@RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {
		
		Member loginMember = (Member)model.getAttribute("loginMember");
		
		try {
			String msg = null;
			int result = 1; // 닉네임을 변경안했을 경우 조건문을 통과하기 위해 초기값 1
			
			// 입력한 닉네임 값이 변경 되었을 때
			if(!loginMember.getMemberNickName().equals(memberNickName)) {
				
				// DB에 보낼 VO를 생성
				Member member = new Member(loginMember.getMemberNo(), memberNickName);
				
				result = memberService.updateNickName(member);
				
				// 닉네임 변경 성공 시
				if(result > 0) {
					// 세션 객체에서 업데이트(세션 객체가 모든 값을 갖고있기 때문)
					loginMember.setMemberNickName(member.getMemberNickName());
				}
			}
			
			if(result > 0) {
				// 파일 저장 경로
				String root = request.getSession().getServletContext().getRealPath("resources");
				
				String savePath = root + "/profileImages";
				
				// 저장 폴더 선택
				File folder = new File(savePath);
				
				// 만약 해당 폴더가 없는 경우 -> 폴더 만들기
				if (!folder.exists()) folder.mkdir();
				
				result = memberService.updateProfileImage(loginMember.getMemberNo(), profileImage, savePath, isDefault);
				
				if(result > 0) {
					// profileImagePath에 프로필사진 경로 저장
					String profileImagePath = memberService.getProfileImagePath(loginMember.getMemberNo());
					model.addAttribute("profilePath", profileImagePath);
				}
				
			}
			
			if(result > 0) msg = "회원정보가 수정되었습니다";
			else msg = "회원정보 수정에 실패하였습니다.";
			
			rdAttr.addFlashAttribute("msg", msg);
			
			return "redirect:updateForm";
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원정보 수정과정중 오류 발생");
			return "common/errorPage";
		}
		
	}
	
	// 비밀번호 변경 폼
	@RequestMapping("changePwdForm")
	public String changePwdForm(Model model, RedirectAttributes rdAttr) {
		Member loginMember = (Member)model.getAttribute("loginMember");
		
		// 비회원으로 접근 시 리다이렉트
		if(loginMember == null) return "redirect:/";
		
		// 소셜 로그인 시 비밀번호 변경 불가
		if(!loginMember.getSignUpRoute().equals("1")) {
			rdAttr.addFlashAttribute("msg", "소셜 로그인 회원은 비밀번호 변경을 할 수 없습니다.");
			return "redirect:updateForm";
		}
		
		return "member/changePwdForm";
	}
	
	// 비밀번호 변경
	@RequestMapping("changePwd")
	public String changePwd(Member member, String changePwd, Model model, RedirectAttributes rdAttr) {
		Member loginMember = (Member)model.getAttribute("loginMember");
		
		// 세션에 있는 회원번호 저장
		member.setMemberNo(loginMember.getMemberNo());
		
		try {
			int result = memberService.changePwd(member, changePwd);
			
			String msg = null;
			String path = null;
			
			if(result > 0) {
				msg = "비밀번호가 변경되었습니다.";
				path = "updateForm";
			}else if(result == 0) {
				msg = "비밀번호 변경에 실패하였습니다.";
				path = "changePwdForm";
			}else {
				msg = "비밀번호가 일치하지 않습니다.";
				path = "changePwdForm";
			}
			
			rdAttr.addFlashAttribute("msg", msg);
			
			return "redirect:" + path;
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "비밀번호 변경 과정중 오류 발생");
			return "common/errorPage";
		}
	}
	
	// 회원탈퇴 폼
	@RequestMapping("secessionForm")
	public String secessionForm(Model model) {
		if((Member)model.getAttribute("loginMember") == null) return "redirect:/";
		
		return "member/secession";
	}
	
	// 회원탈퇴
	@RequestMapping("secession")
	public String secession(Member member, Model model, RedirectAttributes rdAttr, SessionStatus session) {
		
		// 세션에 있는 회원번호 저장
		member.setMemberNo(((Member)model.getAttribute("loginMember")).getMemberNo());
		
		try {
			int result = memberService.secession(member);
			
			String msg = null;
			String path = null;
			
			if(result > 0) {
				session.setComplete();
				msg = "회원 탈퇴 되었습니다.";
				path = "/";
			}else if(result == 0) {
				msg = "회원 탈퇴에 실패하였습니다.";
				path = "secessionForm";
			}else {
				msg = "비밀번호가 일치하지 않습니다.";
				path = "secessionForm";
			}
			
			rdAttr.addFlashAttribute("msg", msg);
			
			return "redirect:" + path;
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원탈퇴 과정 중 오류 발생");
			return "common/errorPage";
		}
	}
	
	// 비밀번호 찾기 폼
	@RequestMapping("findPwdForm")
	public String findPwdForm(Model model) {
		if((Member)model.getAttribute("loginMember") != null) return "redirect:/";
		
		return "member/findPwdForm";
	}
	
	// 가입된 이메일인지 확인
	@RequestMapping("signUpedEmail")
	@ResponseBody
	public int signUpedEmail(String email) {
		
		int result = 0;
		try {
			result = memberService.signUpedEmail(email);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 비밀번호 찾기(임시 비밀번호 전송)
	@RequestMapping("findPwd")
	public String findPwd(String memberEmail, Model model, RedirectAttributes rdAttr) {
		
		try {
			int result = memberService.findPwd(memberEmail);
			
			String msg = null;
			String path = null;
			
			if(result > 0) {
				msg = "임시 비밀번호가 메일로 전송되었습니다.";
				path = "loginForm";
			}else {
				msg = "비밀번호 찾기에 실패하였습니다.";
				path = "findPwdForm";
			}
			
			rdAttr.addFlashAttribute("msg", msg);
			
			return "redirect:" + path;
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "비밀번호 찾기 과정 중 오류 발생");
			return "common/errorPage";
		}
	}
}
