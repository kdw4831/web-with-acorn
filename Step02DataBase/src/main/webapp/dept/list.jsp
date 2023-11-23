<%@page import="test.dept.dto.DeptDto"%>
<%@page import="java.util.List"%>
<%@page import="test.dept.dao.DeptDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	//DeptDao 객체 참조값을 static 메소드를 이용해서 얻어온다.
	DeptDao dao= DeptDao.getInstance();
	//아래의 table에 출력학 부서목록 얻어오기12
	List<DeptDto> list = dao.getList();
	
	 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/dept/list.jsp</title>
</head>
<body>
	<div class="container">
			<h1>부서 목록입니다</h1>
			<a href="${pageContext.request.contextPath}/dept/insertform.jsp">회원 추가</a>
			<table>
				<thead>
					<tr>
						<th>부서번호</th>
						<th>부서이름</th>
						<th>부서위치</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody>
				<%for(DeptDto tmp:list){ %>
					<tr>
						<td><%=tmp.getDeptno() %></td>
						<td><%=tmp.getDname() %></td>
						<td><%=tmp.getLoc() %></td>
						<td>
							<a href="updateform.jsp?num=<%=tmp.getDeptno()%>">수정</a>
						</td>
						<td>
							<a href="delete.jsp?num=<%=tmp.getDeptno()%>">삭제</a>
						</td>
					</tr>
				<%} %>
				</tbody>
			</table>
		</div>
	</body>
</html>