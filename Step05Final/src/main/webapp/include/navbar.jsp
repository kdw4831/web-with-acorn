<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//navbar.jsp 페이지가 어떤 페이지에 include 되었는지 정보 읽어오기
	String currentPage=request.getParameter("current"); // "index" or "member" or "guest" 
	
	String id= (String)session.getAttribute("id");
%>
<nav class="navbar bg-primary navbar-expand-md" data-bs-theme="dark">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath }/index.jsp">
      <img src="https://getbootstrap.com/docs/5.3/assets/brand/bootstrap-logo.svg" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
      Acorn
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
    		data-bs-target="#navbarText">
     		<span class="navbar-toggler-icon"></span>
   	</button>
    <div class="collapse navbar-collapse" id="navbarText">
	   	<ul class="navbar-nav me-auto">
        	<li class="nav-item">
          		<a class="nav-link <%=currentPage.equals("cafe") ? "active" : "" %>" href="${pageContext.request.contextPath }/cafe/list.jsp">게시판</a>
        	</li>
        	<li class="nav-item">
          		<a class="nav-link <%=currentPage.equals("file") ? "active" : "" %>" href="${pageContext.request.contextPath }/file/list.jsp">자료실</a>
        	</li>
        
      	</ul>
      	<form class="d-flex">
	        <input class="form-control me-2" type="search" placeholder="Search" >
	        <button class="btn btn-info" type="submit">Search</button>
      	</form>
      	<span class="navbar-text ms-2">
      		<%if(id!=null){ %>
			<p>	
				<a href="${pageContext.request.contextPath}/user/protected/info.jsp"><%=id %></a>님 로그인중...
				<a href="${pageContext.request.contextPath}/user/logout.jsp">로그아웃</a>
			</p>
			<%}else{ %>
				<a href="${pageContext.request.contextPath}/user/loginform.jsp">로그인</a>
			<%} %>
      	</span>
    </div>
  </div>
</nav>