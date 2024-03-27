package com.example.boot13.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.boot13.entity.Dept;
import com.example.boot13.entity.Emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeptDto {
	private int deptno;
	private String dname;
	private String loc;
	private int count; // 근무하는 인원수 
	private List<String> names; // 근무하는 사원의 이름들
	
	//Entity 를 Dto 로 변환하는 메소드
	public static DeptDto toDto(Dept dept) {
		//사원의 이름을 담을 List 
		List<String> names=new ArrayList<>();
		//반목문 돌면서 사원목록에서 사원의 이름을 List 에 누적 시킨다.
		for(Emp tmp:dept.getList()) {
			names.add(tmp.getEname());
		}
		
		return DeptDto.builder()
				.deptno(dept.getDeptno())
				.dname(dept.getDname())
				.loc(dept.getLoc())
				.count(dept.getList().size())
				.names(names)
				.build();
	}
	
}