<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// GET 방식 파라미터로 전달되는 삭제할 회원의 번호를 읽어온다. ?num=삭제할 회원의 번호
	int num=Integer.parseInt(request.getParameter("num"));
	//회원 한명의 정보를 삭제하고 (삭제할 회원의 번호는?)
	MemberDao dao= MemberDao.getInstance();
	boolean isSuccess=dao.delete(num);

	//응답
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/delete.jsp</title>
</head>
<body>
	<div class="container">
		<h1>알림</h1>
		<%if(isSuccess){ %>
			<p>
				<strong><%=num %></strong>번 회원의 정보를 삭제 했습니다.
				<a href="list2.jsp">목록보기</a>
			</p>
		<%}else{ %>
			<p>
				삭제 실패!
				<a href="list2.jsp">확인</a>
			</p>
		<%} %>
	</div>
</body>
</html>