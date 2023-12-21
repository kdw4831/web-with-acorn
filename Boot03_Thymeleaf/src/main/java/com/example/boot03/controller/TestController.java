package com.example.boot03.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.boot03.dto.MemberDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class TestController {
	@GetMapping("/escape_test")
	public String escape_test(Model model) {
		//모델이 마크업 형식의 문자열일 수도 있다.
		model.addAttribute("markup", "<a href='http://daum.net'>daum</a>");
		//url 정보를 테스트로 담기
		model.addAttribute("url", "/shop/buy?id=111&amount=2");
		return "sub/escape_test";
	}
	
	@GetMapping("/inc")
	public String inc() {
		return "sub/inc";
	}
	
	//가상의 로그인
	@GetMapping("/user/login")
	public String login(HttpSession session) {
		session.setAttribute("id", "kimgura");
		return "sub/login";
	}
	
	//가상의 로그아웃
	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		//최상위 경로로 리다이렉트 이동
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String members(Model model) {
		List<MemberDto> list= new ArrayList<MemberDto>();
		MemberDto dto= new MemberDto(1,"김구라", "노량진");
		MemberDto dto2= new MemberDto(2,"원숭이", "동물원");
		MemberDto dto3= new MemberDto(3,"해골", "행신동");
		list.add(dto);
		list.add(dto2);
		list.add(dto3);
		//모델 객체에 담으면 자동으로 httpServletRequest 객체에 이런식으로 담기는 것이다.
		//Spring 프레임워크가 담아준다.
		model.addAttribute("list",list);
		return "sub/members";
	}
	
	@GetMapping("/notice")
	public String notice(Model model) {
		//DB에서 읽어온 공지사항이라고 가정
		List<String> list= new ArrayList<>();
		list.add("날씨가 너무 추워요");
		list.add("곧 크리스마스입니다.");
		list.add("어쩌구 저쩌구...");
		model.addAttribute("list",list);
		return "sub/notice";
	}
	
	@GetMapping("/member")
	public String member(Model model) {
		//응답에 필요한 데이터
		MemberDto dto= new MemberDto(1,"김구라", "노량진");
		//응답에 필요한 데이터를 Model 객체에 담는다.
		model.addAttribute("dto",dto);
		
		// /template/sub/member.html
		return "sub/member";
	}
	
	
	//Get 방식 /shop/buy?id=xxx&amount=xxx요청을 처리할 컨트롤러 메소드
	@GetMapping("/shop/buy")
	//매개 변수명을 파라미터명과 동일하게 작성하면 파라미터가 자동추출되어서 전달된다.
	public String buy(String id, int amount) {
		System.out.println(id+"|" + amount);
		// /templates/shop/buy.html
		return "shop/buy";
	}
	
	
	//Get 방식 /sub/play 요청을 처리할 컨트롤러 메소드
	@GetMapping("/sub/play")
	public String play() {
		
		// /template/sub/play.html 템플릿을 해석해서 응답하기
		return "sub/play";
	}
	
	
}
