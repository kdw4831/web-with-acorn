package com.example.boot13.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot13.dto.DeptDto;
import com.example.boot13.dto.EmpListDto;
import com.example.boot13.entity.Dept;
import com.example.boot13.entity.Emp;
import com.example.boot13.repository.DeptRepository;
import com.example.boot13.repository.EmpRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;



@Controller
public class EmployController {
	
	@Autowired
	private EmpRepository repo;
	@Autowired 
	private DeptRepository deptRepo;
	
	@Autowired
	private EntityManagerFactory factory;
	
	@GetMapping("/jpql")
	public String jpql() {
		
		return "jpql";
	}
	
	@ResponseBody
	@PostMapping("/jpql/test")
	public List<EmpListDto> test(String query) {
		EntityManager em=factory.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		List<EmpListDto> list=null;
		try {
			//전달된 query(jpql) 문을 전달해서 query 를 실행할 준비를 한다. 
			TypedQuery<Emp> tQuery=em.createQuery(query, Emp.class);
			//실제 실행해서 stream 으로 얻어낸다음 List<EmpListDto> 로 변환한다.
			list=tQuery.getResultStream().map(EmpListDto::toDto).toList();
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		//결과 리턴하기 
		return list;
	}
	
	@GetMapping("/employ/dept")
	public String deptDetail(int deptno, Model model) {
		//deptno 에 해당하는 부서 정보를 얻어와서 
		Dept d=deptRepo.findById(deptno).get();
		//DeptDto 로 변경한다음 
		DeptDto dto=DeptDto.toDto(d);
		//view page 에서 사용할수 있도록 Model 객체에 담아준다
		model.addAttribute("dto", dto);
		return "employ/dept";
	}
	
	
	@GetMapping("/employ/list")
	public String list(Model model) {
		//모든 사원의 목록을 얻어온다.
		//List<Emp> list=repo.findAll();
		//List<Emp> list=repo.findAllByOrderByEmpnoAsc();
		
		//JPQL 을 사용하는 메소드 이용해 보기
		//List<Emp> list=repo.getListAll();
		List<Emp> list=repo.getList(3000);
		
		// .stream() 을 이용해서 Entity 목록을 Dto 목록으로 변경한다.
		List<EmpListDto> list2=list.stream().map(EmpListDto::toDto).toList(); 
		// view page 에 전달 할수 있도록 Model 객체에 담는다.
		model.addAttribute("list", list2);
		return "employ/list";
	}
	
	@ResponseBody
	@GetMapping("/employ/list2")
	public List<EmpListDto> list2(int pageNum) {
		//한페이지에 몇개씩 표시할것인지
		final int PAGE_ROW_COUNT=5;
		//정렬을 먼저 결정을 해야 한다.
		Sort sort=Sort.by(Sort.Direction.ASC, "empno"); // 사원번호에 대해서 오름차순 정렬
		/*
		 *  .of(페이지 인덱스, 한페이지에 나타낼 갯수 , 정렬객체 )
		 *  
		 *  PageRequest 객체가 리턴되는데 PageRequest 는 Pageable 인터페이스를 구현한 객체 이다.
		 */
		Pageable pagable=PageRequest.of(pageNum-1, PAGE_ROW_COUNT, sort);
		// Pageable 객체를 이용해서 해당 페이지에 맞는 정보를 얻어낸다.
		Page<Emp> page=repo.findAll(pagable);
		//Page<Emp> 를 List<EmpListDto> 로 변경하기 
		List<EmpListDto> list= page.stream().map(EmpListDto::toDto).toList();
		return list;
	}
	
	@GetMapping("/employ/list3")
	public String list(Model model, @RequestParam(defaultValue = "1") int pageNum) {
		//한페이지에 나타낼 row 의 갯수
		final int PAGE_ROW_COUNT=5;
		//하단 페이지 표시 갯수 
		final int PAGE_DISPLAY_COUNT=5;
		
		//empno 에 대해서 오름차순 정렬하겠다는 정보를 담고 있는 Sort 객체 만들기
		//Sort sort=Sort.by(Sort.Direction.ASC, "empno"); 
		
		// dept.deptno , ename 에 대해서 오름 차순 정렬
		//Sort sort=Sort.by(Sort.Direction.ASC, "dept.deptno", "ename");
		
		// dept.deptno 에 대해 오름차순, ename 에 대해 내림차순 정렬
		Sort sort=Sort.by(Order.asc("dept.deptno"), Order.desc("ename"));
		
		//원하는 페이지정보를 담고 있는 Pageable 객체를 얻어내서 
		Pageable pagable=PageRequest.of(pageNum-1, PAGE_ROW_COUNT, sort);
		//JpaRepository 객체에 전달해서 원하는 페이지 정보를 얻어낸다 
		Page<Emp> page=repo.findAll(pagable);
		// Emp 목록을 EmpListDto 목록으로 변환 
		List<EmpListDto> list= page.stream().map(EmpListDto::toDto).toList();
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 페이지의 갯수 구하기
		int totalPageCount=page.getTotalPages();
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}
		
		//pageNum 에 해당하는 사원 목록을 Model 에 담는다 
		model.addAttribute("list", list);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("pageNum", pageNum);
		
		return "employ/list3";
	}
	
	@GetMapping("/employ/list4")
	public String list4(Model model, @RequestParam(defaultValue = "1") int pageNum, String keyword) {
		//검색 키워드가 넘어온다고 가정하자
		keyword="S";
		
		//한페이지에 나타낼 row 의 갯수
		final int PAGE_ROW_COUNT=5;
		//하단 페이지 표시 갯수 
		final int PAGE_DISPLAY_COUNT=5;
		
		//empno 에 대해서 오름차순 정렬하겠다는 정보를 담고 있는 Sort 객체 만들기
		//Sort sort=Sort.by(Sort.Direction.ASC, "empno"); 
		
		// dept.deptno , ename 에 대해서 오름 차순 정렬
		//Sort sort=Sort.by(Sort.Direction.ASC, "dept.deptno", "ename");
		
		// dept.deptno 에 대해 오름차순, ename 에 대해 내림차순 정렬
		Sort sort=Sort.by(Order.asc("dept.deptno"), Order.desc("ename"));
		
		//원하는 페이지정보를 담고 있는 Pageable 객체를 얻어내서 
		Pageable pagable=PageRequest.of(pageNum-1, PAGE_ROW_COUNT, sort);
		//JpaRepository 객체에 전달해서 원하는 페이지 정보를 얻어낸다 
		//Page<Emp> page=repo.findByEnameContaining(keyword, pagable);
		Page<Emp> page=repo.findByEnameContainingOrJobContaining(keyword, keyword, pagable);
		
		// Emp 목록을 EmpListDto 목록으로 변환 
		List<EmpListDto> list= page.stream().map(EmpListDto::toDto).toList();
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 페이지의 갯수 구하기
		int totalPageCount=page.getTotalPages();
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}
		
		//pageNum 에 해당하는 사원 목록을 Model 에 담는다 
		model.addAttribute("list", list);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("pageNum", pageNum);
		
		return "employ/list4";
	}
}