package com.example.boot01.service;

import org.springframework.stereotype.Component;

@Component
public class DrillImpl implements Drill{

	@Override
	public void on() {
		System.out.println("윙~~~");
		
	}

	@Override
	public void off() {
		System.out.println("전원이 꺼졌습니다.");
		
	}
	
	

}
