<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/member/updateform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원 수정폼</h1>
		<form action="${pageContext.request.contextPath}/member/update" method="post">
			
			<input type="hidden" name="num" value="${dto.num }"/>
			<div>
				<label for="name">이름</label>
				<input type="text" name="name" value="${dto.name}"/>
			</div>
			<div>
				<label for="addr">주소</label>
				<input type="text" name="addr" value="${dto.addr}"/>
			</div>
			<button type="submit">수정</button>
		</form>
	</div>
</body>
</html>