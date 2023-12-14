package com.example.boot02.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

// 	@Controller+ @ResponseBody를 합친 기능이라고 보면 된다.
@RestController
public class SendController {
	// "msg"라는 파라미터가 전달되는 요청처리하기
	@PostMapping("/send")
	public String send(HttpServletRequest request) {//HttpServletRequest 객체가 필요하면 메소드에 선언하면 spring이 참조값을 전달해준다.
		// 요청파라미터 추출
		String msg=request.getParameter("msg");
		System.out.println("msg: "+msg);
		//응답
		return "클라이언트야 메세지 잘 받았어";
	}
	
	// 매개변수를 파라미터명과 type을  똑같이 해주면 자동으로 파라미터를 들고 온다. 
	// 이걸 컨트롤러가 해주는거 
	@PostMapping("/send2")
	public String send2(String msg) {
		System.out.println("msg: "+msg);
		return "클라이언트야 메세지 잘 받았어";
	}
}
