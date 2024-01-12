package com.example.boot09.exception;
/*
 * spring MVC가 동작중에 특정 type의 예외가 발생하면 해당 예외를 여기서 처리 할 수 있다.
 *
 */

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(PasswordException.class)
	public String password(PasswordException pe,Model model) {
		model.addAttribute("exception",pe);
		return "error/password";
	}
	
	// spring framework 가 동작하는 중에 NotOwnerException type 의 예외가 발생하면 호출되는 메소드
	@ExceptionHandler(NotOwnerException.class)
	public String notOwner(NotOwnerException noe, Model model) {
		//메소드의 매개 변수에 예외객체의 참조값이 전달된다.
		//"exception"이라는 키값으로 예외 객체를 담는다.
		model.addAttribute("exception",noe);
		
		//view 페이지에서 에러 정보를 응답한다.
		return "error/info";
	}
}
