package com.hardcoding.common.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class TraceAop {
		
//	@Pointcut("execution(public * com.hardcoding.controller..*(..))")
//	public void pointCutMethod() {}
	
	//2.@Around("exececution(public * com.hardcoding.controller..*(..)) && args(map, model, req, res)") //위에꺼 합침
	//3
	 @Around("execution(public * com.hardcoding.controllerr..*(..))")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
	
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		Object[] arg = joinPoint.getArgs();
		
		for(Object obj:joinPoint.getArgs()) {
			log.info(obj.getClass().getName());
		}
		
		log.info("===============================");
		log.info("profiler == Before || " + joinPoint.getSignature().toShortString());
		log.info("== 이 시스템은" + System.getProperty("spring.profiles.active") + "입니다.");
		String addr = req.getRemoteAddr();
		log.info("== 서비스 요청 ip는" + addr+ "입니다.");
		log.info("===============");
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> map = oMapper.convertValue(joinPoint.getArgs()[0], Map.class);
		
		Object result = new Object();
		try {
			
			result = joinPoint.proceed();
			
			map.put("SERVICE", req.getServletPath().replace("/", ""));
			map.put("ACCESS_IP", addr);
			map.put("REQ_QUERY", req.getQueryString());
			System.out.println(map);
			
			return result;
		}
		finally {
			
			}
		}		
	}
	

