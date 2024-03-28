package com.example.boot13.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.boot13.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer>{
	
	//사원 번호에 대해서 오름 차순 정렬된 목록을 반환하는 메소드 만들기
	public List<Emp> findAllByOrderByEmpnoAsc();
	
	//검색 키워드를 반영하는 메소드
	// ename 에 keyword 가 포함된 
	public Page<Emp> findByEnameContaining(String keyword, Pageable pageable);
	// ename 에 keyword 가 포함되거나 Or job 에 keyword2 가 포함된 
	public Page<Emp> findByEnameContainingOrJobContaining(String keyword, String keyword2, Pageable pageable);
	
	/*
	 *  Java Persistence Query Language (JPQL)
	 *  - JPQL은 SQL과 유사하지만 엔티티와 속성에 기반하여 작성되며, 데이터베이스 종속적이지 않음
	 *  - JPQL 만의 문법이 존재한다 
	 *  - 테이블명 대신에 => Entity 명 
	 *  - Entity 의 별칭은 필수 
	 *  - select 된 row 의 정보를 Entity 혹은 Dto 에 담을수 있다. 
	 */
	@Query(value = "select e from Emp e order by e.empno asc")
	public List<Emp> getListAll();
	
	// selection 인자를 전달할수 있다  :변수명
	@Query(value = "select e from Emp e where sal > :sal order by e.sal asc")
	public List<Emp> getList(int sal);
	// selection 인자를 여러개 전달할수 있다  :변수명 , :변수명2
	@Query(value = "select e from Emp e where sal > :sal and sal < :sal2 order by e.sal asc")
	public List<Emp> getListBetWeen(int sal, int sal2);
	// selection 인자를 순서대로 전달할수 있다  ?1 , ?2   여기서 1, 2 는 순서 
	@Query(value = "select e from Emp e where sal > ?1 and sal < ?2 order by e.sal asc")
	public List<Emp> getListBetWeen2(int sal, int sal2);
}