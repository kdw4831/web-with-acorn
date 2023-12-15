package com.example.boot03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("fortuneToday","동쪽으로 가면 귀인을 만나요");
		//여기서 리턴한 문자열 앞에는 /template/가 붙고 뒤에는 .html 이붙어서 /templates/home.html을  가리키게 된다.
		return "home";
	}
}
