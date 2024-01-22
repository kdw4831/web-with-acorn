package com.example.boot09.repository;

import com.example.boot09.dto.CafeCommentDto;

public interface CafeCommentDao {
	//추가할 댓글의 글번호를 리턴하는 메소드
	public int getSequence();
	//댓글 추가 
	public void insert(CafeCommentDto dto);
}
