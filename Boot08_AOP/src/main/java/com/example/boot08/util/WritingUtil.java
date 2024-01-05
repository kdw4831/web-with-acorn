package com.example.boot08.util;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component //이렇게 하면 bean이 된다.
public class WritingUtil {
	//생성자
	public WritingUtil() {
		System.out.println("WritingUtil 생성자");
	}

	public void writeLetter() {
		
		
		System.out.println("편지를 써요");
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	public void writeReport() {
		
		
		System.out.println("보고서를 써요");
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
	}
	public void writeDiary() {
		
		System.out.println("일기를 써요");	
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}