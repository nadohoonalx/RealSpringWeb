//package com.hardcoding.common.aspect;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import lombok.extern.slf4j.Slf4j;
//
////@Slf4j
////@Aspect
////@Component
//public class IpChkAspect {
//		
//	@Pointcut("execution(public * com.hardcoding.controller..*(..))")
//	public void pointCutMethod() {}
//	
//	@Around("pointCutMethod() ") //위 메소드를 참고한다
//	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
//		
//		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//		//바로 위에껄 를 하면 아까 지웠던 model, req, res && args(model, req, res) 넣었던거 
//		
//		
//		log.info("============================================");
//		log.info("profiler == Before || " + joinPoint.getSignature().toShortString()); //toshort tolong 그거 찍어보기
//		log.info("profiler == Before || " + joinPoint.getSignature().toLongString()); 
//		log.info("profiler == Before || " + joinPoint.getSignature().toString()); 
//		log.info("== 이 시스템은" + System.getProperty("spring.profiles.active") + "입니다.");
//		String addr = req.getRemoteAddr();
//		log.info("== 서비스 요청 IP는" + addr + "입니다.");
//		ObjectMapper oMapper = new ObjectMapper();
//		Map<String, Object> map = new HashMap<>();     //oMapper.convertValue(joinPoint.getArgs()[0], Map.class);
//		
//		Object[] args = joinPoint.getArgs();
//		for(Object obj : args) {
//			map.put(obj.getClass().getName(), obj.toString());
//		}
//		
//		log.info("1");
//		Object result = new Object();
//		try {
//			result = joinPoint.proceed();
//			log.info("2");
//			map.put("SERVICE", req.getServletPath().replace("/", "")); //요청서비스 ID
//			map.put("ACESS_IP", addr);									//서비스 요청 IP
//			map.put("REQ_QUERY", req.getQueryString());					//요청 URL
//			log.info("3");
//			System.out.println(map);
//			
//			return result;
//		}
//		catch(Exception e) {
//			
//		}
//		
//		return result;
//	}
//}
