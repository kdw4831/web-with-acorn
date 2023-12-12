<%@page import="test.cafe.dto.CafeDto"%>
<%@page import="java.util.List"%>
<%@page import="test.cafe.dao.CafeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=5;


	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=10;
	
	//보여줄 페이지의 번호를 일단 1이라고 지정
	int pageNum=1;
	
	//페이지 번호가 파라미터로 전달되는지 읽어와 본다.
	String strPageNum=request.getParameter("pageNum");
	//만일 페이지 번호가 파라미터로 넘어온다면
	if(strPageNum!=null){
		//숫자로 바꿔서 보여줄 페이지 번호를 지정한다.
		pageNum=Integer.parseInt(strPageNum);
	}
	//보여줄 페이지의 시작 ROWNUM
	int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT; //등차수열 페이지 번호에 따라 5씩증가
 	//보여줄 페이지의 끝 ROWNUM
 	int endRowNum=pageNum*PAGE_ROW_COUNT;
	
	//하단 시작 페이지 번호 (1일 때 1,  2일 때 11, 3일 때 21, ....이것도 등차수열인데?)
	int startPageNum=1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
	//하단 끝 페이지 번호 (pageNum에 따라서 10, 20 ,30 요런식으로 가겠네)
	int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
	//전체 글의 갯수
	int totalRow=CafeDao.getInstance().getCount();
	//전체 페이지의 갯수 구하기
	int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
	//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산 되었다면 잘못된 값이다.
	// endPageNum은10페이지씩 하단에서 보여주는데 총페이지가 13개야 그러면 20페이지까지 보여줄 필요가 없지
	//그래서 마지막페이지를 끝페이지를 대입해줘야하는게 맞지
	if(endPageNum >totalPageCount){
		endPageNum=totalPageCount;
	}
	List<CafeDto> list=CafeDao.getInstance().getList(startRowNum,endRowNum);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<%--여기서는 컨텍스트 경로를 사용할 필요가 없다.--%>
	<jsp:include page="/include/navbar.jsp">
		<jsp:param value="cafe" name="current"/>
	</jsp:include>
	<div class="container">
		<h1>Cafe 게시판</h1>
		<a href="${pageContext.request.contextPath}/cafe/protected/insertform.jsp"><button class="btn btn-dark btn-sm mb-2" align="right">글쓰기</button></a>
		<table class="table  table-striped">
			<colgroup>
			    <col style="width:10%;">
				<col style="width:20%;">
				<col style="width:20%;">
				<col style="width:20%;">
				<col style="width:20%;">    
			</colgroup>
		 	<thead class=table-dark>
		 		<th>번호</th>
		 		<th>작성자</th>
		 		<th>제목</th>
		 		<th>조회수</th>
		 		<th>작성일</th>
		 	</thead>
		 	<tbody>
		 	<%for(CafeDto tmp:list){ %>
		 		<tr>
		 			<td><%=tmp.getNum() %></td>
		 			<td><%=tmp.getWriter() %></td>
		 			<td><a href="${pageContext.request.contextPath}/cafe/detail.jsp?num=<%=tmp.getNum() %>&pageNum=<%=pageNum%>"><%=tmp.getTitle() %></a></td>
		 			<td><%=tmp.getViewCount() %></td>
		 			<td><%=tmp.getRegdate() %></td>
		 		</tr>
		 	<%}%>
		 	</tbody>
		</table>
			<%--페이징 UI 부분 --%>
			<nav  class="mt-5" aria-label="Page navigation example">
			  <ul class="pagination justify-content-center ">
			  	<%if(startPageNum!=1){ %>
			  		<li class="page-item">
				      <a class="page-link" href="list.jsp?pageNum=<%=startPageNum-1 %>" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
			  	<%} %>
			  	<%for(int i=startPageNum; i<=endPageNum; i++){ %>
				  	<% if(i==pageNum){%>
				  		<li class="page-item active">
		  			 		<a class="page-link" href="list.jsp?pageNum=<%=i%>"><%=i%></a>
		  				 </li>
				  	<%}else{ %>
					  	<li class="page-item ">
			  			 	<a class="page-link" href="list.jsp?pageNum=<%=i%>"><%=i%></a>
			  			 </li>
				  	<%} %>			
			  	<%} %>
			  	
			  	<%if(endPageNum<totalPageCount){ %>
				    <li class="page-item">
				      <a class="page-link" href="list.jsp?pageNum=<%=endPageNum+1 %>" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
			  	<%} %>
			   
			  </ul>
			</nav>
	</div>
<jsp:include page="/include/footer.jsp"></jsp:include>

</body>
</html>