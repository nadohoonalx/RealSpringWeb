package com.hardcoding.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hardcoding.repository.CommonDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

//@Log4j
@Slf4j // 스트링만됨
@Service
@RequiredArgsConstructor
public class CommonService {
	
	
	public final CommonDao dao;
	
	@Transactional
	public List<Map<String, Object>> getCodeList(Map params) {
		List<Map<String, Object>> resultList = dao.selectList("common.selectItems", params);
		return resultList;
	}

}
