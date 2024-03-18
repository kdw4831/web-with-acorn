package com.example.boot11.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot11.dto.CafeCommentDto;
import com.example.boot11.dto.CafeDto;
import com.example.boot11.repository.CafeCommentDao;
import com.example.boot11.service.CafeService;

@RestController
public class CafeController {
	@Autowired
	private CafeService service;

	@PostMapping("/cafes")
	public Map<String,Object> insert(@RequestBody CafeDto dto){
		
		service.addtoWriting(dto);
		return Map.of("isSuccess",true);
	
	}

	
	@GetMapping("/cafes")
	public Map<String,Object> list(CafeDto dto){
		return service.selectPage(dto);
	}
	
	@GetMapping("/cafes/{num}") 
	public Map<String,Object> detail(@PathVariable("num") int num, CafeDto dto){
		// CafeDto에 경로 변수 num 값을 담는다 ( 검색조건과 키워드도 담겨 있을 수 있다)
		dto.setNum(num);
		
		return service.selectOne(dto);
	}
	
	//댓글 추가 	요청 처리
	@PostMapping("/cafes/comments")
	public Map<String,Object> commentInsert(CafeCommentDto dto){
		//FormData를 클라이언트에서 전송했기 때문에 @ResponsBody로 받을 수 없다??
		service.saveComment(dto);
		dto.setPageNum(1);
		
		// 새로운 댓글 1페이지의 내용을 응답한다.
		return service.getCommentList(dto);
	}
	
	//댓글 삭제 요청 처리
	@DeleteMapping("/cafes/comments/{num}")
	public Map<String,Object> commentDelete(@PathVariable("num") int num){
		//댓글 번호를 통해 삭제
		service.deleteComment(num);
		return Map.of("isSuccess",true);
	}
	
	@PatchMapping("/cafes/comments/{num}")
	public CafeCommentDto commentUpdate(CafeCommentDto dto){
		// 수정할 글번호는 dto에 담겨 있기 때문에 경로변수 num은 활용하지 않아도 된다.
		service.updateComment(dto);
		//수정한 글정보를 응답
		return dto;
	}
	
	@GetMapping("/cafes/comments")
	public Map<String,Object> commentList(CafeCommentDto dto){
		//dto에는 응답해야할 댓글의 페이지 번호와 원글의 글번호(ref_group)가 전달된다.
		
		//로딩 애니메이션 연습을 위해서 약간의 시간 지연시키기
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return service.getCommentList(dto);
	}
}
