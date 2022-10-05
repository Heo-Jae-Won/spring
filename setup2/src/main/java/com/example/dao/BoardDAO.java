package com.example.dao;

import java.util.List;

import com.example.domain.BoardVO;

public interface BoardDAO {
	public List<BoardVO>list(String word,int page);
	public int total(String word);
	public void insert(BoardVO vo);
	public void update(BoardVO vo);
	public BoardVO read(int bno);
	public void delete(int bno);

}
