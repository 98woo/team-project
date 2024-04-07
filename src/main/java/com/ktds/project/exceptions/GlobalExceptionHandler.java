package com.ktds.project.exceptions;

import com.google.gson.Gson;
import com.hello.forum.utils.AjaxResponse;
import com.hello.forum.utils.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Base Package (com.hello.forum) 아래에서 발생하는
 * 처리되지 않은 모든 예외들을 ControllerAdvice 가 처리해준다.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * PageNotFoundException 이 발생했을 때, 동작하는 메서드.
	 * 
	 * @param pnfe ControllerAdvice 까지 처리되지 않은 PageNotFoundException 객체
	 * @return 에러 페이지
	 */
	@ExceptionHandler(PageNotFoundException.class)
	public Object viewPageNotFoundPage(PageNotFoundException pnfe, Model model) {
		
		logger.error(pnfe.getMessage(), pnfe);
		
		HttpServletRequest request = RequestUtil.getRequest();
		String uri = request.getRequestURI();
		
		if (uri.startsWith("/ajax/")) {
			AjaxResponse ar = new AjaxResponse();
			ar.append("errorMessage", pnfe.getMessage());
			
			// AjaxResponse 를 JSON 으로 변환. 
			// Gson 을 사용하면 @ResponseBody 를 활영한 것과 비슷한 효과를 얻을 수 있다. 레퍼런스 타입의 메모리 관계를 끊기 위해서 많이 사용한다.
			Gson gson = new Gson();
			String ajaxJsonResponse = gson.toJson(ar);
			
			// return new ResponseEntity<String>(ajaxJsonResponse, HttpStatus.OK);	// String 을 반환 시키면 페이지가 되어버린다. 그래서 ResponseEntity 로 전달한다. (순서한 데이터만 전달)
			// Response 이름을 데이터로 바꿔라
			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ajaxJsonResponse);
		}
		
		model.addAttribute("message", pnfe.getMessage());
		
		return "error/404";
	}
	
	@ExceptionHandler({FileNotExistsException.class, MakeXlsxFileException.class, AlreadyUseException.class, UserIdentifyNotMatchException.class, RuntimeException.class})
	public Object viewErrorPage(RuntimeException re, Model model) {
		
		logger.error(re.getMessage(), re);
		
		HttpServletRequest request = RequestUtil.getRequest();
		String uri = request.getRequestURI();
		
		if (uri.startsWith("/ajax/")) {
			AjaxResponse ar = new AjaxResponse();
			ar.append("errorMessage", re.getMessage());
			
			Gson gson = new Gson();
			String ajaxJsonResponse = gson.toJson(ar);
			return new ResponseEntity<String>(ajaxJsonResponse, HttpStatus.OK);	// String 을 반환 시키면 페이지가 되어버린다. 그래서 ResponseEntity 로 전달한다. (순서한 데이터만 전달)
		}
		
		// 파라미터를 꺼내기 위해서 instanceof
		if (re instanceof AlreadyUseException) {
			AlreadyUseException aue = (AlreadyUseException) re;
			model.addAttribute("email", aue.getEmail());
		}
		
		model.addAttribute("message", re.getMessage());
		return "error/500";
	}
	
	
}
