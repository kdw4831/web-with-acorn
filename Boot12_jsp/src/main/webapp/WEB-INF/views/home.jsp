<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/WEB-INF/view/home.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지 입니다.</h1>
		<ul><a href="${pageContext.request.contextPath }/member/list">회원 목록보기</a></ul>
		<h3>공지사항</h3>
		<ul>
			<c:forEach var="tmp" items="${requestScope.notice} ">
				<li>${tmp }</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>