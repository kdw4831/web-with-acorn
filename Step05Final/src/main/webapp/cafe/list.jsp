<%@page import="test.cafe.dto.CafeDto"%>
<%@page import="java.util.List"%>
<%@page import="test.cafe.dao.CafeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<CafeDto> list=CafeDao.getInstance().getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>Cafe 게시판</h1>
		<a href="${pageContext.request.contextPath}/cafe/protected/insertform.jsp">글쓰기</a>
		<table class="class table table">
		 	<thead>
		 		<th>번호</th>
		 		<th>작성자</th>
		 		<th>제목</th>
		 		<th>내용</th>
		 		<th>추천수</th>
		 		<th>작성일</th>
		 	</thead>
		 	<tbody>
		 	<%for(CafeDto tmp:list){ %>
		 		<tr>
		 			<td><%=tmp.getNum() %></td>
		 			<td><%=tmp.getWriter() %></td>
		 			<td><%=tmp.getTitle() %></td>
		 			<td><%=tmp.getContent() %></td>
		 			<td><%=tmp.getViewCount() %></td>
		 			<td><%=tmp.getRegdate() %></td>
		 		</tr>
		 	<%}%>
		 	</tbody>
		</table>
	</div>

</body>
</html>