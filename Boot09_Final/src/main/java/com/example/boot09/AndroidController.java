package com.example.boot09;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot09.dto.GalleryDto;
import com.example.boot09.repository.GalleryDao;



//@RestController가 default 값인 컨트롤러
@RestController
public class AndroidController {
	@Autowired
	private GalleryDao galleryDao;
	
	@GetMapping("/api/gallery/{num}")
	public GalleryDto gallery(@PathVariable int num) {
		
		return galleryDao.getdata(num);
	
	}
	
	@GetMapping("/api/galleries")
	public List<GalleryDto> galleries(){
		GalleryDto dto= new GalleryDto();
		dto.setStartRowNum(1);
		dto.setEndRowNum(10);
		
		return galleryDao.getList(dto);
	}
	
	@GetMapping("/api/notices")
	public List<String> notice(){
		List<String> notices= new ArrayList<String>();
		
		notices.add("곧 프로젝트가 시작 됩니다.");
		notices.add("다음주 부터입니다.");
		notices.add("어쩌구... 저쩌구...");
		
		return notices;
	}
	
	
}
