<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	//request scope에 "list"라는 키값으로 담긴 List<String> type 데이터 얻어오기
	List<String> list= (List<String>)request.getAttribute("list"); 
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test/friend.jsp</title>
</head>
<body>
	<ul>
		<%for(String tmp :list){%>
			<li><%=tmp %></li>
		<%} %> 
	</ul>
</body>
</html>