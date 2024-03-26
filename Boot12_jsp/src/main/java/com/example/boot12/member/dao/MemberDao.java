package com.example.boot12.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot12.member.dto.MemberDto;


@Repository
public class MemberDao {
	/*
	 * DB 접속 설정과 MyBatis 설정이 잘 되어 있다면 spring boot에서 자동으로 sqlSesseion 객체를 만들어서
	 * spring bean container에서 관리를 해준다.
	 * SqlSession 객체를 이용해서 DB에 insert, update, delete, select 작업을 하면된다.
	 */
	@Autowired
	SqlSession session;
	
	public void update(MemberDto dto) {
		session.update("member.update",dto);
	}
	
	
	public MemberDto getData(int num) {
		/*
		 * mapper의 namespace => member
		 * sql의 id => getData
		 * parameterType=> int
		 * resultType => MemberDto type
		 */
		return session.selectOne("member.getData",num);
	}
	
	public void delete(int num) {
		session.delete("member.delete",num);
	}
	
	public void insert(MemberDto dto) {
		/*
		 * mapper의 namespace => member
		 * sql의 id => insert
		 * parameterType=>MemberDto
		 */
		session.insert("member.insert", dto);
	}
	
	//회원 목록 얻어오기
	public List<MemberDto> getList(){
		/*
		 * member=>mapper의 namespace
		 * getList=> sql의 id
		 * MemberDto=>result type
		 * 
		 * .selectOne()메소드는 result Type이 리턴된다.
		 * .selectList() 메소드 List<result type>이 리턴된다.
		 */
		return session.selectList("member.getList"); // 이게 List다
			
	}
}
