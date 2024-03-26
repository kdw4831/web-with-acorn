package com.example.boot13.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	
}
