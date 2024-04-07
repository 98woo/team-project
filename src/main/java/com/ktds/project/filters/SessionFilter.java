package com.ktds.project.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public SessionFilter() {
        super();
        
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			
		// Request 를 Servlet (DispatcherServlet) 에게 보내기 전에 필터링 코드 실행
		// Session 을 가져와서 로그인 여부를 판단.
		// filter 는 Spring 이 아니기 때문에 request를 사용해야 한다. 
		// Session 을 가져오려면 HttpServletRequest 에서 가져와야 한다.
		// request 파라미터는 ServletRequest
		// HttpServletRequest is a ServletRequest
		// HttpServletRequest ServletRequest 를 상속한 클래스
		// (is a) 상속 관계를 뒤집기 위해 캐스팅 한다.
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		// 로그인을 안 했다면, 로그인 페이지로 이동
		if (session.getAttribute("_LOGIN_USER_") == null) {
			// sendRedirect() 는 HttpServletResponse 에서 사용 가능
			// HttpServletResponse 는 ServletResponse 를 상속한 클래스.
			// HttpServletResponse is ServletResponse
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			// return 
			httpResponse.sendRedirect("/member/login");
			return; // 1번의 Request 에 1번의 Response : 삭제 메서드를 실행할 경우 Request 가 2번 실행되어 오류가 발생한다.
		}
		
		chain.doFilter(request, response);
		
		// Servlet (DispatcherService) 에서 보내준 Response 를 검사하는 필터링 코드 실행
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
