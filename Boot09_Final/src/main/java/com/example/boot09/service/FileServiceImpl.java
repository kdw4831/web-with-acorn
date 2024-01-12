package com.example.boot09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.example.boot09.dto.FileDto;
import com.example.boot09.repository.FileDao;

public class FileServiceImpl implements FileService{
	final int PAGE_ROW_COUNT=5;
	final int PAGE_DISPLAY_COUNT=5;
	
	@Autowired private FileDao fileDao;

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
		int totalRow=fileDao.getCount();
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		List<FileDto> list=fileDao.getList(dto);
		// 이제 model에 넘겨보자
		
		
		
		
	}

	
}
