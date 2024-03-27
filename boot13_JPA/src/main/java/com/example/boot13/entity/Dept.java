package com.example.boot13.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dept {
	@Id
	private int deptno;
	private String dname;
	private String loc;
	
	//Dept entity 의 연관되는 필드명을 mappedBy = "필드명" 을 설정해야 한다 
	@OneToMany(mappedBy = "dept", fetch = FetchType.EAGER) //default 값은 LAZY 이다 
	private List<Emp> list=new ArrayList<>();
	
}