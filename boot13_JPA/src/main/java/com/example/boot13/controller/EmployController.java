package com.example.boot13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot13.dto.DeptDto;
import com.example.boot13.dto.EmpListDto;
import com.example.boot13.entity.Dept;
import com.example.boot13.entity.Emp;
import com.example.boot13.repository.DeptRepository;
import com.example.boot13.repository.EmpRepository;

@Controller
public class EmployController {
	
	@Autowired
	private EmpRepository repo;
	@Autowired 
	private DeptRepository deptRepo;
	

	
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
		List<Emp> list=repo.findAllByOrderByEmpnoAsc();
		// .stream() 을 이용해서 Entity 목록을 Dto 목록으로 변경한다.
		List<EmpListDto> list2=list.stream().map(EmpListDto::toDto).toList(); 
		// view page 에 전달 할수 있도록 Model 객체에 담는다.
		model.addAttribute("list", list2);
		return "employ/list";
	}
	
	@ResponseBody
	@GetMapping("/employ/list2")
	public List<EmpListDto> list2(int pageNum) {
		//한페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=5;
		//정렬을 먼저 결정을 해야한다.
		Sort sort=Sort.by(Sort.Direction.ASC, "empno");
		/*
		 * .of(페이지 인덱스 , 한페이지에 나타낼 갯수, 정렬객체 )
		 * 
		 * PageRequest 객체가 리턴되는데 PageRequest는 Pageable 인터페이스를 구현한 객체 이다.
		 */
		Pageable pageable=PageRequest.of(pageNum-1,PAGE_ROW_COUNT,sort);
		//Pageable 객체를 이용해서 해당 페이지에 맞는 정보를 얻어낸다.
		Page<Emp> page=repo.findAll(pageable);
		//Page<Emp>를 List<EmpListDto>로 변경하기
		List<EmpListDto> list=page.stream().map(EmpListDto::toDto).toList();
		return list;
	}
	
	
	@GetMapping("/employ/list3")
	public String list(Model model,@RequestParam(defaultValue="1") int pageNum) {
	
		final int PAGE_ROW_COUNT=5;
	
		Sort sort=Sort.by(Sort.Direction.ASC, "empno");
	
		Pageable pageable=PageRequest.of(pageNum-1,PAGE_ROW_COUNT,sort);
	
		Page<Emp> page=repo.findAll(pageable);
		page.getTotalPages();
	
		List<EmpListDto> list=page.stream().map(EmpListDto::toDto).toList();
		
		//pageNum에 해당하는 사원 목록을 Model에 담는다. 
		model.addAttribute("list",list);
		return "employ/list3";
	}
}