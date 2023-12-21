package com.example.boot03.controller;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	
	@GetMapping("/")
	public String home(Model model) {
		
		//Model 객체에 담은 데이터는 request 영역에 담긴다.
		model.addAttribute("fortuneToday","동쪽으로 가면 귀인을 만나요");
		
		//session 영역에 id 라는 키값으로 kimgura담기 
		//session.setAttribute("id","kimgura");
		
		
		//테스트를 위해  Model 객체에 추가 정보 담기
		model.addAttribute("id","abc11");
		model.addAttribute("amount",2);
		
		//경로 파라미터 테스트를 위한 정보 담기
		model.addAttribute("num",999);
		
		//여기서 리턴한 문자열 앞에는 /template/가 붙고 뒤에는 .html 이붙어서 /templates/home.html을  가리키게 된다.
		return "home";
	}
}
