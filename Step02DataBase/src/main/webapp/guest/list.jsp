<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//글목록 얻어오기 
	List<GuestDto> list=GuestDao.getInstance().getList();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/list.jsp</title>
</head>
<body>
	<div class="container">
		<h1>방명록 목록 입니다</h1>
		<a href="insertform.jsp">글작성</a>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>내용</th>
					<th>등록일</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
			<%for(GuestDto tmp:list){ %>
				<tr>
					<td><%=tmp.getNum() %></td>
					<td><%=tmp.getWriter() %></td>
					<td>
						<textarea rows="5" readonly><%=tmp.getContent() %></textarea>
					</td>
					<td><%=tmp.getRegdate() %></td>
					<td>
						<form action="delete.jsp" method="post">
							<input type="hidden" name="num" value="<%=tmp.getNum() %>" />
							<input type="password" name="pwd" placeholder="비밀번호..." />
							<button type="submit">삭제</button>
						</form>
					</td>
				</tr>
			<%} %>
			</tbody>
		</table>
	</div>
</body>
</html>
