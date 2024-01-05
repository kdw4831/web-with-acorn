package com.example.boot07.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //모든인자
@NoArgsConstructor //bean 생성자
@Builder
@Data //setter,getter 메소드 만들기 and toString() 메소드 오버라이드
public class MemberDto {
	
	private int num;
	private String name;
	private String addr;
	
}
