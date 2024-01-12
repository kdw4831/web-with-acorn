package com.example.boot09.service;

import org.springframework.ui.Model;

import com.example.boot09.dto.FileDto;

public interface FileService {
	public void getList(Model model , FileDto dto);
}
