<%@page import="test.cafe.dao.CafeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num= Integer.parseInt(request.getParameter("num"));
	
	boolean isSuccess=CafeDao.getInstance().delete(num);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/protected/delete.jsp</title>
</head>
<body>
	<script>
		<%if(isSuccess){ %>
			alert("글 삭제를 성공하였습니다.");
			location.href="${pageContext.request.contextPath}/cafe/list.jsp";
		<%}else{ %>
			alert("글 삭제를 실패하였습니다.");
			location.href="${pageContext.request.contextPath}/cafe/detail.jsp";
		<%} %>
	</script>
</body>
</html>