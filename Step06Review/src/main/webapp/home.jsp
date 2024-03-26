<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	//응답에 필요한 데이터는 request 영역에 담겨 있다.
	List<String> notice =(List<String>)request.getAttribute("notice");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지 입니다.</h1>
		<h3> 공지사항 </h3>
		<ul>
		<%for(String tmp :notice){%>
			<li><%=tmp %></li>
		<%} %>
		</ul>
	</div>
</body>
</html>