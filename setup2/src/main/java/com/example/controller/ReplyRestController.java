package com.example.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ReplyDAO;
import com.example.domain.ReplyVO;

@RestController
@RequestMapping("/api")

public class ReplyRestController {
	@Autowired
	ReplyDAO rdao;
	
	//bno만 parameter고 나머지는 qs라서  http://localhost:8088/api/reply/list/1016?page=1&num=5와 같이 써주게 됨.
	@RequestMapping("/reply/list/{bno}")
	public HashMap<String,Object>list(int page, int num, @PathVariable int bno){
		HashMap<String,Object>map=new HashMap<>();
		map.put("total",rdao.total(bno));
		map.put("list",rdao.list(page, num, bno));
		return map;
	}
	
	@RequestMapping(value="/reply/insert",method=RequestMethod.POST)
	public void insert(@RequestBody ReplyVO vo) {
		rdao.insert(vo);
	}
	
	@RequestMapping(value="/reply/update",method=RequestMethod.POST)
	public void update(@RequestBody ReplyVO vo) {
		rdao.update(vo);
	}
	
	@RequestMapping(value="/reply/delet/{rno}",method=RequestMethod.POST)
	public void delete(@PathVariable int rno) {
		rdao.delete(rno);
	}

}
