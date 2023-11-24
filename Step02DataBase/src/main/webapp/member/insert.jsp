<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//post 방식 전송했을 때 한글이 깨지지 않도록
	request.setCharacterEncoding("utf-8");
	//form에 전송되는 회원의 이름과 주소를 읽어와서
	String name=request.getParameter("name");
	String addr=request.getParameter("addr");
	
	//Db에 저장되고
	MemberDto dto= new MemberDto();
	dto.setName(name);
	dto.setAddr(addr);
	
	MemberDao dao= MemberDao.getInstance();
	boolean isSuccess=dao.insert(dto);
	
	//응답한다.
%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/insert.jsp</title>
</head>
<body>
	<div class="container">
		<h1>알림</h1>
		<%if(isSuccess){ %>
			<p> 
				<strong><%=name %></strong> 님의 정보를 추가 했습니다.
				<a href="${pageContext.request.contextPath}/member/list2.jsp">목록보기</a>
			</p>
		<%}else{ %>
			<p>
				회원정보 추가 실패!
				<a href="insertform.jsp">다시 입력</a>
			</p>
		<%} %>
	</div>
</body>
</html>