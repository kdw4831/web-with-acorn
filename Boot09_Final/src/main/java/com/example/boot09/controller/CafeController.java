package com.example.boot09.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot09.dto.CafeCommentDto;
import com.example.boot09.dto.CafeDto;
import com.example.boot09.service.CafeService;

@Controller
public class CafeController { //bean도 되고 controller 역할도 하고
	@Autowired private CafeService service;
	
	@PostMapping("/cafe/comment_update")
	public String commentUpdate(CafeCommentDto dto) {
		
		return null;
	}
	
	@ResponseBody
	@GetMapping("/cafe/comment_delete")// Map 객체를 리턴하면 json 문자열이 응답되도록 @ResponseBody 어노테이션을 추가로 붙여준다.
	public Map<String, Object> commemtDelete(int num){
		service.deleteComment(num);
		//num은 Get 방식 파라미터로 전달되는 삭제할 댓글의 번호 
		Map<String,Object> map=new HashMap<>();
		map.put("isSuccess", true);
		return map;
	}
	
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
