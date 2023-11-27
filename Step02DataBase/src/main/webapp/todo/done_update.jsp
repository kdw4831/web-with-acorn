<%@page import="test.todo.dao.TodoDao"%>
<%@page import="test.todo.dto.TodoDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");
	int num=Integer.parseInt(request.getParameter("num"));
	String done=request.getParameter("done");
	String todo=request.getParameter("todo");
	TodoDto dto =new TodoDto(num, todo, done);
	boolean isSuccess=TodoDao.getInstance().done_update(dto);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
location.href="${pageContext.request.contextPath}/todo/list.jsp";
</script>
</body>
</html>