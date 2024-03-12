package com.example.boot11.service;

import java.util.Map;

import org.springframework.ui.Model;

import com.example.boot11.dto.CafeDto;



public interface CafeService {
	public void addtoWriting(CafeDto dto);
	public CafeDto selectOne( CafeDto dto); // 글 자세히 보기를 위한 기능
	public Map<String,Object> selectPage(int pageNum);
	public void deleteOne(int num);
	public void updateOne(CafeDto dto);
	public void selectOne2(Model model,int num); // 글 수정 폼을 출력하기 위한 기능

}
