package com.example.boot11.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
	
	//sample 공지사항을 json 문자열로 응답하는 컨트롤러 메소드
	@GetMapping("/notice")
	public List<String> notice(){
		List<String> list= new ArrayList<String>();
		list.add("프로젝트 기간입니다.");
		list.add("힘내서 열심히 공부 합니다.");
		list.add("어쩌구");
		list.add("저쩌구");
		return list;
	}
}
