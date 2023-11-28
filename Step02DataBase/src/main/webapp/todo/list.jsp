<%@page import="test.todo.dto.TodoDto"%>
<%@page import="java.util.List"%>
<%@page import="test.todo.dao.TodoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// todo_list를 만들자
// 번호, 할일, 확인여부 아니면 
// all done ongoing

List<TodoDto> list= TodoDao.getInstance().getList();
System.out.println(list.size());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/todo/list.jsp</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>   
<style>
    .checked {
        text-decoration: list-style: none;;
    }
</style>

</head>
<body>

	<jsp:include page="/include/navbar.jsp">
		<jsp:param value="todo" name="current"/>
	</jsp:include>

   <div class="container">
      <h1 class="justfy-content-center">오늘의 할일</h1>
      <form  class="row" action="insert.jsp" method="post">
         
         
         <div class="col-md-6">
            <input  class="form-control" type="text" id="todo" name="todo" />
         </div>
         
         <div class="col-md-4">
            <button type = "submit" class="btn btn-primary">추가</button>
         </div>
      
      </form>
      
      
      <table class="table table-striped">
         
         
            <thead>
               <th>번호</th>
               <th>할일</th>
               <th>확인</th>
               <th>삭제</th>
            </thead>
         
            <tbody>
            <%for(TodoDto tmp: list){%>
               <tr>
                  <td><%=tmp.getNum() %></td>
                  <td><%=tmp.getTodo() %></td>
                  
                  <td> 
	               	 <input type="checkbox" name="done" value="true"/>
                  </td>
                  
                  <td>
                     <form action="delete.jsp" method="post">
                        <input type="hidden" name="num" value="<%=tmp.getNum()%>" />
                        <button type="submit" class="btn btn-danger">삭제</button>
                     </form>
                  </td>
               </tr>
            <% }%>
                     
            </tbody>
      
      </table>
      <form action="done_delete.jsp" method="post">
      <% %>
      	<button type="submit">done 삭제</button>
      </form>
      
   </div>   
   

  
  <jsp:include page="/include/footer.jsp"></jsp:include>
  <script>
    //각 체크박스에 이벤트 리스너 추가
    document.querySelectorAll('input[name="done"]').forEach(function(checkbox) {
        checkbox.addEventListener('change', ()=> {
            var todoText = this.parentElement.nextElementSibling; // 할일 텍스트 엘리먼트
            if (this.checked) {
                todoText.classList.add('checked'); // 체크되면 클래스 추가
            } else {
                todoText.classList.remove('checked'); // 체크 해제되면 클래스 제거
            }
        });
    });
</script>
</body>
</html>