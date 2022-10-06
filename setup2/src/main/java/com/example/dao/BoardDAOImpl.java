package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	SqlSession session;
	String namespace = "com.example.mapper.BoardMapper";

	@Override
	public List<BoardVO> list(String word,int page) {
		HashMap<String, Object>map=new HashMap<>();
		map.put("word", word);
		map.put("start", (page-1)*5);
		return session.selectList(namespace+".list",map);
	}

	@Override
	public int total(String word) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".total",word);
	}

	@Override
	public void insert(BoardVO vo) {
		session.insert(namespace+".insert",vo);
	}

	@Override
	public void update(BoardVO vo) {
		session.update(namespace+".update",vo);
		
	}
	

	@Override
	public BoardVO read(int bno) {
		return session.selectOne(namespace+".read",bno);
	}

	@Override
	public void delete(int bno) {
		session.delete(namespace+".delete",bno);
		
	}

}
