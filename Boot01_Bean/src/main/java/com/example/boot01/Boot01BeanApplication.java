package com.example.boot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.boot01.service.HomeService;

@SpringBootApplication
public class Boot01BeanApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(Boot01BeanApplication.class, args);
	
		//원숭이 집을 청소하려면?  homeservice와 impl 클래스는 의존관계가 없다.
		HomeService s1= ctx.getBean(HomeService.class);
		s1.clean("원숭이"); 
		
		//주뎅이의 빨래를 하려면?
		s1.wash("주뎅이");
		
		
		//바닥에 구멍을 뚫으려면?
		s1.hole("바닥");
	}

}
