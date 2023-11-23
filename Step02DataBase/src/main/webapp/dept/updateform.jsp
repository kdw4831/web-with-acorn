
<%@page import="test.dept.dto.DeptDto"%>
<%@page import="test.dept.dao.DeptDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
  	//GET 방식 파라미터로 전달되는 수정할 회원의 번호를 읽어온다.
  	int deptno= Integer.parseInt(request.getParameter("deptno"));
  	
  	//DB에서 해당 회원의 정보를 읽어온다.(MemberDto)
  	DeptDao dao= DeptDao.getInstance();
  	DeptDto dto= dao.getData(deptno);
  	
  	String name=dto.getDname();
  	String addr=dto.getLoc();
  	
  	//아래 form 요소의 기본 value 값으로 출력한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/updateform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원 정보 수정 양식</h1>
		<form action="update.jsp" method="post">
			<div>
				<label for="deptno">번호</label>
				<input type="text" id="deptno" name="deptno" value="<%=deptno%>" readonly />
			</div>
			
			<div>
				<label for="dname">이름</label>
				<input type="text" id="dname" name="dname" value="<%=dto.getDname()%>" />
			</div>
			
			<div>
				<label for="loc">주소</label>
				<input type="text" id="loc" name="loc" value="<%=dto.getLoc()%>" />
			</div>
			
			<button type="submit">수정확인</button>	
			<button type="reset">취소</button>
		</form>
	</div>
</body>
</html>