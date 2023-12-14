package com.example.boot02.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 클라이언트의 요청을 처리한 컨트롤러를 정의하고 bean으로 만들기
 */

//bean으로만 만드려면 component를 붙이면된다.
@Controller //1. bean으로 만들기 + 2. 요청을 처리하는 컨트롤러 역할하기
public class HelloController {
	@ResponseBody
	@GetMapping("/hello")   // deleteMapping 같은게 있다아
	public String hello() {
		return "Nice to meet you!";
	}
	
	@ResponseBody
	@GetMapping("/fortune")
	public String fortune() {
		return "동쪽으로 가면 귀인을 만나요";
	}
	
	@ResponseBody//2. 응답?
	@GetMapping("/member") //1.요청하고
	public Map<String, Object> member(){
		Map<String, Object> map= new HashMap<>();
		map.put("num",1 );
		map.put("name","김구라" );
		map.put("isMan",true );
		return map; //json문자열로 응답을 해준다.
	}
	
	@ResponseBody
	@GetMapping("/friends")
	public List<String> friends(){
		List<String> names=new ArrayList<String>();
		names.add("김구라");
		names.add("해골");
		names.add("원숭이");
		return names;
	}
	
	@ResponseBody
	@GetMapping("/members")
	public List<Map<String, Object>> members(){
		List<Map<String, Object>> members= new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map1= new HashMap<>();
		map1.put("num",1 );
		map1.put("name","김구라" );
		map1.put("isMan",true );
		
		Map<String, Object> map2= new HashMap<>();
		map2.put("num",2 );
		map2.put("name","해골" );
		map2.put("isMan",true );
		
		Map<String, Object> map3= new HashMap<>();
		map3.put("num",3 );
		map3.put("name","원숭이" );
		map3.put("isMan",true );
		
		members.add(map1);
		members.add(map2);
		members.add(map3);
		
		
		return members;

	}
	
}
