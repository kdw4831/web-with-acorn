<%@page import="test.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//request scope에 "dto"라는 키값으로 담긴 MemberDto 객체 얻어오기
	MemberDto dto= (MemberDto)request.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/fortune.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원정보</h1>
		<p>번호: <strong><%=dto.getNum() %></strong></p>
		<p>이름: <strong><%=dto.getName() %></strong></p>
		<p>주소: <strong><%=dto.getAddr() %></strong></p>
		
	</div>
</body>
</html>