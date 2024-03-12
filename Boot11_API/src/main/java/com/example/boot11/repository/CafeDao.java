package com.example.boot11.repository;

import java.util.List;

import com.example.boot11.dto.CafeDto;



public interface CafeDao {
	public void insert(CafeDto dto);
	public void update(CafeDto dto);
	public void delete(int num);
	public CafeDto getData(int num);
	public CafeDto getDetail(CafeDto dto);
	public List<CafeDto> getList(CafeDto dto);
	
	public int getCount(CafeDto dto);
	
	
}
