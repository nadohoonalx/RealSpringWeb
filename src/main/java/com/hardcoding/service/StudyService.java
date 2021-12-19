package com.hardcoding.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hardcoding.repository.CommonDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyService {
	//디비 조회하는 기름
	public final CommonDao dao;
	 
	public String doReg(Map map) {
		
		String resultPage = "";
		
		//1. 중복 확인
		int cnt = dao.selectInt("study.existMembers", map);
		
		if(cnt >0) {
			resultPage = "study/registFail";
		}
		//2. 등록
		else {
			dao.insert("study.insertMember", map);
			resultPage = "study/registOK";
		}
		
		return resultPage;
	}
	
	@Transactional
	public List<Map<String, Object>> getMembers(Map params) {
		List<Map<String, Object>> resultList = dao.selectList("study.selectItems2", params);
		return resultList;
	}
	
}
