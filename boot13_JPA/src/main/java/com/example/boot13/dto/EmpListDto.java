package com.example.boot13.dto;



import com.example.boot13.entity.Emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpListDto {
	private Integer empno;
	private String ename;
	private Integer deptno;
	private String dname;
	private String job;
	
	public static EmpListDto toDto(Emp emp) {
		
		return EmpListDto.builder()
			.empno(emp.getEmpno())
			.ename(emp.getEname())
			.deptno(emp.getDept().getDeptno())
			.dname(emp.getDept().getDname())
			.job(emp.getJob())
			.build();
			
	}
}