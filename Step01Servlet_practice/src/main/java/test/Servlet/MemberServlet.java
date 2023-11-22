package test.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDto;


@WebServlet("/member/list")
public class MemberServlet extends HttpServlet {
	 @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MemberDto> list=new ArrayList<MemberDto>();
		list.add(new MemberDto(1,"김구라","노량진"));
		list.add(new MemberDto(1,"해골","행신동"));
		list.add(new MemberDto(1,"원숭이","동물원"));
		
		//1.응답인코딩
		//2.응답 컨텐트 설정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("test/html; charset=utf-8");
		
		PrintWriter pw= resp.getWriter();
		
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title>회원 목록 보기</title>");
		pw.println("</head>");
		pw.println("<body>");
			pw.println("<h1>친구 목록 입니다.</h1>");
			pw.println("<table border='1'>");
				pw.println("<tr>");
				pw.println("<th>번호</th>");
				pw.println("<th>이름</th>");
				pw.println("<th>주소</th>");
				pw.println("</tr>");
		list.forEach((tmp)->{
			pw.println("<td>"+tmp.getNum()+"</td>");
			pw.println("<td>"+tmp.getName()+"</td>");
			pw.println("<td>"+tmp.getAddr()+"</td>");
		});
			
			
			pw.println("</table>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
		
		
	 }
}
