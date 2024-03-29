package com.example.boot09.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot09.dto.CafeCommentDto;
import com.example.boot09.dto.CafeDto;
import com.example.boot09.exception.NotOwnerException;
import com.example.boot09.repository.CafeCommentDao;
import com.example.boot09.repository.CafeDao;

@Service
public class CafeServiceImpl implements CafeService{
	//한 페이지에 글을 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=5;
	//하단 페이지 UI를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;
		
	@Autowired private CafeDao cafeDao;
	
	@Autowired private CafeCommentDao commentDao;
	
	@Override
	public void addtoWriting(CafeDto dto) {
		
		
		String writer=SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(writer);
		cafeDao.insert(dto);
	}

	@Override//getDetails
	public void selectOne(Model model, CafeDto dto) {
		//글번호를 이용해서 글 하나의 정보를 얻어와서
		CafeDto resultDto=cafeDao.getDetail(dto);
		//원래의 검색 조건을 글정보가 들어있는 결과 dto에 추가해준다.
		resultDto.setCondition(dto.getCondition());
		resultDto.setKeyword(dto.getKeyword());
		//id = writer지 이것도 같이 가져와서 담아줌(로그인이 되지 않았다면 null이다.)
		String id=SecurityContextHolder.getContext().getAuthentication().getName();
		
		//댓글 목록을 얻어온다.
		//CafeDto에 ref_group 번호를 담아서 dao에 전달해서 댓글 목록을 얻어낸다.
		CafeCommentDto commentDto= new CafeCommentDto();
		//원글의 글번호를 담아서
		commentDto.setRef_group(dto.getNum());
		
		//댓글의 페이지 번호
		int pageNum=1;
		/*
		 	[댓글 페이징 처리에 관련된 로직]
		*/
		//한 페이지에 댓글을 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=10;
		
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 RowNum
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		//계산된 값을 dto에 담는다.
		commentDto.setStartRowNum(startRowNum);
		commentDto.setEndRowNum(endRowNum);
			
		
		
		//원글에 달린 댓글 목록을 얻어내기
		List<CafeCommentDto> commentList=commentDao.getList(commentDto);
		System.out.println("원글번호"+dto.getNum());
		//원글에 글번호를 이용해서 댓글 전체의 갯수를 얻어낸다.
		int totalRow=commentDao.getCount(dto.getNum());
		System.out.println("원글번호2"+dto.getNum());
		//댓글 전체 페이지의 갯수
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		
			
		model.addAttribute("id",id);
		model.addAttribute("dto",resultDto);
		model.addAttribute("commentList",commentList);
		model.addAttribute("totalPageCount",totalPageCount);
		
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

	@Override
	public void saveComment(CafeCommentDto dto) {
		// 댓글 작성자는 SpringSecurity로 부터 얻어내기
		String writer=SecurityContextHolder.getContext().getAuthentication().getName();
		//글번호를 미리 얻어낸다.
		int num=commentDao.getSequence();
		dto.setWriter(writer);
		dto.setNum(num);
		//만일 comment_group 번호가 넘어오지 않으면 원글의 댓글이다.
		if(dto.getComment_group()==0) {
			//원글의 댓글의 경우 댓글의 번호(num)가 곧 comment_group 번호가 된다.
			dto.setComment_group(num);
			
		}
		//DB 에 저장하기
		commentDao.insert(dto);
	}

	@Override
	public void deleteComment(int num) {
		// 로그인된 사용자의 댓글 작성자가 같은지 확인
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		String writer = commentDao.getData(num).getWriter();
		if(!userName.equals(writer)) {
			throw new NotOwnerException("댓글의 소유자가 아닙니다!");
		}
		//삭제 작업을 한다.
		commentDao.delete(num);
		
	}

	@Override
	public void updateComment(CafeCommentDto dto) {
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		String writer=commentDao.getData(dto.getNum()).getWriter();
		if(!userName.equals(writer)) {
			throw new NotOwnerException("댓글의 소유자가 아닙니다!");
		}
		//수정
		commentDao.update(dto);
		
	}

	@Override
	public void getCommentList(Model model, CafeCommentDto dto) {
		//요청된 댓글의 페이지 번호
		int pageNum=dto.getPageNum();
		/*
		 	[댓글 페이징 처리에 관련된 로직]
		*/
		//한 페이지에 댓글을 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=10;
		
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 RowNum
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		//계산된 값을 dto에 담는다.
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		
		//pageNum에 해당하는 댓글 목록만 select 되도록 한다.
		List<CafeCommentDto> commentList=commentDao.getList(dto);
		
		//응답에 필요한 데이터를 Model 객체에 담는다.
		model.addAttribute("commentList", commentList);//댓글 목록
		model.addAttribute("pageNum", pageNum);//페이지번호
		model.addAttribute("ref_group", dto.getRef_group()); //원글의 글번호
		
	}

}
