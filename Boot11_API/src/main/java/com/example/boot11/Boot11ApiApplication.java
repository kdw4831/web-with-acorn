package com.example.boot11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//custom.properties 파일 로딩하도록 설정
@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class Boot11ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot11ApiApplication.class, args);
	}

}
