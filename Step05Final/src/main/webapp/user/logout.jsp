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
		//2.응답
	%>
	
	<p>로그아웃 되셨습니다.</p>
	<a href="${pageContext.request.contextPath}">인덱스페이지로 ..</a>
	
	<script>
		alert("로그아웃 되셧습니다.")
		//자바스크립트로 페이지 이동하게하기
		location.href("${pageContext.request.contextPath}/");
	</script>
</body>
</html>