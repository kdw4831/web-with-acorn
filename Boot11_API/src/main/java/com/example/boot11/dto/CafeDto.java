package com.example.boot11.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//mapper.xml 문서에서 CafeDto type을 별칭으로 이용해서 사용할 수 있다.
@Alias("cafeDto") //mybatis에서 사용하는 type alias
@Builder
@AllArgsConstructor//모든 인자를 받는 생성자
@NoArgsConstructor //디폴트 생성자만들기
@Data //setter, getter만들기
public class CafeDto {
	private int num;
	private String writer;
	private String title;
	private String content;
	private int viewCount;
	private String regdate;
	//페이징 처리를 위한 추가 필드
	private int startRowNum;
	private int endRowNum;
	//검색 기능 관련된 필드
	private String condition;
	private String keyword;
	private int pageNum=1;
	
	//이전글과 다음글의 글번호를 담을 필드
	private int prevNum, nextNum;

}
