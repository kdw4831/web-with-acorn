package com.example.boot13.entity;

import com.example.boot13.dto.MemberDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity // Entity로 사용하겠다는 어노테이션 
@Table(name = "MEMBER_TBL") // 원하는 테이블 설정 (생략하면 클래스명이 테이블 명이 된다.) 
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long num;
	private String name;
	private String addr;
	
	
	//dto를 entity로 변경하는 static 메소드
	public static Member toEntity(MemberDto dto) {
		return Member.builder()
				.num(dto.getNum())
				.name(dto.getName())
				.addr(dto.getAddr())
				.build();
	}
}
