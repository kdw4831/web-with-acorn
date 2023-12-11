<%@page import="java.net.URLEncoder"%>
<%@page import="test.user.dao.UserDao"%>
<%@page import="test.user.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 폼전송되는 id, pwd 를 읽어와서
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	//2. id 에 해당하는 회원정보를 UserDao 객체를 이용해서 얻어와서
	UserDto dto=UserDao.getInstance().getData(id);
	//3. 실제로 존재하는 아이디 이고 존재한다면 비밀번호도 일치하는지 비교해서 
	boolean isLoginSuccess=false;
	if(dto != null ){
		if(dto.getPwd().equals(pwd)){
			//로그인 처리해주기
			session.setAttribute("id", id);
			isLoginSuccess=true;
		}
	}
	
	//로그인후 가야할 목적지 정보
	String url=request.getParameter("url");
	//로그인 실패를 대비해서 목적지 정보를 인코딩한 결과도 준비한다.
	String encodedUrl=URLEncoder.encode(url);
	
	
	//체크박스를 체크한 상태로 로그인 버튼을 누르면 null 이 아니다. (체크하지 않으면 null)
		String isSave=request.getParameter("isSave");
		if(isSave != null){
			//아이디 비밀번호를 쿠키로 응답하고 1주일 동안 유지되도록 한다.
			Cookie cook1=new Cookie("savedId", id);
			Cookie cook2=new Cookie("savedPwd", pwd);
			cook1.setMaxAge(60*60*24*7);
			cook2.setMaxAge(60*60*24*7);
			response.addCookie(cook1);
			response.addCookie(cook2);
		}else{
			//특정 키값으로 저장된 쿠키값 삭제하기 (value 에는 아무 값이나 넣어도 상관없다)
			Cookie cook1=new Cookie("savedId", "");
			Cookie cook2=new Cookie("savedPwd", null);
			cook1.setMaxAge(0);
			cook2.setMaxAge(0);
			response.addCookie(cook1);
			response.addCookie(cook2);
		}	
	
	//4. 일치하면 로그인 처리, 아니면 아이디 혹은 비밀번호가 틀려요 라고 응답한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login.jsp</title>
</head>
<body>
	<div class="container">
		<%if(isLoginSuccess){ %>
			<p>
				<strong><%=dto.getId() %></strong> 님 로그인 되었습니다.
				<a href="<%=url %>">확인</a>
			</p>
		<%}else{ %>
			<p>
				아이디 혹은 비밀 번호가 틀려요 
				<a href="${pageContext.request.contextPath}/user/loginform.jsp?url=<%=encodedUrl %>">다시 로그인</a>
			</p>
		<%} %>
	</div>
</body>
</html>