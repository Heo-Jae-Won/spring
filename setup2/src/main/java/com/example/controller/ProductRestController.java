package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.ProductDAO;
import com.example.domain.ProductVO;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
	@Resource(name="uploadPath") //bean에 있는 upload Path와 동일해야함. servlet-context XML
	String path;
	
	@Autowired
	ProductDAO pdao;
	
	@RequestMapping("/list")
	public HashMap<String,Object> list(int page,int num,String word){
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("list", pdao.list(page, num, word));
		map.put("total",pdao.total(word));
		return map;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public void insert(ProductVO vo, MultipartFile file) throws Exception{
		if(!file.isEmpty()){
			String filePath=path+"/product/";
			String fileName=System.currentTimeMillis()+file.getOriginalFilename();
			//여기 import는 Java io.file을 import함. org를 import하면 안됨.
			FileCopyUtils.copy(file.getBytes(), new File(filePath+fileName));
			vo.setImage("/upload/product/"+fileName);
		}
		pdao.insert(vo);
	}
	


}
