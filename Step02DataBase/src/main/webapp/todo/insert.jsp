<%@page import="test.todo.dao.TodoDao"%>
<%@page import="test.todo.dto.TodoDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
   request.setCharacterEncoding("utf-8");
   
   // post 방식 파라미터로 전달되는 todo를 읽어와서 todoDto에 담은다음
   String todo= request.getParameter("todo");
   TodoDto dto = new TodoDto();
   dto.setTodo(todo);
   
   boolean isSuccess=TodoDao.getInstance().insert(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/Todo/insert.jsp</title>
</head>
<body>
   <script>
      <%if(isSuccess){%>
      location.href="${pageContext.request.contextPath }/todo/list.jsp";
      <%}else{%>
         alert("등록 실패")
         location.href="${pageContext.request.contextPath }/todo/list.jsp"
      <%}%>
      
   </script>

</body>
</html>