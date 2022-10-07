package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{
	
//	@Resource(name="uploadPath") //bean에 있는 upload Path와 동일해야함. servlet-context XML
//	String path;
	
	@Autowired
	SqlSession session;
	String namespace="com.example.mapper.UserMapper";
	


	@Override
	public List<UserVO> list(String word, int page, int num) {
		HashMap<String,Object>map=new HashMap<>();
		map.put("word", word);
		map.put("start", (page-1)*num);
		map.put("num", num);
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
//		String fileName=path+"photo/"+System.currentTimeMillis()+file.getOriginalFilename(); 
		
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
