package com.kh.ourtrip.common.visitLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kh.ourtrip.common.visitLog.model.DAO.VisitCountDAO;

public class VisitCounter implements HttpSessionListener {

	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		// 등록되어있는 빈을 사용할 수 있도록 설정해준다
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		// request 생성
		
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		VisitCountDAO visitCountDAO = (VisitCountDAO)wac.getBean("visitCountDAO");
		SqlSessionTemplate sqlSession = (SqlSessionTemplate)wac.getBean("sqlSessionTemplate");
																		 
		//System.out.println("sqlSession : " + sqlSession);
		// DAO 객체 생성
		
		//VisitCount vc = new VisitCount(req.getRemoteAddr());
		String ip = req.getRemoteAddr();
		//System.out.println("ip: " + ip);
		//System.out.println("visitCountDAO : " + visitCountDAO);
		//System.out.println("template : " + sqlSession);
		try {
			int result = visitCountDAO.insertVisitor(ip, sqlSession);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}
	
}
