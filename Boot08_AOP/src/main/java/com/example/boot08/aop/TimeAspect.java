package com.example.boot08.aop;

import java.util.Date;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TimeAspect {
	//"" 안에 작성을 잘하면 특정 메소드가 실행되기 전에 여기가 실행된다.
	// 작성문법이 있다.  
	
	/*
	 * spring이 관리하는 bean의 메소드가 수행되기 이전(Before)에 적용되는 Aspect
	 * [메소드의 pattern]
	 * 리턴type =>void
	 * 메소드명=>write로 시작하는 메소드
	 * 메소드의 매개변수 =>없음
	 * 
	 * Aspect 가 적용되는 위치를 "point cut"이라고 부른다. 
	 */
	@Before("execution(void write*())")
	public void start() {
		Date start=new Date();
		long startTime=start.getTime();
		System.out.println("시작 시간: "+startTime);
	}
	@After("execution(void write*())")
	public void end() {
		Date end=new Date();
		long endTime=end.getTime();
		System.out.println("종료 시간: "+endTime);
	}
	
}
