<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /fetch/check_nick.jsp --%>
<%
	//GET 방식 요청 파라미터로 전달되는 닉네임 읽어오기
	String nick=request.getParameter("nick");
	
	//DB 에 존재하는지 확인해서 사용가능한 닉네임인지 여부를 json 문자열로 응답해야 한다.
	boolean canUse=true;
	if(nick.equals("kimgura")){
		canUse=false;
	}
%>
{"canUse":<%=canUse %>}