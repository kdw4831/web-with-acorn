package com.example.boot13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.boot13.dto.MemberDto;
import com.example.boot13.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired private MemberService service;
	
	
	@PostMapping("/update")
	public String update(MemberDto dto) {
		service.update2(dto);
		return "member/update";
	}
	
	@GetMapping("/updateform")
	public String updateform (Long num, Model model) {
		service.getData(num, model);
		return "member/updateform";
	}
	
	
	@GetMapping("/delete")
	public String delete(Long num) {
		service.delete(num);
		return "redirect:/member/list";
	}
	
	
	@PostMapping("/insert")
	public String insert(MemberDto dto) {
		//MemberService를 이용하여 회원 정보 추가
		service.insert(dto);
		return "member/insert";
	}
	
	@GetMapping("/insertform")
	public String insertform() {
		
		return "member/insertform";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		service.getList(model);
		return "member/list";
	}
}
