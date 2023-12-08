<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int num= Integer.parseInt(request.getParameter("num"));
	CafeDto dto = CafeDao.getInstance().getdata(num);
	String id= (String)session.getAttribute("id");
	int pageNum=Integer.parseInt(request.getParameter("pageNum"));
	
	
	//ViewCount올리기
	dto.setViewCount(dto.getViewCount()+1);
	boolean isSuccess=CafeDao.getInstance().update(dto);
	
	System.out.println(isSuccess);
	
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/detail.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<style>
	ul{
		    list-style-type: none;
	}
	.title{
	 font-size:2rem;
	 font-style:bold;
	}
	.button{
		display:flex;
		justify-content:right;
	}
	button{
		margin:5px;
		
	}
	
</style>
</head>
<body>	
	<div class="container">	
	
		<ul>
			<li><p class="title">제목:<%=dto.getTitle() %></p></li>
			<li><p class="wrtier">작성자:<%=dto.getWriter() %></p></li>
			<li><textarea  class="form-control" name="content" id="" cols="30" rows="10" readonly><%=dto.getContent() %></textarea></li>
			
		</ul>
		<a href="${pageContext.request.contextPath}/cafe/list.jsp?pageNum=<%=pageNum %>"><button class="btn btn-primary">돌아가기</button></a>
		<div class="button">
			<%if(dto.getWriter().equals(id)){%>
				<a href="${pageContext.request.contextPath}/cafe/protected/updateform.jsp?num=<%=num%>"><button class="btn btn-dark">수정</button></a>
				<a href="protected/delete.jsp?num=<%=num%>"><button class="btn btn-danger">삭제</button></a>
			<%}else{%>
				<a href="protected/delete.jsp?num=<%=num%>"><button class="btn btn-danger">삭제</button></a>
			<%} %>
		</div>
		
	</div>
	

</body>
</html>