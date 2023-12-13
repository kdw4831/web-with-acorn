package com.example.boot01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//spring이 이 클래스로 객체를 생성해서 bean으로 관리 하도록 한다.
@Component
public class HomeServiceImpl implements HomeService{
	//필드로 필요한 type을 선언하고 @Autowired라는 어노테이션을 붙이면 해당 객체가 주입된다.
	@Autowired //자동연결
	private Drill drill;
	
	@Override
	public void clean(String name) {
		System.out.println(name+"의 집을 청소해요!");
	}

	@Override
	public void wash(String name) {
		System.out.println(name+" 의 빨래를 빨아요~");
	}

	@Override
	public void hole(String name) {
		System.out.println(name+" 에 구멍을 뚫어요!");
		//구멍을 뚫을려면 Drill type 이 필요하다.
		//spring이 관리하는 bean을 이용해서 구멍을 뚫어야한다.
		drill.on();
		
	}

}
