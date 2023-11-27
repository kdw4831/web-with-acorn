<%@page import="test.todo.dao.TodoDao"%>
<%@page import="test.todo.dto.TodoDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String done=request.getParameter("done");
   int num= Integer.parseInt(request.getParameter("num"));
   
   TodoDto dto= new TodoDto();
   dto.setNum(num);
   dto.setDone(done);
   
   TodoDao.getInstance().done_update(dto);
   
   
   boolean isSuccess= TodoDao.getInstance().done_delete(dto);
   
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
   <%if(isSuccess){ %>
      location.href="${pageContext.request.contextPath }/todo/list.jsp";
   <%}else{%>
      alert("done_삭제실패")
      location.href="${pageContext.request.contextPath }/todo/list.jsp"
   <%} %>
</script>
   
   

</body>
</html>