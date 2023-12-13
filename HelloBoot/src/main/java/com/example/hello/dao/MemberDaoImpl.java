package com.example.hello.dao;

import com.example.hello.dto.MemberDto;

public class MemberDaoImpl implements MemberDao {

	@Override
	public void insert(MemberDto dto) {
		System.out.println("저장 했습니다.");
		
	}

	@Override
	public void update(MemberDto dto) {
		System.out.println("수정 했습니다.");
		
	}

	@Override
	public MemberDto getData(int num) {
		System.out.println("회원 한명의 정보를 리턴했습니다.");
		return null;
	}

}
