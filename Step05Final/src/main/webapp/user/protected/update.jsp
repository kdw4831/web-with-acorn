<%@page import="test.user.dto.UserDto"%>
<%@page import="test.user.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//폼 전송되는 수정할 회원의 정보를 읽어온다.
	String email=request.getParameter("email");
	//프로필 이미지를 한번도 등록한 적이 없으면 "null"이 넘어온다.
	String profile=request.getParameter("profile");
	if(profile.equals("null")){
		//DB의 profile 칼럼을 null로 유지하기 위해null을 넣어준다.
		profile=null;
	}
	
	//수정할 회원의 PK(아이디)
	String id=(String)session.getAttribute("id");
	//수정할 회원의 정보를 UserDto에 담고 
	UserDto dto= new UserDto();
	dto.setId(id);
	dto.setEmail(email);
	dto.setProfile(profile);
	//DB에 수정반영하고
	boolean isSuccess=UserDao.getInstance().update(dto);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/protected/update.jsp</title>
</head>
<body>
	<script>
		<%if(isSuccess){%>
			alert("수정 했습니다.");
			location.href="info.jsp";
		<%}else{%>
			alert("수정실패");
			location.href="updateform.jsp";
		<%}%>
	</script>

</body>
</html>