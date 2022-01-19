package com.hardcoding.service;

import java.security.MessageDigest;
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
	 
	@Transactional//DB 처리를 하니깐
	public String doReg(Map map) {
		
		String resultPage = "";
		
		//1. 중복 확인
		int cnt = dao.selectInt("study.existMembers", map);
		
		
		if(cnt >0) {
			resultPage = "study/registFail";
		}
		//2. 등록
		else {
			String password = (String) map.get("password");
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA-256");
				digest.update(password.getBytes());
				byte byteData [] = digest.digest();
				StringBuffer sb = new StringBuffer();
				for(byte byteTmp : byteData) {
					sb.append(Integer.toString((byteTmp&0xff) + 0x100, 16).substring(1));
				}
				map.put("password", sb.toString());
			}
			catch(Exception e) {
				log.error(e.getMessage());
			}
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
