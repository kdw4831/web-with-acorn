package com.example.boot10;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(Model model) {
		//DB에서 읽어온 공지 사항이라고 가정하자
		List<String> notice=Arrays.asList("동쪽으로 가면 귀인을 만나요","어쩌구","저쩌구...");
		//view page에 전달
		model.addAttribute("notice",notice);		
		return "home";
	}
	
	@GetMapping("/play")
	public String play() {
		return "play";
	}
}
