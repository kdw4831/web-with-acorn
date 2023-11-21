<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	//한글 인코딩
	request.setCharacterEncoding("utf-8");
	//msg라는 파라미터 명으로 전송되는 문자열 추출하기
	String msg= request.getParameter("msg");
	System.out.println("msg :"+msg);
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/title.jsp</title>
</head>
<body>
	<p>메세지 잘 받았어 클라이언트야!</p>
</body>
</html>