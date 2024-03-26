package com.example.boot13.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boot13.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer>{
	//사원 번호에 대해서 오름 차순 정렬된 목록을 반환하는 메소드 만들기
	public List<Emp> findAllByOrderByEmpnoAsc();
	
	
	
}
