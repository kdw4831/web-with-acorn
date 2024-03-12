package com.example.boot11.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.example.boot11.dto.CafeDto;
import com.example.boot11.repository.CafeDao;


@Service
public class CafeServiceImpl implements CafeService{
	//한 페이지에 글을 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=5;
	//하단 페이지 UI를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;

	@Autowired
	private CafeDao cafeDao;

	@Override
	public Map<String,Object> selectPage(int pageNum) {
		// pageNum 에 해당하는 글정보를 select 에서 Model 객체에 담는 작업을 하면 된다.


		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;

		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//임시로 빈 dto를 생성해서 전체 글의 갯수를 얻어온다.
		CafeDto dto= new CafeDto();
		//전체 글의 갯수
		int totalRow=cafeDao.getCount(dto);
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}

		//위에서 계산된 startRowNum 과 endRowNum 을 dto 담고
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		//CafeDto 를 인자로 전달해서 글목록 얻어오기
		List<CafeDto> list=cafeDao.getList(dto);

		//react frontend 에서 필요한 데이터를 Map 에 담는다 
		Map<String, Object> map = Map.of("list", list, 
				"startPageNum", startPageNum,
				"endPageNum", endPageNum,
				"totalPageCount", totalPageCount,
				"pageNum", pageNum);
		
		return map;
	}

	@Override
	public void addtoWriting(CafeDto dto) {
		//글 작성자를 dto 에 담아서 
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(userName);
		//DB 에 저장 
		cafeDao.insert(dto);
	}

	@Override
	public CafeDto selectOne(CafeDto dto) {
		//글번호를 이용해서 글 하나의 정보를 얻어와서 
		CafeDto resultDto=cafeDao.getDetail(dto);
		//원래의 검색 조건을 글정보가 들어 있는 결과 dto 에 추가해준다. 
		resultDto.setCondition(dto.getCondition());
		resultDto.setKeyword(dto.getKeyword());
		//userName 도 읽어와서 담아준다(로그인 되지 않았다면 null 이다)
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		
//		//Model 객체에 담아준다.
//		model.addAttribute("dto", resultDto);
//		model.addAttribute("userName", userName);
		return resultDto;
	}

	@Override
	public void deleteOne(int num) {
		//글 작성자와 
		String writer=cafeDao.getData(num).getWriter();
		//로그인된 사용자와 같은 경우에만 삭제
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();

		//DB 에서 num 에 해당하는 글 삭제하기
		cafeDao.delete(num);
	}

	@Override
	public void selectOne2(Model model, int num) {
		CafeDto dto=cafeDao.getData(num);
		model.addAttribute("dto", dto);
	}

	@Override
	public void updateOne(CafeDto dto) {
		cafeDao.update(dto);
	}

}