<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/insertform.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>좋은 글을 남겨 주세요</h1>
		<form action="insert.jsp"  method="post">
			<div class="mb-3">
				<label class="form-label" for="writer">작성자</label>
				<input class="form-control" type="text" id="writer" name="writer" />
			</div>
			<div>
				<label for="content">내용</label>
				<textarea class="form-control" 	 name="content" id="content" cols="30" rows="10"></textarea>
			</div>
			
			<div class="mb-3 col-auto">
				<label for="pwd">비밀번호</label>
				<input class="form-control" type="password" id="pwd" name="pwd"/>
			</div>
			<div class="col-auto">
				<button class="btn btn-primary mb-3 align-right" type="submit">등록</button>
			</div>
							
		</form>
	</div>
</body>
</html>
