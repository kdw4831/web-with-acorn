
<%@page import="test.file.dto.FileDto"%>
<%@page import="java.util.List"%>
<%@page import="test.file.dao.FileDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	// 한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=5;
	
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=10;

	//보여줄 페이지의 번호를 일단 1이라고 초기값 설정
	int pageNum=1;
	//페이지 번호가 파라미터로 전달되는지 읽어와 본다.
	String strPageNum=request.getParameter("pageNum");
	//만일 페이지 번호가 파라미터로 넘어온다면
	if(strPageNum!=null){
		//숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
		pageNum=Integer.parseInt(strPageNum);
	}
	
	//보여줄 페이지의 시작 ROWNUM
	int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;  //등차 수열의 일반항
	//보여줄 페이지의 끝 ROWNUM
	int endRowNum=pageNum*PAGE_ROW_COUNT;
	
	
	
	//하단 시작 페이지 번호 
	int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
	//하단 끝 페이지 번호
	int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
	//전체 글의 갯수
	int totalRow=FileDao.getInstance().getCount();
	//전체 페이지의 갯수 구하기
	int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
	//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
	if(endPageNum > totalPageCount){
		endPageNum=totalPageCount; //보정해 준다. 
	}
	
	//파일 전체 목록 얻어오기
	//List<FileDto> list=FileDao.getInstance().getList();
	
	//보여줄 페이지에 맞는 목록만 가져오기
	List<FileDto> list=FileDao.getInstance().getList(startRowNum,endRowNum);
	
	// 로그인 된 사용자 읽어오기(로그인 되지 않았다면 null이다.)
	String id=(String)session.getAttribute("id");
	
	//EL+JSTL을 테스트하기 위해서 응답에 필요한 데이터 (model)을 request 영역에 담기
	//"list"라는 키값으로 파일 목록(List<FileDto>) 담기
	request.setAttribute("list", list);
	
	//"startPageNum"이라는 키값으로 int type 담기
	request.setAttribute("startPageNum",startPageNum);
	request.setAttribute("endPageNum",endPageNum);
	request.setAttribute("totalPageCount",totalPageCount);

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 목록입니다.</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<style>
	/* ul 의 기본 스타일 제거 */
	.page-list{
		margin: 0;
		padding: 0;
		list-style-type: none;
	}
	
	.page-list li{
		float: left; /* li 가 필요한 만큼의 폭만 차지하면서 가로로 배치 되도록 */
		padding: 5px;
	}
	
	.page-list li:hover{
		background-color: #cecece;
	}
	
	.page-list li a{
		color: #000;
		text-decoration: none;
	}
	
	.page-list li.active a{
		color: red;
		text-decoration: underline;
		font-weight: bold;
	}
	
</style>
</head>
<body>
	<jsp:include page="/include/navbar.jsp">
		<jsp:param value="file" name="current"/>
	</jsp:include>
	<div class="container">
		
		<a href="${pageContext.request.contextPath}/file/protected/upload_form.jsp">업로드 하러 가기</a>
		<h1>자료실 목록입니다.</h1>
		<table class="table table">
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>파일명</th>
					<th>크기</th>
					<th>등록일</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tmp" items="${requestScope.list }">
					<tr>
						<td>${tmp.num }</td>
						<td>${tmp.writer }</td>
						<td>${tmp.title }</td>
						<td><a href="${pageContext.request.contextPath}/file/download?num=${tmp.num }">${tmp.orgFileName }</a></td>
						<td>${tmp.fileSize }</td>
						<td>${tmp.regdate }</td>
						<td>
							<c:if test="${tmp.writer eq sessionScope.id }">
								<a href="${pageContext.request.contextPath}/file/protected/delete.jsp?num=${tmp.num}">삭제</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<%--페이징 UI --%>
		<ul class="page-list">
		<%--
			startPageNum이 1이 아닌 경우에만 Prev링크를 제공한다. 
		--%>
		
		<c:if test="${startPageNum ne 1}">
			<li>
				<a href="list.jsp?pageNum=${startPageNum-1 }">Prev</a>
			</li>	
		</c:if>
		
		
		<c:forEach var="i" begin="${startPageNum }" end="${endPageNum}">
			<c:choose>
				<c:when test="${i eq param.pageNum }">
					<li class="active">
						<a href="list.jsp?pageNum=${i }">${i }</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="list.jsp?pageNum=${i }">${i }</a>
					</li>
				</c:otherwise>
			
			</c:choose>
		</c:forEach>
		
		
		<%--
			마지막 페이지 번호가 전체 페이지의 갯수보다 작으면 Next 링크를 제공한다.
		 --%>
		
		 
		 <c:if test="${endPageNum lt totalPageCount }">
		 	<li>
		 		<a href="list.jsp?pageNum=${endPageNum+1}">NEXT</a>
		 	</li>
		 </c:if>
		 
	</ul>
	
	</div>
	<br/>
	<jsp:include page="/include/footer.jsp"></jsp:include>
	
</body>
</html>