package com.hardcoding.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DownloadView extends AbstractView {
	
	public DownloadView() {
		log.info("DownloadView 생성자 ==");
		setContentType("application/download; charset=utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		log.info("DownloadView.renderMergedOutputModel == ");
		Map map = (HashMap) model.get("downloadFile");
		
		File file = (File)map.get("model");
		
		response.setContentType(getContentType());
		response.setContentLength((int)file.length());
		
		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		String fileName= null;
		
		if(ie) {
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		} else {
			fileName = new String(file.getName().getBytes("UTF=8"), "8859_1");
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+fileName + "\";");
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} finally {
			if(fis !=null)
				try {
					fis.close();
				} catch(IOException ex) {
					
				}
		}
		out.flush();
	}
}
