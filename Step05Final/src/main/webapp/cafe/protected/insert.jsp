<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	String writer=(String)session.getAttribute("id");
	String title=request.getParameter("title");
	String content=request.getParameter("content");
	
	CafeDto dto= new CafeDto();
	dto.setWriter(writer);
	dto.setTitle(title);
	dto.setContent(content);
	
	boolean isSuccess=CafeDao.getInstance().insert(dto);
	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/protected/insert.jsp</title>
</head>
<body>
	<script>
		<%if(isSuccess){ %>
			alert("글 등록이 성공하였습니다.");
			location.href="${pageContext.request.contextPath}/cafe/list.jsp";
		<%}else{ %>
			alert("글 등록이 실패하였습니다.");
			location.href="${pageContext.request.contextPath}/cafe/protected/insertform.jsp";
		<%} %>
	</script>
	
</body>
</html>