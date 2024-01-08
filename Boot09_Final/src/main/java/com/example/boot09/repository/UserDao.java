package com.example.boot09.repository;


import com.example.boot09.dto.UserDto;

import jakarta.websocket.Session;

public interface UserDao {
	
	public void insert(UserDto dto);
	public UserDto getData(String userName);
		
	

}
