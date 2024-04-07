package com.ktds.project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

// Spring Bean 으로부터 객체를 생성해주고 Bean 에 적재 시키려는 것.
@Aspect	// AOP 모듈
@Component	// Spring Bean 생성해주는 Annotation
public class TimingAspect {
	
	private Logger logger = LoggerFactory.getLogger("timing");
	
	/**
	 * AOP 를 적용할 대상을 지정한다.
	 * execution() : 실행 단계에서 적용할 대상을 지정한다.
	 * public : public 접근 제어자를 사용하는 것을 대상으로 한다.
	 * * : 모든 반환 타입을 대상으로 한다.
	 * com.hello.forum..service.*ServiceImpl : com.hello.forum 아래에 있는 모든 패키지 중에서
	 * 										   service 패키지 내부에 있는
	 * 										   ServiceImpl로 끝나는 모든 클래스를 대상으로 한다.
	 * .*(..) : ServiceImpl 로 끝나는 모든 클래스 내부의 모든 메서드를 대상으로 한다.
	 * 
	 * ServiceImpl 클래스 밑에 있는 public 모든 반환 타입 메서드명() 을 대상으로 Aspect 를 실행한다.
	 */
	@Pointcut("execution(public * com.hello.forum..service.*ServiceImpl.*(..))")
	public void aroundTarget() {
	}
	
	/**
	 * 원래 실행될 메서드의 전, 후 에 공통 코드를 실행한다.
	 * ServiceImpl 에 있는 메서드가 실행될 때 (PointCut 대상이 실행될 때)
	 * 아래 메서드가 실행된다. - Weaving 된 코드가 실행된다.
	 * 
	 * @param pjp 원래 실행될 클래스와 메서드의 정보
	 * @return 원래 실행될 메서드의 반환 값.
	 * @throws Throwable 
	 */
	@Around("aroundTarget()")
	public Object timimgAdvice(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		
		Object result = null;
		
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			// ServiceImpl 에서 발생한 예외를 그대로 던진다.
			// 그래야 Controller 로 예외가 전달되고 
			// 마지막으로 ControllerAdvice로 예외가 전달되어 
			// 공통 예외를 처리할 수 있기 때문이다.
			throw e;
		} finally {
			stopwatch.stop();
			
			// 원래 실행 되어야 하는 클래스 정보 가져오기
			String classPath = pjp.getTarget().getClass().getName();
			// 원래 실행 되어야 하는 메서드 가져오기
			String methodName = pjp.getSignature().getName();
			logger.debug("{}.{} 걸린시간: {}ms", classPath, methodName, stopwatch.lastTaskInfo().getTimeMillis());
		}
		
		
		return result;
	}
}
