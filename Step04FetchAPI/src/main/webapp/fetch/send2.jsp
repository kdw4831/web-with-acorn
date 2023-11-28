<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//get 방식 요청 파라미터 읽어오기
	String msg= request.getParameter("msg");
	System.out.println("msg:"+msg);
%>
{"isSuccess":true, "toClient":"메세지 잘받았어 클라이언트야"}