<%@page import="test.cafe2.dao.CafeDao2"%>
<%@page import="test.cafe2.dto.CafeDto2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	CafeDto2 dto= new CafeDto2();
	int num= Integer.parseInt(request.getParameter("num"));
	dto.setNum(num);
	
	String title=request.getParameter("title");
	dto.setTitle(title);
	String content=request.getParameter("content");
	dto.setContent(content);
	
	boolean isSuccess=CafeDao2.getInstance().update(dto);	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/protected/update.jsp</title>
</head>
<body>
	<div class="container pt-5">
		<%if(isSuccess){ %>
	         <p class="alert alert-success">
	             수정 했습니다.
	            <a class="alert-link" href="${pageContext.request.contextPath }/cafe2/detail.jsp?num=<%=dto.getNum()%>">확인</a>
	         </p>
      	<%}else{ %>
	         <p class="alert alert-danger">
	            수정 실패했습니다.
	            <a class="alert-link" href="updateform.jsp?num=<%=dto.getNum() %>">다시 수정하러 가기</a>
	         </p>
      	<%} %>
	</div>
</body>
</html>