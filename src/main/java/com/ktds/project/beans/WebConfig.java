package com.ktds.project.beans;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Interceptor, Servlet Filter, MVC 설정 등 은 클래스를 만들고 Config 에 등록을 해야 한다. Bean Container

// 페이지 방문 기록 
// 사용자가 방문할 때마다 URL을 기록할 수 하는 기능을 Config 에 추가해서 모든 페이지에서 접근할 수 있도록 할 수 있다.

@Configuration  // Spring Interceptor, Servlet Filter, MVC 설정 | MVC 와 관련된 모든 설정들의 우선 순위를 Configuration 이 다 가져간다.
@Configurable
@EnableWebMvc	// (yml mvc 설정을 나중에 가져옴) MVC와 관련된 여러 가지 기능들이 활성화 된다.
				// MVC와 관련된 설정들은 이 파일에 작성해야 한다.
				// 그 중 하나가 파라미터 유효성 검사이다.
public class WebConfig implements WebMvcConfigurer{
	
	// yml 에서 지정한 authentication 에 해당하는 값을 변수에 할당한다.
	// @Value 어노테이션으로 변수 초기화
	@Value("${app.authentication.check-url-pattern:/**}")
	private String authCheckUrlPattern;
	
	@Value("${app.authentication.ignore-url-patterns:}")
	private List<String> authCheckIgnorePatterns;
	
	// Filter 등록 
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**") // js 로 시작하는 모든 URL
				.addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/css/**") // css 로 시작하는 모든 URL
				.addResourceLocations("classpath:/static/css/");
	}
	
	
//	@Bean
//	FilterRegistrationBean<Filter> filter() {
//		
//		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//		filterRegistrationBean.setFilter(new SessionFilter());
//		filterRegistrationBean.setUrlPatterns(List.of("/board/write", "/board/modify/*", "/board/delete/*"));
//		
//		return filterRegistrationBean;
//	}
	
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
//		// 세션 체크를 하지 않을 URL 패턴 정의 (하드코딩.. 매번 추가 해야 할 수 있다. yml 파일에서 설정할 수도 있다.
//		List<String> excludePatterns = new ArrayList<>();
//		excludePatterns.add("/member/regist/**");
//		excludePatterns.add("/member/login");
//		excludePatterns.add("/board/list");
//		excludePatterns.add("/js/**");
//		excludePatterns.add("/css/**");
//		excludePatterns.add("/error/**");
		
		// 인터셉터 등록하기.
		registry.addInterceptor(new CheckSessionInterceptor())
				.addPathPatterns(this.authCheckUrlPattern)
				.excludePathPatterns(this.authCheckIgnorePatterns);
	
		// 모든 URL 에 Interceptor 를 개입 시키겠다.
		// excludePathPatterns 제외할 URL 을 객체로 만들고 인스턴스를 exclude 한다.
		

		registry.addInterceptor(new BlockDuplicateLoginInterceptor())
				.addPathPatterns("/member/login", "/ajax/member/login","/member/regist", "/ajax/member/regist");

	}
}
