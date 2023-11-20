<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="test.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	//DB에서 읽어온 회원 목록이라고 가정하자
	//회원 목록을 담을 ArrayList 객체
	List<MemberDto> list= new ArrayList<MemberDto>();
	list.add(new MemberDto(1,"김구라","노량진"));
	list.add(new MemberDto(2,"해골","행신동"));
	list.add(new MemberDto(3,"원숭이","동물원"));
%>
<html>
<head>	
<meta charset="UTF-8">
<title>회원 목록 보기</title>
</head>
<body>
	<h1>회원 목록 입니다.</h1>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>주소</th>
		</tr>
		<%for(MemberDto tmp:list){ %>
			<tr>
				<td><%=tmp.getNum() %></td>
				<td><%=tmp.getName() %></td>
				<td><%=tmp.getAddr() %></td>
			</tr>
		<%} %>
	</table>

</body>
</html>