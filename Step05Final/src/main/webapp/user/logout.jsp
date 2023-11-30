<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/logout.jsp</title>
</head>
<body>
	<%
		//세션초기화를 한다.
		session.invalidate();
	%>
	<p>로그아웃 되셨습니다.</p>
	<a href="${pageContext.request.contextPath}">인덱스페이지로 ..</a>
</body>
</html>