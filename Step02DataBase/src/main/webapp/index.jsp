<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/index.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지 입니다.</h1>
		<ul>
			<li><a href="test.jsp">Connection 객체를 잘 얻어오는지 확인하기</a></li>
			<li><a href="${pageContext.request.contextPath}/member/list.jsp">회원목록보기</a></li>
			<li><a href="${pageContext.request.contextPath}/member/list2.jsp">회원목록보기2</a></li>
			<li><a href="${pageContext.request.contextPath}/dept/list.jsp">부서 목록 보기</a></li>
			<li><a href="${pageContext.request.contextPath}/guest/list.jsp">방명록 보기</a></li>
		</ul>
	</div>
	
</body>
</html>