package com.example.boot09.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot09.dto.FileDto;

public interface FileService {
   public void getList(Model model,FileDto dto);
   public void saveFile(FileDto dto);
   // 다운로드 해줄 파일 하나의 정보 얻어오기 
   public ResponseEntity<InputStreamResource> getFileData(int num);
   //파일 삭제
   public void deleteFile(int num);
}