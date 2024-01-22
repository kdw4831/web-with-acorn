package com.example.boot09.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot09.dto.CafeCommentDto;

@Repository
public class CafeCommentDaoImpl implements CafeCommentDao {
	@Autowired 
	SqlSession session;
	@Override
	public int getSequence() {
		return session.selectOne("cafeComment.getSequence");
	}

	@Override
	public void insert(CafeCommentDto dto) {
		session.insert("cafeComment.insert",dto);		
	}
	
}
