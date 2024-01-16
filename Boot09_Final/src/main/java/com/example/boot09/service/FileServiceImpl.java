package com.example.boot09.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot09.dto.FileDto;
import com.example.boot09.exception.NotOwnerException;
import com.example.boot09.repository.FileDao;
@Service
public class FileServiceImpl implements FileService{
   //한페이지에 글을 몇
   final int PAGE_ROW_COUNT=5;
   final int PAGE_DISPLAY_COUNT=5;
   
   @Autowired private FileDao dao;
   
   //파일을 저장할 위치 
   @Value("${file.location}") 
   private String fileLocation;

   @Override
   public void getList(Model model, FileDto dto) {
      //pageNum에 해당하는 글정보를 select해서 Model에 저장해서 넘겨보자
      
      int startRowNum=1+(dto.getPageNum()-1)*PAGE_ROW_COUNT;
      int endRowNum=dto.getPageNum()*PAGE_ROW_COUNT;
      //하단 시작 페이지 번호 
     
      int startPageNum = 1 + ((dto.getPageNum()-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
      //하단 끝 페이지 번호
      int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
      //전체 글의 갯수
      int totalRow=dao.getCount(dto);
      //전체 페이지의 갯수 구하기
      int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
      //끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
      if(endPageNum > totalPageCount){
         endPageNum=totalPageCount; //보정해 준다. 
      }
      dto.setStartRowNum(startRowNum);
      dto.setEndRowNum(endRowNum);
      
      
      List<FileDto> list=dao.getList(dto);
      // 이제 model에 넘겨보자
      
      model.addAttribute("list",list);
      model.addAttribute("startPageNum",startPageNum);
      model.addAttribute("endPageNum",endPageNum);
      model.addAttribute("totalPageCount",totalPageCount);
      model.addAttribute("pageNum",dto.getPageNum());
      model.addAttribute("dto",dto);// 키워드정보가 들어있는 dto를 모델에 담기
      model.addAttribute("totalRow",totalRow);
      
      
      
      	
   }



   

	
	@Override
	public void saveFile(FileDto dto) {
		//파일 업로드 처리를 위한 객체의 참조값 얻어오기 (업로드 된 파일에 대한 정보를 얻어낼 객체)
		MultipartFile myFile= dto.getMyFile();
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일크기
		long fileSize= myFile.getSize();
		//저장할 파일명을 하나 얻어낸다.
		String saveFileName=UUID.randomUUID().toString();
		//저장할 파일의 상세 경로
		// C:/acorn202310/upload/adsafdsf
		String filePath=fileLocation+File.separator+saveFileName;
		try {
			//file 객체 생성
			File f= new File(filePath);
			//파일 원하는 곳에  저장하기 
			myFile.transferTo(f);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//DB에 업로드된 파일에 대한 정보를 저장한다.
		String writer=SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(writer);
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		
		dao.insert(dto);
		
	}



	@Override
	public ResponseEntity<InputStreamResource> getFileData(int num) {
		//다운로드 해줄 파일의 정보를 DB 에서 읽어온다.
		FileDto dto=dao.getData(num);
		ResponseEntity<InputStreamResource> responseEn=null;
		try {
			//다운로드 시켜줄 원본 파일명1
			String encodedName=URLEncoder.encode(dto.getOrgFileName(), "utf-8");
			//파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
			encodedName=encodedName.replaceAll("\\+"," ");
			//응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
			HttpHeaders headers=new HttpHeaders();
			//파일을 다운로드 시켜 주겠다는 정보
			headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
			//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
			//파일의 크기 정보도 담아준다.2
			headers.setContentLength(dto.getFileSize());
			
			//읽어들일 파일의 경로 구성3
			String filePath=fileLocation + File.separator + dto.getSaveFileName();
			//파일에서 읽어들일 스트림 객체 생성
			InputStream is= new FileInputStream(filePath);
			//InputStreamResource 객체를 얻어내서 지역변수에 담고 
			InputStreamResource isr=new InputStreamResource(is);
			responseEn=ResponseEntity.ok().headers(headers).body(isr);
		}catch(Exception e) {
			//예외를 던지고  ExceptionController 에서 처리 할수 있다.
			throw new RuntimeException("파일 다운로드 중에 예외가 발생했습니다.");
		}
		//InputStreamResource  객체를 리턴해준다.
		return responseEn;
	}






	@Override
	public void deleteFile(int num) {
		//DB에서 삭제할 파일의 정보를 읽어온다.
		FileDto dto=dao.getData(num);
		//로그인된 사용자와 파일의 소유자가 같은지 확인해서 다르면 exception 발생시키기
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		if(!dto.getWriter().equals(userName)) {
			throw new NotOwnerException("파일의 소유자가 아닙니다.");
		}
		//파일 시스템에서 실제로 삭제하고 
		String filePath=fileLocation + File.separator + dto.getSaveFileName();
		File f=new File(filePath);
		f.delete();
		//DB에서도 삭제
		
		dao.delete(num);
	}



   
}