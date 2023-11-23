
<%@page import="test.dept.dao.DeptDao"%>
<%@page import="test.dept.dto.DeptDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//한글 깨지지 않도록
	request.setCharacterEncoding("utf-8");
	
	//1. 폼 전송되는 수정할 회원의 정보를 얻어온다.
	int deptno=Integer.parseInt(request.getParameter("deptno"));
	String dname=request.getParameter("dname");
	String loc=request.getParameter("loc");
	//2. DB 에 수정 반영한다.
	DeptDto dto=new DeptDto(deptno, dname, loc);
	boolean isSuccess=DeptDao.getInstance().update(dto);
	//3. 응답한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/dept/update.jsp</title>
</head>
<body>
	<%if(isSuccess){ %>
		<script>
			//알림창을 띄우고 
			alert("수정 했습니다.");
			//location 객체를 이용해서 회원 목록보기로 리다일렉트 시키기
			location.href="${pageContext.request.contextPath}/dept/list.jsp";
		</script>
	<%}else{ %>
		<h1>알림</h1>
		<p>
			수정 실패! 
			<a href="updateform.jsp?deptno=<%=deptno %>">다시 수정하러 가기</a>
		</p>
	<%} %>
</body>
</html>