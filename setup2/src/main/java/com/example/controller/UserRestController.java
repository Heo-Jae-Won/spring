package com.example.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserDAO;
import com.example.domain.UserVO;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	@Autowired
	UserDAO udao;
	
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
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public void insert(@RequestBody UserVO vo) {
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
