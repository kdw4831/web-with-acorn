<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num= Integer.parseInt(request.getParameter("num"));
	CafeDto dto=CafeDao.getInstance().getdata(num);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/protected/updateform</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
 <style>
 
 </style>
</head>
<body>

	<div class="container">
		<form action="update.jsp" method="post">
			<h2>글수정</h1>
			<input type="hidden" name="num" value="<%=dto.getNum() %>" />
			<div class="mb-2">
				<label class="form-label" for="title">제목</label>
				<input class="form-control" type="text" name="title" id="title" value=<%=dto.getTitle() %> />
			</div>
			<div class="mb-2">
				<label class="form-label" for="content">내용</label>
				<textarea class="form-control"  name="content" id="content" cols="30" rows="10"><%=dto.getContent() %></textarea>
			</div>
			<button class="btn float-right btn-dark" type="submit">수정완료</button>
	
		</form>
	</div>
</body>
</html>