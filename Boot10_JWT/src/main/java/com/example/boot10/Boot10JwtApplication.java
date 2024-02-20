package com.example.boot10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//custom.properties 파일 로딩하도록 설정
@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class Boot10JwtApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(Boot10JwtApplication.class, args);
	}

}
