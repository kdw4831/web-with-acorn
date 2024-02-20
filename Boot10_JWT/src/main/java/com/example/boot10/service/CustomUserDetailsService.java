package com.example.boot10.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.boot10.dto.UserDto;
import com.example.boot10.repository.UserDao;

/*
 *   
 *   원래는 DB 에서 읽어와야 하지만 DB 에 저장된 sample 데이터가 아래와 같다고 가정하고 로그인후 테스트해야한다.
 *   
 *   - 계정 / 비밀번호 예시
 *   
 *   1. 일반 사용자(USER)      =>  kimgura  / 1234
 *   2. 직원(STAFF)    =>  batman   / 1234 
 * 	 3. 관리자(ADMIN)   =>  superman / 1234
 */
@Service //bean으로 만들기 위해 
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao dao;
	
	//Spring Security가 로그인 처리시 호출하는 메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//1.form에 입력한 userName을 이용해서 사용자의 자세한 정보를 얻어온다. 		
		UserDto dto=dao.getData(username); 
		
		//만일 저장된 userName이 없다면
		if(dto==null) {
			//예외를 발생시킨다.
			throw new UsernameNotFoundException("존재하지 않는 사용자 입니다.");
		}
		
		//2.UserDetails 객체에 해당정보를 담아서 리턴해 주어야한다.
		//권한은 1개 이지만 List에 담아서
		List<GrantedAuthority> authList=new ArrayList<>();
		//Authority는 role 앞에 "ROLE_"를 붙여주어야한다.
		authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		UserDetails ud= new User(dto.getUserName(), dto.getPassword(), authList);
		return ud;
		
		
	}
	
}
