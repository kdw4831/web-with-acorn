package com.example.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hello.auto.Car;
import com.example.hello.auto.MyCar;
import com.example.hello.dao.MemberDao;
import com.example.hello.dao.MemberDaoImpl;

@Configuration
public class BeanConfig {
	
	/*
	 * 이 메소드에서 리턴되는 객체를 spring이 관리하는 bean이 되도록한다.
	 * 메소드의 이름이 이 객체의 이름으로 부여된다.
	 * 이 메소드는 한번만 호출된다.(single ton)
	 */
	@Bean
	public Car myCar() {// bean의 이름은 myCar 
		System.out.println("BeanConfig 클래스의 myCar() 호출됨");
		Car c1=new MyCar();
		return c1;
		
	}
	
	// MemberDao type을 리턴해주는 메소드
	@Bean
	public MemberDao memberdao() {
		return new MemberDaoImpl();
	
	}
}
