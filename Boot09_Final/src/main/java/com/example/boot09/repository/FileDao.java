package com.example.boot09.repository;

import java.util.List;

import com.example.boot09.dto.FileDto;

public interface FileDao {
	public List<FileDto> getList(FileDto dto);
	public int getCount();
}
