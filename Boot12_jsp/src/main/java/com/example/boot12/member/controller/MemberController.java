package com.example.boot12.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot12.member.dao.MemberDao;
import com.example.boot12.member.dto.MemberDto;

@Controller
public class MemberController {
	//의존객체 주입 받기 (Dependency Injection)
	@Autowired MemberDao dao;
	
	//회원정보 수정 요청 처리
	@PostMapping("/member/update")
	public String update(MemberDto dto) {
		/*
		 *  매개변수를 dto 로 선언하면 전달되는 파라미터가 자동 추출되고 추출된 파라미터가
		 *  dto 에 자동으로 담겨서 전달된다. 
		 */
		dao.update(dto);
		return "member/update";
	}
	
	//회원정보 수정 폼 요청 처리
	@GetMapping("/member/updateform")
	public String updateform(int num, Model model) {
		// 파라미터로 전달되는 회원의 번호를 이용해서 수정할 회원의 정보를 얻어온다
		MemberDto dto=dao.getData(num);
		// Model 객체에 수정할 회원의 정보를 담고
		model.addAttribute("dto", dto);
		
		// /WEB-INF/views/member/updateform.jsp 페이지로 forward 이동해서 응답 하기
		return "member/updateform";
	}
	
	//회원정보 삭제 요청 처리
	@GetMapping("/member/delete")
	public String delete(int num) { // int type 으로 받으면 자동으로 숫자로 바꿔서 파라미터를 추출해서 전달해준다
		//MemberDao 객체를 이용해서 회원 한명의 정보 삭제하기 
		dao.delete(num);
		return "redirect:/member/list";
	}
	
	//회원정보 저장 요청처리
	@PostMapping("/member/insert")
	public String insert(String name, String addr) { //전송되는 파라미터의 이름과 동일하게 매개변수를 선언하면 자동추출 
		//DB 에 저장할 정보를 MemberDto 에 담고 
		MemberDto dto=new MemberDto();
		dto.setName(name);
		dto.setAddr(addr);
		//Dao 를 이용해서 DB 에 저장
		dao.insert(dto);
		
		// /WEB-INF/views/member/insert.jsp 페이지로 forward 이동해서 응답 하기
		return "member/insert";
	}
	
	@GetMapping("/member/insertform")
	public String insertform() {
		// /WEB-INF/views/member/insertform.jsp 페이지로 forward 이동해서 응답 하기
		return "member/insertform";
	}
	@GetMapping("/member/list")
	public String list(Model model) {
		//출력해줄 회원목록을 얻어와서 모델에 담고 
		List<MemberDto> list=dao.getList();
		//Model 객체에 "list" 라는 키값으로 회원 목록을 담는다. 
		model.addAttribute("list", list);
		// /WEB-INF/views/member/list.jsp 페이지로 forward 이동해서 응답 하기  
		return "member/list";
	}
}