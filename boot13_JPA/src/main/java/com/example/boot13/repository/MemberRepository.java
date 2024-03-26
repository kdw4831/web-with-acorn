package com.example.boot13.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.boot13.entity.Member;

/*
 * JpaRepository<Entity 클래스, Entity 클래스 안에서 id 역할을 하는 칼럼의 data type>
 */

@Repository // extends JpaRepository 하는 것 만으로 bean 이 만들어 지기 때문에 생략 가능
public interface MemberRepository extends JpaRepository<Member, Long> {
	/*
	 * 정렬된 결과를 select 하는 메소드를 custom으로 추가하기 
	 * 
	 * - 정해진 형식이 있다
	 * 
	 * findAllByOrderBy칼럼명Desc()
	 * findAllByOrderBy칼럼명Asc()
	 * 
	 * 컬럼명은 camel case로 작성	
	 */
	
	public List<Member> findAllByOrderByNumDesc();

}
