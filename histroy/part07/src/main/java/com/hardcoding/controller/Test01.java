package com.hardcoding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Map<String, Object>> periodList = new ArrayList<Map<String, Object>>(); // 기간목록
		
		Map<String, Object>
		map = new HashMap<>(); map.put("CODE", "1d");map.put("VALUE", "1��"); periodList.add(map);
		System.out.println(periodList.toString());
		 map.put("CODE", "1w");map.put("VALUE", "1��"); periodList.add(map);
		System.out.println(periodList.toString());
		 map.put("CODE", "1m");map.put("VALUE", "1����");periodList.add(map);
		System.out.println(periodList.toString());
		 map.put("CODE", "6m");map.put("VALUE", "6����");periodList.add(map);
		System.out.println(periodList.toString());
		map.put("CODE", "1y");map.put("VALUE", "1��");periodList.add(map);
		System.out.println(periodList.toString());
		
		//그래서 왜 map을 각 떄마다 생성자를 만들어야 하는지 이유 덮어쓰지 않음
	}

}
