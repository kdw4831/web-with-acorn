<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	//session scope에 id라는 키값으로 저장된 값이 있는지 읽어와 본다.
	//null이면 로그인을 하지 않은 상태, null이 아니면 로그인된 아이디가 리턴된다.
	String id=(String)session.getAttribute("id");
%>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지 입니다.</h1>
		<%if(id!=null){ %>
			<p>
				<strong><%=id %></strong>님 로그인중...
				<a href="${pageContext.request.contextPath}/user/logout.jsp">로그아웃</a>
			</p>
		<%}else{ %>
			<a href="${pageContext.request.contextPath}/user/loginform.jsp">로그인</a>
		<%} %>
		
		<ul>
			<li><a href="${pageContext.request.contextPath}/user/signup_form.jsp">회원가입</a></li>
		</ul>
	</div>
</body>
</html>