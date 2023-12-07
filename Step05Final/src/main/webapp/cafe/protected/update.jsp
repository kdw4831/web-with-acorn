<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num= Integer.parseInt(request.getParameter("num"));
	String title= request.getParameter("title");
	String content=request.getParameter("content");
	CafeDto dto=new CafeDto();
	dto.setNum(num);
	dto.setContent(content);
	dto.setTitle(title);
	
	boolean isSuccess=CafeDao.getInstance().update(dto);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/protected/update.jsp</title>
</head>
<body>
	<script>
		<%if(isSuccess){ %>
			alert("글 수정을 성공하였습니다.");
			location.href="${pageContext.request.contextPath}/cafe/list.jsp";
		<%}else{ %>
			alert("글 수정을 실패하였습니다.");
			location.href="${pageContext.request.contextPath}/cafe/detail.jsp";
		<%} %>
	</script>
</body>
</html>