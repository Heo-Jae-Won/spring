package com.example.dao;

import java.util.List;

import com.example.domain.ProductVO;

public interface ProductDAO {
	public List<ProductVO> list(int page,int num,String word);
	public int total(String word);
	public void insert(ProductVO vo);

}
