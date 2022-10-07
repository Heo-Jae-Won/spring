package com.example.controller;

import java.io.File;

import java.nio.file.Files;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Resource(name="uploadPath") //bean에 있는 upload Path와 동일해야함. servlet-context XML
	String path;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@RequestMapping("/insert")
	public String insert(){
		return "insert";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(MultipartFile file) throws Exception{
		String fileName=path+"photo/"+System.currentTimeMillis()+file.getOriginalFilename(); 
		System.out.println("....."+fileName);
		//시간을 파일이름을 각각 다 다르게 만들어줌.
		// 만약 C드라이브 외부웹모듈이 /가 안붙어있으면 여기 photo에 /를 붙여야 함.
		FileCopyUtils.copy(file.getBytes(), new File(fileName)); //파일을 binary file로 바꾼 뒤에 filename으로 집어넣음.
		return "redirect:/";
	}
	@RequestMapping("/api/display")
	@ResponseBody
	public ResponseEntity<byte[]> display(String fileName)	throws Exception{
		ResponseEntity<byte[]> image=null;
		File file=new File(fileName);
		HttpHeaders header=new HttpHeaders(); //이거 지워버리면 아래 image가 안나옴. header를 참조하는듯.아래 header.add는 없어도 됨.
//		header.add("Content-type",Files.probeContentType(file.toPath()));
		image=new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
		return image;
	}

	

	
}
