package com.example.boot09.repository;

import java.util.List;

import com.example.boot09.dto.FileDto;

public interface FileDao {
	public void insert(FileDto dto);
	public FileDto getData(int num);
	public void delete(int num);
	//검색 조건과 원하는 page에 부합하는 목록가져오기
	public List<FileDto> getList(FileDto dto);
	//검색 조건에 맞는 자료의 갯수 가져오기
	public int getCount(FileDto dto);
}
