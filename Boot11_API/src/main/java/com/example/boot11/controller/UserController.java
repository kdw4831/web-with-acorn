package com.example.boot11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot11.dto.UserDto;
import com.example.boot11.util.JwtUtil;


@RestController
public class UserController {
	@Autowired
	private JwtUtil jwtUtil;
	//SecurityConfig 에서 Bean 으로 등록한 객체
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/auth")
	public String auth(@RequestBody UserDto dto) throws Exception {
		try {
			//입력한 username 과 password 를 인증토큰 객체에 담아서 
			UsernamePasswordAuthenticationToken authToken=
					new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());	
			//인증 메니저 객체를 이용해서 인증을 진행한다 
			authManager.authenticate(authToken);
		}catch(Exception e) {
			//예외가 발생하면 인증실패(아이디 혹은 비밀번호 틀림 등등...)
			e.printStackTrace();
			throw new Exception("아이디 혹은 비밀번호가 틀려요!");
		}
		//예외가 발생하지 않고 여기까지 실행 된다면 인증을 통과 한 것이다. 토큰을 발급해서 응답한다.
		String token=jwtUtil.generateToken(dto.getUserName());
		return token;
	}
}