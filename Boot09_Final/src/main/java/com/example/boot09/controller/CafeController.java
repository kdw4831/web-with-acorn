package com.example.boot09.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.boot09.dto.CafeCommentDto;
import com.example.boot09.dto.CafeDto;
import com.example.boot09.service.CafeService;

@Controller
public class CafeController { //bean도 되고 controller 역할도 하고
	@Autowired private CafeService service;
	
	@PostMapping("/cafe/comment_insert")
	public String commentInsert(CafeCommentDto dto) {
		//댓글 저장 처리를 하고
		service.saveComment(dto);
		//해당글 자세히 보기로 다시 리다일렉트 시킨다.
		return "redirect:/cafe/detail?num="+dto.getRef_group();
	}
	
	@PostMapping("/cafe/update")
	public String update(CafeDto dto) {
		service.updateOne(dto);
		return "redirect:/cafe/detail?num="+dto.getNum();
	}
	
	
	@GetMapping("/cafe/updateform")
	public String updateForm(Model model,int num) {
		service.selectOne2(model, num);
		return "cafe/updateform";
	}
	
	@GetMapping("/cafe/delete")
	public String delete(int num) {
		service.deleteOne(num);
		return "redirect:/cafe/list";
	}
	
	@GetMapping("/cafe/detail")
	public String detail(Model model, CafeDto dto) {
		
		service.selectOne(model, dto);
		return "cafe/detail";
	}
	
	@GetMapping("/cafe/insertform")
	public String insertForm() {
		return "cafe/insertform";
	}

	@PostMapping("/cafe/insert")
	public String insert(CafeDto dto) {
		service.addtoWriting(dto);
		
		return "cafe/insert";
	}
	
	
	@GetMapping("/cafe/list")
	public String list(Model model, CafeDto dto) {
		service.selectPage(model,dto);
		return "cafe/list";
		
	}
}
