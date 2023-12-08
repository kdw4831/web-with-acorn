<%@page import="test.cafe2.dto.CafeDto2"%>
<%@page import="test.cafe2.dao.CafeDao2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. GET 방식 파라미터로 전달되는 삭제할 파일의 글번호 읽어오기
	int num=Integer.parseInt(request.getParameter("num"));
	
	CafeDto2 dto=CafeDao2.getInstance().getData(num);

	//로그인된 아이디와 글의 작성자가 일치하는지 확인해서 일치하지 않으면 에러 페이지를 응답한다 
	String id=(String)session.getAttribute("id");
	if(!dto.getWriter().equals(id)){
		//에러페이지를 응답하도록 한다. 
		response.sendError(HttpServletResponse.SC_FORBIDDEN , "남의 파일 지우면 혼난다!");
		return; //메소드를 여기서 끝내기 (service() 메소드)
	}

	//2. DB 에서 삭제하기
	CafeDao2.getInstance().deleteRef(num);
	boolean isSuccess=CafeDao2.getInstance().delete(num);
	//3. 응답하기 
	//응답
	String cPath=request.getContextPath();
	response.sendRedirect(cPath+"/cafe2/list.jsp");	
%>    