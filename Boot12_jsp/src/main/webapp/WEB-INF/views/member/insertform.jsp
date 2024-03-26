<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/vies/member/insertform.jsp</title>
</head>
<body>
	<div class="container">
	<h1>회원 추가 폼</h1>
	<form action="insert" method="post">
		<input type="text" name="name" placeholder="이름..." />
		<input type="text" name="addr" placeholder="주소..." />
		<button type="submit">추가</button>
	</form>
	</div>
</body>
</html>