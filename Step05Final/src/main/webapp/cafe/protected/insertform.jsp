<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 입력해야되는 폼 제목, 작성자(id), 글  --%>    

<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/protected/insertform.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>


	<div class="container">
	<form action="insert.jsp" method="post">
	<h2>글쓰기</h1>
	<input type="hidden"  />
	<div class="mb-2">
		<label class="form-label" for="title">제목</label>
		<input type="text" name="title" id="title" placeholder="제목을 입력해주세요" />
	</div>
	<div class="mb-2">
		<label for="content">내용</label>
		<textarea name="content" id="content" cols="30" rows="10"></textarea>
	</div>
	<button type="submit">저장</button>

	</form>
		
	
	</div>
</body>
</html>