<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//post 방식 전송했을 때 한글이 깨지지 않도록
	request.setCharacterEncoding("utf-8");
	//1. 폼 전송되는 수정할 회원의 정보를 읽어온다.
	int num=Integer.parseInt(request.getParameter("num"));
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	String pwd= request.getParameter("pwd");
	GuestDto dto =new GuestDto();
	dto.setNum(num);
	dto.setWriter(writer);
	dto.setContent(content);
	dto.setPwd(pwd);
	//2. DB 에 수정 반영한다
	boolean isSuccess=GuestDao.getInstance().update(dto);
	
	
	
	//3. 응답하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update.jsp</title>
</head>
<body>
	<h1>알림</h1>
	<%if(isSuccess){ %>
		<p>
			<strong><%=writer %></strong> 님이 작성한 글이 수정되었습니다.
			<a href="list.jsp">목록보기</a>
		</p>
	<%}else{ %>
		<p>
			수정 실패!
			<a href="updateform.jsp?num=<%=num%>">다시 작성</a>
		</p>
	<%} %>
</body>
</html>