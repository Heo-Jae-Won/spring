package com.example.dao;

import java.util.List;

import com.example.domain.ReplyVO;

public interface ReplyDAO {

	public List<ReplyVO>list(int page,int num,int bno);	
	public int total(int bno);
	public void insert(ReplyVO vo);
	public void delete(int rno);
	public void update(ReplyVO vo);
}
