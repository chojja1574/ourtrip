package com.kh.ourtrip.common.visitLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kh.ourtrip.common.visitLog.model.vo.VisitCount;

public class VisitCounter implements HttpSessionListener {

//	@Autowired 	
//	public VisitCountDAO visitCountDAO;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		// 등록되어있는 빈을 사용할 수 있도록 설정해준다
		// WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		// request를 파라미터에 넣지 않고도 사용할 수 있도록 설정
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		// VisitCountDAO visitCounDAO = (VisitCountDAO)wac.getBean("visitCountDAO");
		VisitCount vc = new VisitCount(req.getRemoteAddr());
		System.out.println("되냐?");
//		try {
//			int result = visitCountDAO.insertVisitor(vc);
//			System.out.println(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}
	
}
