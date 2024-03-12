package com.example.boot11.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot11.dto.CafeDto;
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
	public Map<String,Object> list(@RequestParam int pageNum){
		return service.selectPage(pageNum);
	}
	
	@GetMapping("/cafes/{num}") 
	public CafeDto detail(@PathVariable("num") int num){
		// 아직 검색 키워드 기능이 없으므로 임시로 CafeDto 객체를 생성해서 글번호만 담는다. 
		CafeDto dto= new CafeDto();
		dto.setNum(num);
		return service.selectOne(dto);
	}
}
