<%@page import="test.util.DbcpBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//아래의 table 에 출력할 회원목록 얻어오기
	List<MemberDto> list=new ArrayList<>();

	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try{
		//DbcpBean() 객체를 이용해서 Connection 객체 하나 얻어내기 (Connection Pool 에서 하나 꺼내오기)
		conn=new DbcpBean().getConn();
		//실행할 sql 문
		String sql="SELECT num, name, addr"+
		 	" FROM member"+
			" ORDER BY num ASC";
		pstmt=conn.prepareStatement(sql);
		//query 문 수행하고 결과(ResultSet) 얻어내기
		rs=pstmt.executeQuery();
		//반복문 돌면서 
		while(rs.next()){
			//MemberDto 객체에 회원 한명, 한명의 정보를 담아서
			MemberDto dto=new MemberDto();
			dto.setNum(rs.getInt("num"));
			dto.setName(rs.getString("name"));
			dto.setAddr(rs.getString("addr"));
			//ArrayList 객체에 누적 시킨다.
			list.add(dto);
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close(); //Connection 객체의 close() 메소드를 호출하면 Pool 에 반납된다.
		}catch(Exception e){}
	}
%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/list.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원 목록입니다</h1>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>주소</th>
				</tr>
			</thead>
			<tbody>
			<%for(MemberDto tmp:list){ %>
				<tr>
					<td><%=tmp.getNum() %></td>
					<td><%=tmp.getName() %></td>
					<td><%=tmp.getAddr() %></td>
				</tr>
			<%} %>
			</tbody>
		</table>
	</div>
</body>
</html>
