package com.example.boot11.service;

import java.util.List;

import org.springframework.ui.Model;

import com.example.boot11.dto.GalleryDto;


public interface GalleryService {
	public void addToGallery(GalleryDto dto);
	public void selectOne(Model model, int num);
	public List<GalleryDto> selectPage(int pageNum);
	public void deleteOne(int num);
}
