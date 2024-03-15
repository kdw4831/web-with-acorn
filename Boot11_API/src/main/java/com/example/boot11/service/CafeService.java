package com.example.boot11.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.example.boot11.dto.CafeCommentDto;
import com.example.boot11.dto.CafeDto;



public interface CafeService {
	public void addtoWriting(CafeDto dto);
	public Map<String,Object> selectOne( CafeDto dto); // 글 자세히 보기를 위한 기능
	public Map<String,Object> selectPage(CafeDto dto);
	public void deleteOne(int num);
	public void updateOne(CafeDto dto);
	public void selectOne2(Model model,int num); // 글 수정 폼을 출력하기 위한 기능
	public void saveComment(CafeCommentDto dto); // 댓글 추가 기능
	// dto에 담긴 페이지 번호에 해당하는 댓글 목록을 리턴해주는 메소드
	public List<CafeCommentDto> getCommentList(CafeCommentDto dto);
	public void deleteComment(int num);
	public void updateComment(CafeCommentDto dto);

}
