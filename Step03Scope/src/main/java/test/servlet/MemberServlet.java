package test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dto.MemberDto;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//회원 한명의 정보를 얻어오는 비즈니스 로직수행
		MemberDto dto=new MemberDto(1,"김구라","노량진");
		
		//위의 회원 정보를 /test/member.jsp 페이지에서 응답하도록 프로그래밍해보세요
		
		//회원 한명의 정보를 담고 있는 MemberDto 객체를 request scope에 담기
		req.setAttribute("dto", dto); // "dto"라는 키값으로 MemberDto type을 담았다.
		
		// jsp페이지로 응답을 위임 시키기(jsp 페이지로 forward이동)
		RequestDispatcher rd= req.getRequestDispatcher("/test/member.jsp");
		
		rd.forward(req, resp);
	}
}
