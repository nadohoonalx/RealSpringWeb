package com.hardcoding.controller;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hardcoding.common.auth.UserAuth;
import com.hardcoding.service.CommonService;
import com.hardcoding.service.StudyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("nosession")
@RequiredArgsConstructor
public class NosessionController {
	
	private final StudyService studyService;
	private final CommonService commonService;
	
	@GetMapping("registForm")
	public String registForm() {
		return "study/registForm";
		
	}
	
	@PostMapping("doReg")
	public String doReg(@RequestParam Map<String, String> model, HttpServletRequest req, HttpServletResponse res) {
			
		System.out.println("Parameter ::" + model.toString());
		
		return studyService.doReg(model);
		}
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "auth/loginForm";
		
	}
	
	@PostMapping("login")
	public String login(@RequestParam Map<String, String> map, HttpSession session
			, HttpServletRequest req, HttpServletResponse res) {
		
		String name = map.get("name");
		String password = map.get("password");
		log.info("1. name :: " + name);
		log.info("2. password ::" + password);
		String rtnPW = "";
		String page = "home";
		
		try {
			//1. name 으로 회원 정보 조회
			Map<String, Object> param = new HashMap<>();
			param.put("name", name); //ctrl+shift+ y,x 대 소문자 변경
			Map<String, Object> userInfo = commonService.getUserInfo(param);
			
			if(userInfo == null) {
				log.info("존재하지 않는 사용자 입니다. ");
				req.setAttribute("error", "true");
				//model.addAttribute("error", "ture"); //위에꺼랑 같음 Model model -> HttpServletRequest
				return "auth/loginForm";
			}
			log.info("1. 사용자 정보를 가져왔습니다.");
			String getPassword = (String)userInfo.get("PASSWORD");
			
			MessageDigest  digest = MessageDigest.getInstance("SHA-256");
			//해쉬값 업데이트
			digest.update(password.getBytes());
			//해쉬값 다이제스트 얻기
			byte byteData [] = digest.digest();
			log.info("byteData [] :" + Arrays.toString(byteData));
			
			StringBuffer sb = new StringBuffer();
			for(byte byteTmp : byteData) {
				sb.append(Integer.toString((byteTmp&0xff) + 0x100, 16).substring(1));
			}
			rtnPW = sb.toString();
			log.info("rtnPW ::" + rtnPW);
			log.info("getPassword ::" + getPassword);
			if(rtnPW.equals(getPassword)) {
				log.info("로그인 성공");
				page = "home";
				res.sendRedirect(req.getContextPath() + "/nosession/home");
				UserAuth userAuth = UserAuth.builder()
									 	  .name(userInfo.get("NAME").toString())
									 	  .email(userInfo.get("EMAIL").toString())
									 	  .build();
				
				session.setAttribute("userAuth", userAuth);
			}
			else {
				log.info("패스워드가 틀렸습니다.");
				req.setAttribute("error", "true");
				page="auth/loginForm";
				
			}
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return page;
	}
}
