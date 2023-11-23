<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//한글이 깨지지 않도록
	request.setCharacterEncoding("utf-8");

	//Post 방식 파라미터로 전달되는 작성자, 내용, 비밀번호를 읽어와서  GuestDto에 담은다음
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	//DB에 저장하고
	GuestDto dto=new GuestDto();
	dto.setWriter(writer);
	dto.setContent(content);
	dto.setPwd(pwd);
	boolean isSuccess=GuestDao.getInstance().insert(dto);
	//응답한다.
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/insert.jsp</title>
</head>
<body>

	<script>
		<%if(isSuccess){%>
			//알림창 띄우기
			alert("글을 성공적으로 등록 했습니다.");
			//javascript 로 페이지 이동
			location.href="${pageContext.request.contextPath }/guest/list.jsp";
		<%}else{%>
			alert("등록 실패!");
			location.href="${pageContext.request.contextPath }/guest/insertform.jsp";
		<%}%>
	</script>

</body>
</html>