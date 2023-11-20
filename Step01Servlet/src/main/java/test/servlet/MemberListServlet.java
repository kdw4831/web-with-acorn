package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dto.MemberDto;


@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//DB에서 읽어온 회원 목록이라고 가정하자
		//회원 목록을 담을 ArrayList 객체
		List<MemberDto> list= new ArrayList<MemberDto>();
		list.add(new MemberDto(1,"김구라","노량진"));
		list.add(new MemberDto(2,"해골","행신동"));
		list.add(new MemberDto(3,"원숭이","동물원"));
		//회원 목록을 client에게 html 형식으로 응답해 보세요 (table 활용)
		//응답 인코딩 설정
		resp.setCharacterEncoding("utf-8");
		//응답 컨텐트 설정
		resp.setContentType("text/html; charset=utf-8");
		//요청을 한 클라이언트에게 문자열을 출력할수 있는 객체의 참조값 얻어내기
		PrintWriter pw = resp.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title>회원 목록 보기</title>");
		pw.println("</head>");
		pw.println("<body>");
			pw.println("<h1>회원 목록 입니다.</h1>");
			pw.println("<table border='1'>");
				pw.println("<tr>");
				pw.println("<th>번호</th>");
				pw.println("<th>이름</th>");
				pw.println("<th>주소</th>");
				pw.println("</tr>");
				
				for(MemberDto tmp:list) {
					pw.println("<tr>");
						pw.println("<td>"+tmp.getNum() +"</td>");
						pw.println("<td>"+tmp.getName() +"</td>");
						pw.println("<td>"+tmp.getAddr() +"</td>");
					pw.println("</tr>");
				}
				
				list.forEach((item)->{
					pw.println("<tr>");
						pw.println("<td>"+item.getNum() +"</td>");
						pw.println("<td>"+item.getName() +"</td>");
						pw.println("<td>"+item.getAddr() +"</td>");
					pw.println("</tr>");
					
				});
			
			pw.println("</table>");
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	}
}
