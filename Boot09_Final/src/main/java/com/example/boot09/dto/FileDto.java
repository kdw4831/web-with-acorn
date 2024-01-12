package com.example.boot09.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("fileDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDto {
	private int num;
	private String userName;
	private String title;
	private String orgFileName;
	private String saveFileName;
	private long fileSize;
	private String regdate;
	//페이징 처리를 위한 추가 필드
	private int startRowNum;
	private int endRowNum;
	private int pageNum=1;
}
