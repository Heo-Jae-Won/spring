package com.example.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.BoardDAO;
import com.example.domain.BoardVO;

@RestController
@RequestMapping("/api/board")
public class BoardRestController {
	@Autowired
	BoardDAO bdao;

//	@RequestMapping("/board/list")
//	public List<BoardVO>list(String word,int page){
//		return bdao.list(word,page);
//	}

	@RequestMapping("/list")
	public HashMap<String, Object> list(String word, int page) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("total", bdao.total(word));
		map.put("list", bdao.list(word, page));
		return map;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insert(@RequestBody BoardVO vo) {
		bdao.insert(vo);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody BoardVO vo) {
		bdao.update(vo);
	}
	
	@RequestMapping(value = "/read/{bno}")
	public BoardVO read(@PathVariable int bno) {
		return bdao.read(bno);
	}
	

	@RequestMapping(value = "/delete/{bno}", method = RequestMethod.POST)
	public void delete(@PathVariable int bno) {
		bdao.delete(bno);
	}
	

}
