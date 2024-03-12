package com.example.boot11.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot11.dto.CafeDto;



@Repository// dao는 @Repository 어노테이션을 이용해서 bean으로 만든다.
public class CafeDaoImpl implements CafeDao {
	//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=8;
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;
	//myBatis 설정이 적절하게 되어 있다면 SqlSession 객체는 spring bean container에서 관리가 된다.
	@Autowired private SqlSession session;
	@Override
	public void insert(CafeDto dto) {
		session.insert("cafe.insert",dto);
		
	}

	@Override
	public void update(CafeDto dto) {
		session.update("cafe.update",dto);
		
	}

	@Override
	public void delete(int num) {
		session.delete("cafe.delete",num);
		
	}

	@Override
	public CafeDto getData(int num) {
		
		return session.selectOne("cafe.getData",num);
	}

	@Override
	public List<CafeDto> getList(CafeDto dto) {
		/*
		 * mapper's namespace=> cafe
		 * sql's id=> getList
		 * parameterType=> CafeDto
		 * resultType=> CafeDto
		 */
	
		
		List<CafeDto> list=session.selectList("cafe.getList",dto);
		return list;
	}
	
	
	
	@Override
	public int getCount(CafeDto dto) {
		//검색 키워드가 있다면 검색 키워드에 맞는 글의 갯수만 가져올 수 있도록 파라미터로 CafeDto를 전달한다.
		return session.selectOne("cafe.getCount",dto);
	}

	@Override
	public CafeDto getDetail(CafeDto dto) {
		
		return session.selectOne("cafe.getDetail",dto);
	}



}
