package com.example.boot09.repository;

import java.util.List;

import com.example.boot09.dto.CafeDto;

public interface CafeDao {
	public void insert(CafeDto dto);
	public void update(CafeDto dto);
	public void delete(int num);
	public CafeDto getData(int num);
	public List<CafeDto> getList(CafeDto dto);
	
	public int getCount();
	
	
}
