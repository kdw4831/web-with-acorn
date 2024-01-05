package com.example.boot07.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GalleryController {
	@Value("${file.location}")
	private String fileLocation;
	
	@PostMapping("gallery/upload")
	public String upload(MultipartFile image, Model m) {
		//저장할 파일의 이름 겹치지 않는 유일한 문자열로 얻어내기
		String saveFileName=UUID.randomUUID().toString();
		//저장할 파일의 전체 경로 구성하기
		String filePath=fileLocation+File.separator+saveFileName;
		try {	
			//업로드된 파일을 이동시킬 목적지 File 객체
			File f=new File(filePath);
			//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기기
			image.transferTo(f);
		}catch(Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("saveFileName",saveFileName);
		return "gallery/upload";
	}
	
	
	@GetMapping("/gallery/uploadform")
	public String uploadForm() {
		return "gallery/uploadform";
	}
	
	@GetMapping("/gallery/list")
	public String list() {
		return "gallery/list";
	}
	
	@ResponseBody
	@GetMapping(
			value = "/gallery/images/{imageName}", 
			//jpg, png, gif 이미지 데이터를 응답할 수 있도록 produces에 배열로 연결한다.
			produces= {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE,
			MediaType.IMAGE_GIF_VALUE}) //{경로변수}
	public byte[] image(@PathVariable("imageName") String name) throws IOException {
		//읽어드릴 파일 절대경로
		String absoutePath= fileLocation+File.separator+name;
		//파일에서 읽어들일 inputStream
		InputStream is =new FileInputStream(absoutePath);
		//commons io에 있는 IOUtils 클래스를 이용해서 이미지 파일에서 byte[]을 얻어낸다.
		return IOUtils.toByteArray(is);
	}
}
