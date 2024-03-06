package com.example.boot11.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.example.boot11.dto.GalleryDto;


public interface GalleryService {
	public void addToGallery(GalleryDto dto);
	public GalleryDto selectOne( int num);
	public Map<String,Object> selectPage(int pageNum);
	public void deleteOne(int num);
}
