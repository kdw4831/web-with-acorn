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
		<p>컨텍스트 경로: <strong>${pageContext.request.contextPath}</strong></p>
		<ul>
			<li><a href="/Step01Servlet/friend/list">친구 목록보기</a></li>
			<li><a href="${pageContext.request.contextPath}/member/list">회원 목록보기</a></li>
			<li><a href="${pageContext.request.contextPath}/friend/list.jsp">친구 목록보기(jsp)</a></li>
			<li><a href="${pageContext.request.contextPath}/member/list.jsp">회원 목록보기(jsp)</a></li>
		</ul>
		<form action="/Step01Servlet/send" method="get">
			<input type="text" name="msg" placeholder="서버에 할말 입력" />
			<button type="submit">전송</button>
		</form>
		<br/>
		<form action="/Step01Servlet/send2" method="post">
			<input type="text" name="msg" placeholder="서버에 할말 입력" />
			<button type="submit">전송</button>
		</form>
		<br/>
		<form action="/Step01Servlet/send.jsp" method="post">
			<input type="text" name="msg" placeholder="서버에 할말 입력" />
			<button type="submit">전송</button>
		</form>
		
		
		
	</div>

</body>
</html>