package com.hardcoding.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
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
@RequiredArgsConstructor //아래에 주입 자동으로 하게
public class BoardService {
	
	private final CommonDao dao; //required 주입 위에꺼 자동으로
	
	public String upload(MultipartHttpServletRequest req) {
		String result = "study/registOK";
		String sPath = "";
		try {
			Iterator<String> itr = req.getFileNames();
			MultipartFile mpf = null;
			String sGroupKey = dao.selectStr("board.selectFileGroupKey", null); //반이있으면 반번호가 있다
			int cnt = 0; //파일키
			while(itr.hasNext()) {
				cnt++;
				mpf = req.getFile(itr.next());
				if(mpf.isEmpty()) continue;
				
				log.info(mpf.getOriginalFilename() + " uploaded!");
				sPath = "c:" + File.separator + "NAS" + File.separator; 
				
				Map<String, Object> param = new HashMap<>();
				param.put("GROUP_KEY", sGroupKey);
				param.put("FILE_KEY", ++cnt);
				param.put("FILE_REALNAME", mpf.getOriginalFilename());
				param.put("FILE_NAME", Calendar.getInstance().getTimeInMillis());
				param.put("FILE_PATH", sPath);
				param.put("FILE_LENGTH", mpf.getBytes().length);
				param.put("FILE_TYPE", mpf.getContentType());
				param.put("USER_ID", System.getProperty("spring.profiles.active"));
				
				
				//1. 지정된 위치가 존재하는지 확인하고 없으면 경로를 생성한다.
				File chkDir = new File(sPath);
				if(!chkDir.isDirectory()) chkDir.mkdirs();
				
				//2. 지정된 위치에 파일을 복사한다.
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(sPath + param.get("FILE_NAME")));
					dao.insert("board.insertFileInfo", param);
				}
		}
		catch(IOException e) {
			e.printStackTrace();
			result = "study/registFail";
		}
		
		return result;
		}
		

}
