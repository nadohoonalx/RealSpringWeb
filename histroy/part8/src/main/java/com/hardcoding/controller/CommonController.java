package com.hardcoding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RequestMapping("common")
@RestController
public class CommonController {
		
	
	@GetMapping("getCodeList")
	public Map getCodeList(String upcd) {
		
		List<Map<String, Object>> masterList = new ArrayList<Map<String, Object>>(); // 기본목록
		List<Map<String, Object>> periodList = new ArrayList<Map<String, Object>>(); // 기간목록
		List<Map<String, Object>> gradeList = new ArrayList<Map<String, Object>>(); // 직급목록
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		//periodList
		Map<String, Object>
		map = new HashMap<>(); map.put("CODE", "1d");map.put("VALUE", "1��"); periodList.add(map);
		map = new HashMap<>(); map.put("CODE", "1w");map.put("VALUE", "1��"); periodList.add(map);
		map = new HashMap<>(); map.put("CODE", "1m");map.put("VALUE", "1����");periodList.add(map);
		map = new HashMap<>(); map.put("CODE", "6m");map.put("VALUE", "6����");periodList.add(map);
		map = new HashMap<>(); map.put("CODE", "1y");map.put("VALUE", "1��");periodList.add(map);
	
		//grade 직급
		map = new HashMap<>(); map.put("CODE", "01");map.put("VALUE", "�������");gradeList.add(map);
		map = new HashMap<>(); map.put("CODE", "02");map.put("VALUE", "���");gradeList.add(map);
		map = new HashMap<>(); map.put("CODE", "03");map.put("VALUE", "�븮");gradeList.add(map);
		map = new HashMap<>(); map.put("CODE", "04");map.put("VALUE", "����");gradeList.add(map);
		map = new HashMap<>(); map.put("CODE", "05");map.put("VALUE", "����");gradeList.add(map);
		map = new HashMap<>(); map.put("CODE", "06");map.put("VALUE", "����");gradeList.add(map);
		map = new HashMap<>(); map.put("CODE", "07");map.put("VALUE", "�̻�");gradeList.add(map);
		map = new HashMap<>(); map.put("CODE", "08");map.put("VALUE", "��");gradeList.add(map);
		map = new HashMap<>(); map.put("CODE", "09");map.put("VALUE", "����");gradeList.add(map);
		
		//Master ����
		map = new HashMap<>(); map.put("CODE", "period");map.put("VALUE", "�Ⱓ"); masterList.add(map);
		map = new HashMap<>(); map.put("CODE", "grade");map.put("VALUE", "����"); masterList.add(map);
	
		log.info("upcd ::" + upcd);
		if(upcd.equals("period")) {
			result.put("codeList", periodList);
		}
		else if(upcd.equals("grade") ) {
			result.put("codeList", gradeList);
		}
		else {
			result.put("codeList", masterList);
		}
		return result;
	}
	
}
