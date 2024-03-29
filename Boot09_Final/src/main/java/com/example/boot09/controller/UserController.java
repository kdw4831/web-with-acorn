package com.example.boot09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot09.dto.UserDto;
import com.example.boot09.repository.UserDao;
import com.example.boot09.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	// util 역할을 하는 서비스 객체를 인터페이스 type으로 DI 받아서 사용한다.
	@Autowired
	private UserService service;
	
	@PostMapping ("/user/pwd_update")
	public String pwdUpdate(UserDto dto,HttpSession session) {
		//비밀번호 수정이후
		service.updatePassword(dto);
		//강제 로그아웃 처리
		session.invalidate();
		return "user/pwd_update";
	}
	
	@GetMapping("/user/pwd_updateform")
	public String pwdUpdateForm() {
		return "user/pwd_updateform";
	}
	
	
	@PostMapping("/user/update")
	public String update(UserDto dto) {
		service.updateUser(dto);
		//개인정보보기로 다시 리다일렉트 시킨다.
		return "redirect:/user/info";
	}
	
	@GetMapping("/user/updateform")
	public String updateForm(Model model) {
		service.getInfo(model);
		
		return "user/updateform";
	}
	
	//개인 정보 보기 요청 처리
	@GetMapping("/user/info")
	public String info(Model model) {
		service.getInfo(model);
		return "user/info";
	}
	
	//회원 가입 요청처리
	@PostMapping("/user/signup")               
	public String signup(UserDto dto) {
		//서비스(util)객체를 이용해서 가입정보를 등록한다.
		service.addUser(dto);
		
		return "user/signup";
	}
	
	//회원가입폼 요청처리
	@GetMapping("/user/signup_form")
	public String signupForm() {
		
		return "user/signup_form";
	}
	
	//세션 허용 갯수 초과시
	@GetMapping("/user/expired")
	public String userExpired() {
		return "user/expired";
	}
	//권한 부족시 or 403인 경우
	@GetMapping("/user/denied")
	public String userDenied() {
		return "user/denied";
	}
	
	
	//로그인이 필요한 요청경로를 로그인 하지 않은 상태로 요청하면 리타일렉트 되는 요청경로
	@GetMapping("/user/required_loginform")  //스프링 security에서 redirect될 경로
	public String required_loginform() {
		return "user/required_loginform";
	}
	//로그인 폼을 제출(post)한 로그인 프로젝트 중에 forward 되는 경로이기 때문에 @PostMapping  임에 주의
	@PostMapping("/user/login_fail")
	public String loginFail() {
		return "user/login_fail";
	}
	
	@GetMapping("/user/loginform")
	public String loginform() {
		//tememplates/user/loginform.html 페이지로 forward이동해서 응답
		return "user/loginform";
	}
	
	
	@PostMapping("/user/login_success")
	public String loginSuccess() {
		return "user/login_success";
	}
}
