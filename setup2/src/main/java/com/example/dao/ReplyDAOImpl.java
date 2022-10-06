package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	String namespace = "com.example.mapper.ReplyMapper";
	@Autowired
	SqlSession session;

	@Override
	public List<ReplyVO> list(int page, int num,int bno) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", (page-1)*num);
		map.put("num", num);
		map.put("bno", bno);
		return session.selectList(namespace + ".list", map);
	}

	@Override
	public int total(int bno) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".total",bno);
	}

	@Override
	public void insert(ReplyVO vo) {
		session.insert(namespace+".insert",vo);
		
	}

	@Override
	public void delete(int rno) {
		// TODO Auto-generated method stub
		session.delete(namespace+".delete",rno);
	}

	@Override
	public void update(ReplyVO vo) {
		// TODO Auto-generated method stub
		session.update(namespace+".update",vo);
	}

}
