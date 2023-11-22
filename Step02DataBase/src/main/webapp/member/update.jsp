<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	
	MemberDao dao= MemberDao.getInstance();
	dao.update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/update</title>
</head>
<body>
	<div class="container">
		<h1>알림</h1>
		<%if(isSuccess){ %>
			<p>
				<strong><%=num %></strong>번 회원의 정보를 수정 했습니다.
				<a href="list2.jsp">목록보기</a>
			</p>
		<%}else{ %>
			<p>
				수정 실패!
				<a href="list2.jsp">확인</a>
			</p>
		<%} %>
	</div>
</body>
</html>