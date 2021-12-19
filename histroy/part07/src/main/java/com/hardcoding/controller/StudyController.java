package com.hardcoding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hardcoding.common.util.CommonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j;

//@Slf4j //logger 머시기를 안써도됨
@Log4j 

@RequestMapping("study")
@RestController  //Responsebody 를 사용 안해도 됨 internalResourview 이거 안쓰고 Responsebody만 사용할 경우 
// RestController이 없고 사용하게 되면 web. 
 // 스프링 4.3 이상ㅇ
public class StudyController {

		
//	@RequestMapping(value="getMessage1", method = RequestMethod.GET)
	@GetMapping("/getMessage1") // -> /getMessage1 도 되긴함
	//@ResponseBody //반환값이 문자로 되어줌 즉 jason 값으로 
	//@PostMapping 도 하면 그 home 에서 GET 이걸로 하면 에러가 나긴 했었ㅇ므
	public User getMessage1() {
		
		log.info(" :: Study.getMessage1 :: ");
		List list = new ArrayList();
		User user = new User("빵형", "1", list);
		log.info(user);   //log4j는 객체등 앞서사용하게 이런것도 됨 slf4j 는 문자열만 사용할수 있음 .  
		return user;
	}
	
	@GetMapping("home")  //InternalResourceViewResolver  spring/servlet-context.xml 엤음
	public String home() {
		return "study/home";
	}
	
	
	@GetMapping("getMessage2")
	public Map getMessage2() {
		log.info(" :: Study.getMessage2 :: ");
		Map map = new HashMap();
		map.put("name", CommonUtil.getSelect());
		return map;
	}
	
	@GetMapping("getMessage3")
	//@ResponseBody//InternalViewResolver  web.xml 엤음
	public Map getMessage3() {
		log.info(" :: Study.getMessage3 :: ");
		Map<String, Object> map = new HashMap();
		map.put("name", "나도훈");
		map.put("no", "이연준");
		return map;
	} // 하면 string 값이 반환
	
	
	@GetMapping("exceptionByZero")
	public Map exceptionByZero() {
		log.info(":: Study.exceptionByZero :: ");
		System.out.println(1/0);
		
		return new HashMap();
	}
	
//	@GetMapping("doReg")
//	public String doReg(@RequestParam Map<String, String> map, Model model, HttpServletRequest req, HttpServletResponse res) {
//			
//		System.out.println("Parameter ::" + map.toString());
//		
//		return "study/registOK";
//		}
	
	
//	@GetMapping("registForm")
//	public String registFrom1() {
//		return "study/registForm";
//	}
	
//	@GetMapping("registForm")
//	public void registForm() {
//		return "home/registForm";
//	}
//	@GetMapping("getMessage3")
//	public User getMessage3() {
//		
//		List list = new ArrayList();
//		User user2 = new User("도훈","1", list);
//		log.info(user2);
//		return user2;
//	}
	
}

@Data
@AllArgsConstructor

class User {
	private String name;
	private String no;
	List list;
}