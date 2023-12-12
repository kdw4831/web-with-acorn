<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%
	// test를 위해 sample data를 request 영역에 담기
	request.setAttribute("jumsu",66);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test.jstl/test04.jsp</title>
</head>
<body>
	<c:if test="true">
		<p>오잉 true네</p>
	</c:if>
	<c:if test="${10%2 eq 0 }">
		<p>10은 짝수입니다.</p>
	</c:if>
	
	<c:if test="${11%2 eq 0 }">
		<p>11은 짝수입니다.</p>
	</c:if>
	<c:if test="${requestScope.jumsu%2 eq 0 }">
		<p>${jumsu }는 짝수 입니다.</p>
	</c:if>
	
	<p>획득한 점수는 <strong>${jumsu }</strong>입니다</p>
	
	<p>
		따라서 이번 학기 학점은
		<c:choose > <%-- 스위치 문처럼 쓸 수 있다.--%>
			<c:when test="${jumsu ge 90 }">A</c:when>
			<c:when test="${jumsu ge 80 }">B</c:when>
			<c:when test="${jumsu ge 70}">C</c:when>
			<c:when test="${jumsu ge 60}">D</c:when>
			<c:otherwise>F</c:otherwise>
		</c:choose>
		입니다.
	</p>
	
	<p>
		<strong>${jumsu }</strong>는
		<c:choose>
			<c:when test="${jumsu%2 eq 0}">
			 	짝수
			</c:when>
			<c:otherwise>
				홀수
			</c:otherwise>
		</c:choose>
	</p>
	
	<% String id=(String)session.getAttribute("id"); %>
	
	<%if(id==null){ %>
		<p>로그인이 필요합니다.</p>
	<%}else{%>
		<p><strong><%=id %></strong>님 로그인 중..</p>
	<%} %>
	
	
	<c:choose><%--id==null=>eq=> empty id --%>
		<c:when test="${id eq null }">
			<p>로그인이 필요합니다.</p>
		</c:when>
		<c:otherwise> <%--여기서는 sessionScope.을 생략한 경우이다. --%>
				<p><strong>${id}</strong>님 로그인 중..</p>
		</c:otherwise>
	</c:choose>
	
</body>
</html>