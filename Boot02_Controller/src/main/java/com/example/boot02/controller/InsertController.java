package com.example.boot02.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsertController {
   
    @PostMapping("/insert")
    public Map<String, Boolean> insert(String  email){
       
        System.out.println("전송된 이메일:" + email);
       
        Map<String, Boolean> map=new HashMap<>();
        map.put("isSuccess", true);
       
        return map;
    }
}