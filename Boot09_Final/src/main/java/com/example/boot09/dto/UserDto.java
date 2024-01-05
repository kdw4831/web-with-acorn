package com.example.boot09.dto;

public class UserDto {
	//숫자로된 아이디는 PK
	private int id;
	//사용자명(user id)은 중복된 데이터가 들어가지 않도록
	private String userName;
	private String password;
	private String email;
	//Authority 정보를 저장할 칼럼 ROLE_XXX 형식이다.
	private String role;
	
	
	public UserDto() {}
	
	
	public UserDto(int id, String userName, String password, String email, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
