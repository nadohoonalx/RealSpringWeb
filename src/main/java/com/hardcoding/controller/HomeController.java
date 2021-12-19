package com.hardcoding.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hardcoding.common.util.CommonUtil;
import com.hardcoding.service.StudyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Slf4j
@RequiredArgsConstructor

public class HomeController {
	
	
	private final StudyService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request, HttpServletResponse res) {
		Locale locale = new Locale("ko", "KR");
		logger.info("Welcome home! The client locale is {}.", locale);
		
		//System.out.println(1/0);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		
		
		String sComboScript = CommonUtil.getSelect();
		
		//System.out.println(1/0);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("combo" ,CommonUtil.getSelect());
		//model.addAttribute("ServerInfo", System.getProperty("spring.profiles.active"));
		
		request.setAttribute("samki","Fighting!!"); //모델은 스프링에서 request 는 자바에서 쓰는데 보통 request 를 쓴다.
		request.setAttribute("combo", sComboScript);
		return "home";
	}
	
	
//	@GetMapping("study/registForm")
//	public String registForm(Model model, HttpServletRequest req, HttpServletResponse res) {
//			
//		
//		return "home/registForm";
//	} //주석침 위에꺼랑 같은건 직접해주기 떄문이고 아래는 
	
	@GetMapping("study/registForm")
	public void registForm() {
			
		
	}
	
	@PostMapping("study/doReg")
	public String doReg(@RequestParam Map<String, String> model, HttpServletRequest req, HttpServletResponse res) {
			
		System.out.println("Parameter ::" + model.toString());
		
		return (String) service.doReg(model);
		}
	
	@GetMapping("home/commonCode")
	public void commonCodeForm() {}
	
//	@GetMapping("study/doReg")
//	public String doReg(@RequestParam Map<String, String> map, Model model, HttpServletRequest req, HttpServletResponse res) {
//			
//		System.out.println("Parameter ::" + map.toString());
//		
//		return "home/registOK";
//		}
//	
	}
