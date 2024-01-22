package com.example.boot09.service;

import org.springframework.ui.Model;

import com.example.boot09.dto.CafeCommentDto;
import com.example.boot09.dto.CafeDto;

public interface CafeService {
	public void addtoWriting(CafeDto dto);
	public void selectOne(Model model, CafeDto dto); // 글 자세히 보기를 위한 기능
	public void selectPage(Model model, CafeDto dto);
	public void deleteOne(int num);
	public void updateOne(CafeDto dto);
	public void selectOne2(Model model,int num); // 글 수정 폼을 출력하기 위한 기능
	public void saveComment(CafeCommentDto dto);
}
