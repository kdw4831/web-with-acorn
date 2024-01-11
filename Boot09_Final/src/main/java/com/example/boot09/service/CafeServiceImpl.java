package com.example.boot09.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot09.dto.CafeDto;
import com.example.boot09.exception.NotOwnerException;
import com.example.boot09.repository.CafeDao;

@Service
public class CafeServiceImpl implements CafeService{
	//한 페이지에 글을 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=5;
	//하단 페이지 UI를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;
		
	@Autowired private CafeDao cafeDao;
	
	@Override
	public void addtoWriting(CafeDto dto) {
		
		
		String writer=SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(writer);
		cafeDao.insert(dto);
	}

	@Override
	public void selectOne(Model model, int num) {
		CafeDto dto=cafeDao.getData(num);
		//id = writer지 이것도 같이 가져와서 담아줌
		String id=SecurityContextHolder.getContext().getAuthentication().getName();
		
		model.addAttribute("id",id);
		model.addAttribute("dto",dto);
		
	}

	@Override
	public void selectPage(Model model,CafeDto dto) {
		// pageNum 에 해당하는 글정보를 select 에서 Model 객체에 담는 작업을 하면 된다.

		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(dto.getPageNum()-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=dto.getPageNum()*PAGE_ROW_COUNT;
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((dto.getPageNum()-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 글의 갯수
		int totalRow=cafeDao.getCount(dto);
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}
		
		
		//위에서 계산된 startRowNum 과 endRowNum 을 담고
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//CafeDto 를 인자로 전달해서 글목록 얻어오기
		List<CafeDto> list=cafeDao.getList(dto);

		//view page에 전달할 내용을 Model 객체에 담는다.

		model.addAttribute("list",list);
		model.addAttribute("startPageNum",startPageNum);
		model.addAttribute("endPageNum",endPageNum);
		model.addAttribute("totalPageCount",totalPageCount);
		model.addAttribute("pageNum",dto.getPageNum());
		//model.addAttribute("keyword",dto.getKeyword());
		model.addAttribute("dto",dto);// 키워드정보가 들어있는 dto를 모델에 담기
		model.addAttribute("totalRow",totalRow);
	}

	@Override
	public void deleteOne(int num) {
		//글 작성자와
		String writer=cafeDao.getData(num).getWriter();
		//로그인된 사용자와 같은 경우에만 삭제
		String id=SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(id)) {
			throw new NotOwnerException("글 작성자와 일치 하지 않습니다.");
		}
		// DB에서 num에 해당하는 글 삭제하기
		cafeDao.delete(num);
		
	}

	@Override
	public void updateOne(CafeDto dto) {
		cafeDao.update(dto);
		
	}

	@Override
	public void selectOne2(Model model, int num) {
		CafeDto dto=cafeDao.getData(num);
		model.addAttribute("dto",dto);
	}

}
