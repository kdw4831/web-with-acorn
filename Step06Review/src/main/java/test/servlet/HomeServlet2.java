package test.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//3. 어떤 요청에 대해서 응답을 할지 매핑하기
@WebServlet("/home2")
public class HomeServlet2 extends HttpServlet { //1. httpServlet 클래스 상속
	
	//2. service() 메소드 오버라이드
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// java coding 공간
		
		//어떤 로직을 처리하고 결과를 얻어낼 수 있다.
		
		//필요하다면 DB에 접속을 해서 DB 관련 작업을 하고 
		List<String> notice =new ArrayList<>();
		notice.add("하나");
		notice.add("두울");
		notice.add("세엣");
		//클라이언트에게 응답도 바로 할 수 있다.
		//그렇지만 html 마크업을 응답하기에는 불편하기 때문에
		//응답에 필요한 모델(data)는 request 영역(HttpServletRequest) 에 담고
		//view page(jsp) 페이지로 forward 이동(응답을 위임)해서 응답한다.
		req.setAttribute("notice",notice);
		
		RequestDispatcher rd=req.getRequestDispatcher("/home2.jsp");
		rd.forward(req, resp);
	}
}
