package com.example.boot09.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Alias("userDto") // mapper xml 에서 사용할 type alias 설정
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	//숫자로된 아이디는 PK
	private int id;
	//사용자명(user id)은 중복된 데이터가 들어가지 않도록
	private String userName;
	private String password;
	private String email;
	//Authority 정보를 저장할 칼럼 ROLE_XXX 형식이다.
	private String role;
	private String profile;
	private String regdate;
}
	
	
	
