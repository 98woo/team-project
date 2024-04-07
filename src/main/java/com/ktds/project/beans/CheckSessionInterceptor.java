package com.ktds.project.beans;

import org.apache.tika.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;



import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckSessionInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(CheckSessionInterceptor.class);

	// 컨트롤러가 실행되기 전에 요청을 가로챈다. ㅓ
	// 브라우저가 서버에 요청한 정보, 서버가 브라우저에 응답할 정보, 실행할 컨트롤러
	// return true 이면 컨트롤러 실행, false이면 실행하지 않는다.
	
	
	/**
	     * Controller 실행 전에 Interceptor가 개입
	     * @param request 브라우저가 서버에게 요청한 정보
	     * @param response 서버가 브라우저에게 응답할 정보
	     * @param handler 실행할 Controller
	     * @return controller 실행을 계속할것인지 여부 
	     *         (false 일 경우, 컨트롤러 실행을 하지 않고 즉시 응답해버린다.)
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 요청 객체에서 session 을 얻어온다.
		// 원래 HttpSession 은 request.getSession() 으로 얻어와야 한다.
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("_LOGIN_USER_");
		
		// 3번 코드.
		if (memberVO == null) {
			
			// 원래 가려했던 URL 정보. -> 현재 요청중인 URL을 확인.
			// GET, POST, PUT, DELETE, FETCH 등등...
			String httpMethod = request.getMethod().toLowerCase();
//			String url = request.getRequestURL().toString();
			String uri = request.getRequestURI();
			
			// 쿼리의 파라미터를 가져온다.
			String queryString = request.getQueryString();
			
			logger.debug("QueryString " + queryString);
			logger.debug("HttpMethod: " + httpMethod);
//			logger.debug("RequestURL " + url);
			logger.debug("RequestURI " + uri);
			
			
			if (httpMethod.equals("get")) {
				
				String nextUrl = uri;
				if (! StringUtils.isEmpty(queryString)) {
					nextUrl += "?" + queryString;
				}
				
				// Model 로 로그인 이후에 요청할 주소를 보내준다.
				request.setAttribute("nextUrl", nextUrl);
			}
			
			// 로그인 화면을 보여주기 위한 RequestDispatcher 객체를 만든다.
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/memberlogin.jsp");

			// 로그인 화면으로 이동시킨다.
			// URL은 바뀌지 않는다.
			rd.forward(request, response);
			
			// 컨트롤러 실행을 하지 않는다.
			return false;
		}
		
		//컨트롤러 계속 실행.
		return true;
	}
	
	
//	/**
//	     * Controller가 실행 된 후에 Interceptor가 개입
//	     * @param request 브라우저가 서버에게 요청한 정보
//	     * @param response 서버가 브라우저에게 응답할 정보
//	     * @param handler 실행한 Controller
//	     * @param modelAndView Controller가 반환한 ModelAndView 객체
//	     *        Controller가 String을 반환해도 ModelAndView로 변환된다.
//	*/
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}
//	
//	
//	/**
//	 	 * 예외처리
//	     * 요청과 응답처리가 모두 완료된 후에 Interceptor가 개입
//	     * @param request 브라우저가 서버에게 요청한 정보
//		 * @param response 서버가 브라우저에게 응답할 정보
//	     * @param handler 실행한 Controller
//	     * @param ex 실행 중 발생한 예외 객체
//	     *        예외가 발생하지 않았다면 null이다.
//     */
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//	
//		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}
	
}
