package com.hardcoding.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hardcoding.repository.CommonDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class testClass {
	
	private final CommonDao dao;
	
	
	@Transactional
	public List<Map<String, Object>> selectgrid(MultipartHttpServletRequest req) {
		
		
		 Map<String, Object> m = dao.selectOne("grid.selectItems", null);
		 System.out.println(m.toString());
		 
		 
		return null;		
		
		
	}
}
