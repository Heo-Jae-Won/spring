package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO{
	
@Autowired
SqlSession session;

String namespace="com.example.mapper.ProductMapper";

//list를 뽑으려면 List를 뽑아야함.
	@Override
	public List<ProductVO> list(int page, int num, String word) {
		HashMap<String,Object>map=new HashMap<>();
		map.put("start", (page-1)*num);
		map.put("num", num);
		map.put("word", word);
		return session.selectList(namespace+".list",map);
	}

	@Override
	public int total(String word) {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".total",word);
	}

	@Override
	public void insert(ProductVO vo) {
		session.insert(namespace+".insert",vo);
		
	}

}
