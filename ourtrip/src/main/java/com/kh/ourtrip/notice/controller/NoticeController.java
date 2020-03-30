package com.kh.ourtrip.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ourtrip.common.Pagination;
import com.kh.ourtrip.common.vo.PageInfo;
import com.kh.ourtrip.member.model.vo.Member;
import com.kh.ourtrip.notice.model.service.NoticeService;
import com.kh.ourtrip.notice.model.vo.Notice;

@Controller
@SessionAttributes({ "loginMember", "msg" })
@RequestMapping("/notice/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@RequestMapping("list")
	public String noticeList(@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "searchKey", required = false) String searchKey,
			@RequestParam(value = "searchValue", required = false) String searchValue, Model model) {

		try {
			// 검색 조건이 있는지 확인하여 map에 세팅
			Map<String, Object> map = null;
			if (searchKey != null && searchValue != null) {
				map = new HashMap<String, Object>();
				map.put("searchKey", searchKey);
				map.put("searchValue", searchValue);
			}

			// 전체 게시글 수 조회
			int listCount = noticeService.getListCount(map);

			// 현재 페이지 확인
			if (currentPage == null)
				currentPage = 1;

			// 페이지 정보 저장
			PageInfo pInf = Pagination.getPageInfo(10, 10, currentPage, listCount);

			// 게시글 목록 조회
			List<Notice> list = noticeService.selectList(map, pInf);

			model.addAttribute("list", list);
			model.addAttribute("pInf", pInf);

			return "notice/noticeList";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "게시글 목록 조회 과정에서 오류 발생");
			return "common/errorPage";
		}
	}

	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String noticeDetaul(int no, Model model, RedirectAttributes rdAttr, HttpServletRequest request) {
		String beforeUrl = request.getHeader("referer");
		System.out.println(no);
		try {
			Notice notice = noticeService.getNoticeDetail(no);
			System.out.println(notice);
			if (notice != null) {

				noticeService.increaseNoticeCount(no);

				notice.setNoticeCount(notice.getNoticeCount() + 1);

				model.addAttribute("notice", notice);

				return "notice/noticeDetail";
			} else {
				rdAttr.addFlashAttribute("msg", "게시물 상세조회 실패");
				return "redirect:" + beforeUrl;
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "게시글 상세조회 과정에서 오류 발생");
			return "common/errorPage";
		}
	}

	@RequestMapping("insertForm")
	public String insertNoticeForm() {
		return "notice/noticeInsert";
	}

	@RequestMapping("insert")
	public String insertNotice(Model model, Notice notice) {

		int result;
		Member loginMember = (Member) model.getAttribute("loginMember");

		try {
			int nextNo = noticeService.selectNextNo();

			System.out.println(notice);

			notice.setNoticeNo(nextNo);
			notice.setMemberNo(loginMember.getMemberNo());

			result = noticeService.insertNotice(notice);

			model.addAttribute("no", nextNo);
			return "redirect:detail";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "게시글 작성 과정에서 오류 발생");
			return "common/errorPage";
		}
	}

	@RequestMapping("updateForm")
	public String noticeUpdateForm(int no, Model model) {
		try {

			Notice notice = noticeService.getNoticeDetail(no);

			System.out.println("updateNo : " + no);
			System.out.println(notice);

			model.addAttribute("notice", notice);

			return "notice/noticeUpdate";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "게시글 수정양식 로드 과정에서 오류 발생");
			return "common/errorPage";
		}
	}
	
	@RequestMapping("update")
	public String noticeUpdate(Notice notice, int no, Model model) {

		int result;
		Member loginMember = (Member) model.getAttribute("loginMember");

		try {
			System.out.println(notice);

			notice.setMemberNo(loginMember.getMemberNo());
			notice.setNoticeNo(no);

			result = noticeService.updateNotice(notice);

			model.addAttribute("no", no);
			
			return "redirect:detail";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "게시글 수정 과정에서 오류 발생");
			return "common/errorPage";
		}
	}
	
	@RequestMapping("delete")
	public String noticeDelete(int no, Model model) {
		int result;
		try {
			result = noticeService.deleteNotice(no);
			
			if(result > 0)
				model.addAttribute("msg","삭제 성공");
			if(result == 0)
				model.addAttribute("msg","삭제 실패");
			return "redirect:list";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "게시글 삭제 과정에서 오류 발생");
			return "common/errorPage";
		}
	}
}
