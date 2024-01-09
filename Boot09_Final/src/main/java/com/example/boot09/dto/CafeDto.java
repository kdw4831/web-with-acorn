package com.example.boot09.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

}
