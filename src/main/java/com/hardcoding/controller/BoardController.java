package com.hardcoding.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.hardcoding.service.BoardService;
import com.hardcoding.service.CommonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
	
	private final BoardService service;
	private final CommonService commonService;
	MediaType mt = MediaType.APPLICATION_JSON;
	
	/*
	 * @GetMapping("list") public String list(Model model, HttpServletRequest req,
	 * HttpServletResponse res) { List list = service.list(); return "board/list"; }
	 */
	
	@GetMapping("boardReg")
	public String boardReg(Model model, HttpServletRequest req, HttpServletResponse res) {
		
		return "board/boardReg";
	}
	
	@GetMapping("tuiGridForm")
	public String tuiGridForm(Model model, HttpServletRequest req, HttpServletResponse res) {
		
		return "board/tuiGridForm";
	}
	
	@GetMapping("writeForm1")
	public String writeForm1(Model model, HttpServletRequest req, HttpServletResponse res) {
		
		return "board/writeForm1";
	}
	
	@PostMapping("upload")
	
	public String upload(Model model, MultipartHttpServletRequest req, HttpServletResponse res ) {
		
		return service.upload(req);
		
		
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
		return "study/registOK";
	}
	
	
	@GetMapping("writeForm3")
	public String writeForm3(Model model, HttpServletRequest req, HttpServletResponse res) {
		return "board/writeForm3";
	}
	
	@ResponseBody
	@PostMapping("upload3")
	public Map upload3(MultipartHttpServletRequest req) {
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> resultList = service.upload3(req);
		
		result.put("files", resultList);
			return result;
			
		}
	
	@RequestMapping("getFileDownload")
	public ModelAndView getFileDownload(@RequestParam Map<String, String> map) throws Exception{
		log.info("dfd");
		Map fileMap = commonService.getData("board.getFileInfo", map);
		String path = null;
		
		if(fileMap != null) {
			path = "" + fileMap.get("FILE_PATH").toString() + fileMap.get("FILE_NAME").toString();
		}
		
		File downlaodFile = new File(path);
		Map data = new HashMap();
		data.put("model", downlaodFile);
		data.put("FILE_REALNAME", fileMap.get("FILE_REALNAME"));
		
		return new ModelAndView("downloadView", "downloadFile", data);
		
	}
	
	@ResponseBody
	@GetMapping(value = "getBasicDummy")
	public ResponseEntity<?> getBasicDummy() {
		
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String, Object> param = new HashMap<>();
		param.put("id", 353453);
		param.put("name", "sdfsdds");
		param.put("artist", "Birdy");
		param.put("release", "2016.04.29");
		param.put("type", "Deluxe");
		param.put("typeCode", "1");
		param.put("genre", "PoP");
		param.put("genreCode", "1");
		param.put("grade", "2");
		param.put("price", 1000);
		param.put("downloadCount", 1000);
		param.put("listenCount", "5000");
		
		result.add(param);
		
		param = new HashMap<>();
		
		param.put("id", 353453);
		param.put("name", "sdfsdds");
		param.put("artist", "Birdy");
		param.put("release", "2016.04.29");
		param.put("type", "Deluxe");
		param.put("typeCode", "1");
		param.put("genre", "PoP");
		param.put("genreCode", "1");
		param.put("grade", "2");
		param.put("price", 1000);
		param.put("downloadCount", 1000);
		param.put("listenCount", "5000");
		
		result.add(param);
		
		param = new HashMap<>();
		
		param.put("id", 353453);
		param.put("name", "sdfsdds");
		param.put("artist", "Birdy");
		param.put("release", "2016.04.29");
		param.put("type", "Deluxe");
		param.put("typeCode", "1");
		param.put("genre", "PoP");
		param.put("genreCode", "1");
		param.put("grade", "2");
		param.put("price", 1000);
		param.put("downloadCount", 1000);
		param.put("listenCount", "5000");
		
		result.add(param);
		
		 final HttpHeaders httpHeaders = new HttpHeaders();
	       httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	      return new ResponseEntity(result, httpHeaders, HttpStatus.OK);		//vscode thurder client에 배열형태로 보여줌. 어떤마인드?
		}
	
	@ResponseBody
	@GetMapping("selectgrid")
	public ResponseEntity<?> selectgrid(@RequestParam Map<String,Object> param) {
					//여길 지우던지 param 그거 추가해서 지워야함
		List<Map<String,Object>> resultList = new ArrayList<>();
		List<Map<String, Object>> FileMap2 = commonService.getDataList("grid.selectItems", param);
		Map<String, Object> map = new HashMap<>();
		
			
		for(Map<String, Object> map2 : FileMap2) {
			log.info("selectgrid1");
			map = new HashMap<>();
			map.put("FILE_REALNAME", map2.get("FILE_REALNAME"));
			map.put("FILE_NAME", map2.get("FILE_NAME"));
			map.put("FILE_PATH", map2.get("FILE_PATH"));
			map.put("FILE_LENGTH", map2.get("FILE_LENGTH"));
			map.put("GROUP_KEY", map2.get("GROUP_KEY"));
			map.put("FILE_KEY", map2.get("FILE_KEY"));
			resultList.add(map);
		}
		
		final HttpHeaders httpHeaders = new HttpHeaders();
	       httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	       log.info("selectgrid2");
	       return new ResponseEntity(resultList, httpHeaders, HttpStatus.OK);	
	}
	
	@GetMapping("sendMail")
	public ResponseEntity<?> sendMail(){
		
		final String username = "heavysender@hardcoding.co.kr";
		final String password = "zheldzkvp1";
		
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "mw-002.cafe24.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress ("immanuel6547@naver.com")); //보내는 사람 메일
			message.setRecipients(Message.RecipientType.TO, 
					InternetAddress.parse("pmc58@naver.com, immanuel6547@naver.com") //받는사람
		);
		//이메일 제목
		message.setSubject("놀라지 마세요 광명컴쟁이입니다테스트 이메일입니다.");
		//이메일 본문
		message.setText("안녕하세요 광명컴쟁이입니다. \n\n감사합니다. ");
		Transport.send(message);
	}
	catch(MessagingException e) {
		e.printStackTrace();
	}
	return ResponseEntity.accepted().contentType(mt).body("");
	
}
}
