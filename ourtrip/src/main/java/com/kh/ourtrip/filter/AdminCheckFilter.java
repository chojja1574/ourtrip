package com.kh.ourtrip.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ourtrip.member.model.vo.Member;

public class AdminCheckFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter
		(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		boolean isRedirect = false;
				
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		if(loginMember==null) {
			isRedirect = true;
		} else {
			if(!loginMember.getMemberGrade().equals("A")) {
				isRedirect = true;
			}
		}
		
		
		
		if(isRedirect == true) {
			
			request.getSession().setAttribute("msg", "잘못된 경로로 접근하였습니다");
			
			response.sendRedirect(request.getContextPath());
		} else {
			chain.doFilter(req, resp);
		}
				
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
