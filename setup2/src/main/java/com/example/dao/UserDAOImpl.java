package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	SqlSession session;
	String namespace="com.example.mapper.UserMapper";

	@Override
	public List<UserVO> list(String word, int page) {
		HashMap<String,Object>map=new HashMap<>();
		map.put("word", word);
		map.put("start", (page-1)*5);
		return session.selectList(namespace+".list",map);
	}

	@Override
	public int total(String word) {
		return session.selectOne(namespace+".total",word);
	}

	@Override
	public UserVO read(String uid) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".read",uid);
	}

	@Override
	public void insert(UserVO vo) {
		// TODO Auto-generated method stub
		session.insert(namespace+".insert",vo);
	}

	@Override
	public void update(UserVO vo) {
		// TODO Auto-generated method stub
		session.update(namespace+".update",vo);
	}

	@Override
	public void delete(String uid) {
		// TODO Auto-generated method stub
		session.delete(namespace+".delete",uid);
	}

}
