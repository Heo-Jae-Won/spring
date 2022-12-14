package com.example.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.domain.UserVO;

public interface UserDAO {
	public List<UserVO> list(String word, int page,int num);
	public int total(String word);
	public UserVO read(String uid);
	public void insert(UserVO vo);
	public void update(UserVO vo);
	public void delete(String uid);
}
