<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// java coding 공간
	
	//어떤 로직을 처리하고 결과를 얻어낼 수 있다.
	
	//필요하다면 DB에 접속을 해서 DB 관련 작업을 하고 
	List<String> notice =new ArrayList<>();
	notice.add("하나");
	notice.add("두울");
	notice.add("세엣");
	//클라이언트에게 응답도 바로 할 수 있다.

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지 입니다.</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath}/home ">home</a></li>
			<li><a href="${pageContext.request.contextPath}/home2 ">home2</a></li>
		</ul>
		<h3>공지사항</h3>
		
		<ul>
		<%for(String tmp :notice){%>
			<li><%=tmp %></li>
		<%} %>
		</ul>
	</div>
</body>
</html>