<%@page import="test.user.dto.UserDto"%>
<%@page import="test.user.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

    
<%
	//1. 폼전송되는 id ,pwd를 읽어와서
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
	//2. id에 해당하는 회원정보를 UserDao 객체를 이용해서 얻어와서
		UserDto dto=UserDao.getInstance().getData(id);
	//3. 실제로 존재하는 아이디 이고 존재한다면 비밀번호도 일치하는지 비교해서 
			
		boolean isLoginSuccess=false;
		if(dto != null){
			if(dto.getPwd().equals(pwd)){
				//로그인 처리 해주기
				session.setAttribute("id", id);
				isLoginSuccess=true;
			}
		}
	
	/* 내가 한거
		if(!id.equals(dto.getId())){
			return;
		}else if(id.equals(dto.getId())){
			if(!pwd.equals(dto.getPwd())){
				return;
			}
		}
	*/
	//4. 일치하면 로그인 처리, 아니면 ,아이디 혹은 비밀번호가 틀려요라고 응답한다.

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login.jsp</title>
</head>
<body>
	<div class="container">
		<%if(isLoginSuccess){%>
			<p>
				<strong><%=dto.getId() %></strong>님 로그인 되셨습니다.
				<a href="${pageContext.request.contextPath}/">확인</a>
			</p>
		<%}else{ %>
			<p>
				아이디 혹은 비밀번호가 틀려요
				<a href="${pageContext.request.contextPath}/user/loginform.jsp"></a>
			</p>
		<%} %>
	</div>
</body>
</html>