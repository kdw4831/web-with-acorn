<%@page import="test.cafe2.dao.CafeDao2"%>
<%@page import="test.cafe2.dto.CafeDto2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//폼전송되는 title, content 읽어내기
	String title=request.getParameter("title");
	String content=request.getParameter("content");
	//글작성자 얻어내기
	String writer=(String)session.getAttribute("id");
	//CafeDto 에 담기
	CafeDto2 dto=new CafeDto2();
	dto.setWriter(writer);
	dto.setTitle(title);
	dto.setContent(content);
	//DB 에 저장하기
	boolean isSuccess=CafeDao2.getInstance().insert(dto);
	//응답하기
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/protected/insert.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<div class="card w-50 mt-5">
			<%if(isSuccess){ %>
				<div class="card-body">
					<h5 class="card-title">알림</h5>
					<p class="card-text"><%=writer %>  님이 작성한 새글을 저장했습니다</p>
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/cafe2/list.jsp">확인</a>
				</div>
			<%}else{ %>
				<div class="card-body">
					<h5 class="card-title">알림</h5>
					<p class="card-text text-danger">글 저장 실패!</p>
					<a class="btn btn-warning" href="${pageContext.request.contextPath}/cafe2/protected/insertform.jsp">다시 작성하기</a>
				</div>
			<%} %>
		</div>
	</div>
</body>
</html>



















