package com.example.boot09.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CafeService {
	
	@GetMapping("/cafe/list")
	public String list() {
		return "cafe/list";
	}
}
