package com.example.boot13.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot13.dto.MemberDto;
import com.example.boot13.entity.Member;
import com.example.boot13.repository.MemberRepository;

import jakarta.transaction.Transactional;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository repo;
	
	@Override
	public void getList(Model model) {
		//Entity 목록
//		List<Member> entityList=repo.findAll();
//		List<MemberDto> list=new ArrayList<MemberDto>();		
//		for(Member tmp:entityList) {
//			MemberDto dto=new MemberDto();
//			dto.setNum(tmp.getNum());
//			dto.setName(tmp.getName());
//			dto.setAddr(tmp.getAddr());
//			list.add(dto);
//		}
		
		//스트림이란 자바스크립트에 .map() .filter 함수같은 것을 쓸 수 있도록 생각하면 됨
		//List<Member>를 Stream으로 변경해서 map() 메소드를 사용하고 다시 List<MemberDto>로 변경하기
		
		List<MemberDto> list=
				repo.findAllByOrderByNumDesc().stream().map(item ->MemberDto.toDto(item)).toList();
		
		/*
		 *  - 메소드 참조 표현식
		 *  
		 *  클래스명 :: 메소드명 
		 *  
		 *  MemberDto 클래스의 메소드   
		 */
		List<MemberDto> list2=
				repo.findAllByOrderByNumDesc().stream().map(MemberDto::toDto).toList();
		// 회원 목록을 모델에 담는다.
		model.addAttribute("list",list);
		
	}

	@Override
	public void insert(MemberDto dto) {
		//dto를 Entity로 변경해서 JpaRepository 객체를 이용해서 저장한다.
		repo.save(Member.toEntity(dto));
		
	}

	@Override
	public void delete(Long num) {
		repo.deleteById(num);
	}

	@Override
	public void getData(Long num, Model model) {
		// 회원의 번호를 이용해서 Member Entity 객체를 얻어온다.
		Member m = repo.findById(num).get();
		//Model에 담는다.
		model.addAttribute("dto", MemberDto.toDto(m));
		 
	}

	@Override
	public void update(MemberDto dto) {
		//save() 메소드는 insert 와 update 겸용이다
		repo.save(Member.toEntity(dto));
		
	}

	/*
	 * 만일 동일한 transaction 이라면
	 * 
	 * Member m1 = repo.findById(1).get();
	 * Member m2 = repo.findById(1).get();
	 * 
	 * m1 과 m2는 동일한 객체 이다 m1 == m2는 true
	 * 
	 * m1.setName(수정할 이름)
	 * m1.setAddr(수정할 주소)
	 * 
	 * 를 호출하면 실제로 DB에도 반영된다.
	 * 
	 * 서비스의 특정 메소드를 하나의 transaction 상에서 실행하려면 @Transactional 이라는 어노테이션을 붙이면 된다.
	 */
	
	@Transactional
	@Override
	public void update2(MemberDto dto) {
		// 수정할 회원의 번호를 이용해서  회원 정보 entity 객체 얻어내기
		Member m1= repo.findById(dto.getNum()).get();
		Member m2= repo.findById(dto.getNum()).get();
		boolean isEqual = m1 == m2;
		System.out.println("m1과 m2 같은지 여부: " + isEqual);
		
		//setter 메소드를 이용해서 이름과 주소 수정하기 
		m1.setName(dto.getName());
		m1.setAddr(dto.getAddr());
		
	}

	
	
}
