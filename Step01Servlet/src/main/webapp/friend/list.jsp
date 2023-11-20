<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//여기가 자바 코딩영역
	//DB에서 불러온 데이터라고 가정하자
	
	List<String> names=new ArrayList<>();
	names.add("김구라");
	names.add("해골");
	names.add("원숭이");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>친구 목록 입니다.</h1>
	<ul>
		<%for(String tmp:names){%>
			<li><%out.print(tmp); %></li>
		<%} %>
	</ul>
	

	<ul>
		<% for(String tmp:names) {%>
			<li><%=tmp %></li>
		<%} %>
	</ul>
</body>
</html>