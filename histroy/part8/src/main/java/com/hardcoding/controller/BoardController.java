package com.hardcoding.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("board")
public class BoardController {
	
	
	@GetMapping("writeForm1")
	public String writeForm1(Model model, HttpServletRequest req, HttpServletResponse res) {
		
		return "board/writeForm1";
	}
	
	@PostMapping("upload")
	public String upload(Model model, MultipartHttpServletRequest req, HttpServletResponse res ) {
		String sPath = "";
		try {
			Iterator<String> itr = req.getFileNames();
			MultipartFile mpf = null;
			while(itr.hasNext()) {
				mpf = req.getFile(itr.next());
				log.info(mpf.getOriginalFilename() + " uploaded!");
				sPath = "c:" + File.separator + "NAS" + File.separator; 
				
				//1. 지정된 위치가 존재하는지 확인하고 없으면 경로를 생성한다.
				File chkDir = new File(sPath);
				if(!chkDir.isDirectory()) chkDir.mkdirs();
				
				//2. 지정된 위치에 파일을 복사한다.
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(sPath + mpf.getOriginalFilename()));;
				
				}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return "home/registOK";
		
	}
	
	
	@GetMapping("writeForm2")
	public String writeForm2(Model model, HttpServletRequest req, HttpServletResponse res) {
		
		return "board/writeForm2";
	}
	
	@PostMapping("upload2")
	public String upload2(Model model, MultipartHttpServletRequest req, HttpServletResponse res) {
		
	
		List<MultipartFile> files = req.getFiles("files");
		
		for(int i=0; i<files.size(); i++) {
			MultipartFile mpf = files.get(i);
			String sPath = "";
			try {
				sPath= "c:" + File.separator + "NAS" + File.separator;
			
				// 1. 지정된 위치가 존재하는지 확인하고 없으면 경로를 생성한다.
				File chkDir = new File(sPath);
				if(!chkDir.isDirectory()) {
					chkDir.mkdirs();
				}
				
				//2. 지정된 위치에 파일을 복사한다.
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(sPath+ mpf.getOriginalFilename()));
				
			}
			catch(IOException e) {
				System.out.println("error - " + e.getMessage());
				e.printStackTrace();
			}
		}
		return "home/registOK";
	}
	
	
	@GetMapping("writeForm3")
	public String writeForm3(Model model, MultipartHttpServletRequest req, HttpServletResponse res) {
		return "board/writeForm3";
	}
	
	@ResponseBody
	@PostMapping("upload3")
	public Map upload3(Model model, MultipartHttpServletRequest req, HttpServletResponse res) {
			List<MultipartFile> files = req.getFiles("files");
			List<String> result = new ArrayList<>();
			Map map = new HashMap();
			for(int i=0; i<files.size(); i++) {
				MultipartFile mpf = files.get(i);
				result.add(mpf.getOriginalFilename());
				String sPath = "";
				try {
					sPath = "c:" + File.separator + "NAS" + File.separator;
					
					//1. 지정된 위치가 존재하는지 확인하고 없으면 경로를 생성한다.
					File chkDir = new File(sPath);
					if(!chkDir.isDirectory()) {
						chkDir.mkdirs();
					}
					
					//2. 지정된 위치에 파일을 복사한다.
					FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(sPath + mpf.getOriginalFilename()));
				}
				catch(IOException e) {
					System.out.println("error -" + e.getMessage());
					e.printStackTrace();
				}
				map.put("files", result);
			}
			return map;
	}
	
	@RequestMapping("getFileDownload")
	public ModelAndView getFileDownload(@RequestParam Map<String, String> map) throws Exception {
		String path = "C:" + File.separator + "NAS" + File.separator + map.get("filename");
		File downloadFile = new File(path);
		Map<String ,File> data = new HashMap<>();
		data.put("model", downloadFile);
		return new ModelAndView("downloadView", "downloadFile", data);
	}
	
}
