<%@page import="test.user.dto.UserDto"%>
<%@page import="test.user.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//session scope 에 저장된 아이디를 이용해서 
	String id=(String)session.getAttribute("id");
	//수정할 회원의 정보를 얻어온다.
	UserDto dto=UserDao.getInstance().getData(id);
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/protected/updateform.jsp</title>
</head>
<body>
	<div class="container">
		<h3>회원 정보 수정 양식</h3>
		<form action="update.jsp" method="post">
			<div>
				<label for="id">아이디</label>
				<input type="text" id="id"  value="<%=dto.getId() %>" readonly/>
			</div>
			<div>
				<label for="email">이메일</label>
				<input type="text" id="email" name="email" value="<%=dto.getEmail()%>"/>
			</div>
			<button type="submit">수정확인</button>
			<button type="reset">취소</button>
		</form>
	</div>
</body>
</html>