package com.hardcoding.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	private static final Logger Logger = LoggerFactory.getLogger(HomeController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	@RequestMapping(value="/", method = RequestMethod.get)
	public String home(Locale locale, Model model) {
		logger.info("welcome home! The client local is {].", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.Long, DateFormat.Long, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		String sComboScript = "<select id = 'searchdate'>"
					+ "<option value = 'all selected=''>-select-</option>"
					+ "<option value = '1d'>1일</option>"
					+ "<option value='1w'> 1주</option>"
					+ "<option value = '1m'>1개월</option>"
					+ "<option value = '6m'>6개월</option>"
					+ "<option value = '1y'>1년</option>"
					+ "</select>";
			
		model.addAttribute("combo", commonUtil.getSelect());
		return "home";
					
		
	}
}
