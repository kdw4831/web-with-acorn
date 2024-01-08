package com.example.boot09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.example.boot09.dto.GalleryDto;


//업로드된 파일을 저장하는 위치정보 로딩
@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class Boot09FinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot09FinalApplication.class, args);
//		GalleryDto dto =new GalleryDto();
//		dto.setNum(1);
//		dto.setWriter("kim");
//		
//		GalleryDto dto2=GalleryDto.builder().num(1).writer("kim").build();
		
	}

}
