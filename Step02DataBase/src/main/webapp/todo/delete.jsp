<%@page import="test.todo.dao.TodoDao"%>
<%@page import="test.todo.dto.TodoDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   int num = Integer.parseInt(request.getParameter("num"));
   
   boolean isSuccess=TodoDao.getInstance().delete(num);
   
   if (isSuccess){
      String cPath= request.getContextPath();
      /*
         jsp 기본 객체인 httpservletResponse 객체의 sendredirect 메소드를 이용해서 응답하기 
         이 응답은 메소드를 호출하면서 전달한 경로로 웹브라우저에게 다시 요청하라는 응답이다.
         이런 응답을 받은 웹브라우저는 해당 경로로 요청을 다시하게 된다.
         
      */
      response.sendRedirect(cPath+"/todo/list.jsp");
   }
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>todo/delete.jsp</title>
</head>
<body>
   <script>
   alert("삭제 실패");
   location.href="${pageContext.request.contextPath }/todo/list.jsp"
   </script>
   
</body>
</html>