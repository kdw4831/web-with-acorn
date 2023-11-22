<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지 입니다.</h1>
		<p>컨텍스트 경로<strong>${pageContext.request.contextPath}</strong></p>
		<ul>
			<li> <a href="${pageContext.request.contextPath}/member/list"> 회원목록Servlet</li>
			<li> <a href="${pageContext.request.contextPath}/friend/list.jsp"> 친구목록jsp</li>
			<li> <a href="${pageContext.request.contextPath}/member/list.jsp"> 회원목록jsp</li>
		</ul>
	</div>
</body>
</html>