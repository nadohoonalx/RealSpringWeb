package com.hardcoding.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hardcoding.service.CommonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@RequestMapping("common")
@RestController
@RequiredArgsConstructor
public class CommonController {
		
	private final CommonService service;
	
	@GetMapping("getCodeList")
	public Map<String, Object> getCodeList(@RequestParam Map<String, Object> param) {
		
		Map<String, Object> result = new HashMap<>();
		result.put("codeList", service.getCodeList(param));
		
		return result;
	}
	
	@GetMapping("logout")
	public String logout(HttpServletRequest req) {
		
		HttpSession session = req.getSession(true);
		session.invalidate();
		
		return "home";
	}
	
	
}
