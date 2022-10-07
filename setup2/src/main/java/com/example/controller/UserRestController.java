package com.example.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.UserDAO;
import com.example.domain.UserVO;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	@Autowired
	UserDAO udao;
	
	@Resource(name="uploadPath") //bean에 있는 upload Path와 동일해야함. servlet-context XML
	String path;
	
	@RequestMapping("/list")
	public HashMap<String,Object> list(String word,int page,int num){
		HashMap<String,Object>map=new HashMap<>();
		map.put("list", udao.list(word, page,num));
		map.put("total", udao.total(word));
		return map;
	}
	
	@RequestMapping("/read/{uid}")
	public UserVO read(@PathVariable String uid) {
		return udao.read(uid);
	}
	
//	@RequestMapping(value="/insert",method=RequestMethod.POST)
//	public void insert(@RequestBody UserVO vo) {
//		 udao.insert(vo);
//	}
//	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public void insert(UserVO vo, MultipartFile file) throws Exception{
		//file.isEmpty가 아닐때이므로 파일이 있다는 의미
		if(!file.isEmpty()){
			String uploadPath=path+"photo/";
			String fileName=System.currentTimeMillis()+file.getOriginalFilename(); 
			System.out.println("....."+fileName);
			//시간을 파일이름을 각각 다 다르게 만들어줌.
			// 만약 C드라이브 외부웹모듈이 /가 안붙어있으면 여기 photo에 /를 붙여야 함.
			FileCopyUtils.copy(file.getBytes(), new File(uploadPath+fileName)); //파일을 binary file로 바꾼 뒤에 filename으로 집어넣음.이건 upload용.
			vo.setPhoto("/upload/photo/"+fileName); //여기는 upload가 아니라 DB에 넣어서 react와 연동하는 용도
		}
		udao.insert(vo);
	}
	
	
	@RequestMapping(value="/delete/{uid}",method=RequestMethod.POST)
	public void insert(@PathVariable String uid) {
		 udao.delete(uid);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public void update(@RequestBody UserVO vo) {
		 udao.update(vo);
	}
	


}
