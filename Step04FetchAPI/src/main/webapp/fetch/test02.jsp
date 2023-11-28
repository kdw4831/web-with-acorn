<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/test02.jsp</title>
</head>
<body>
	<input type="text" id="msg" placeholer="메세지 입력..." />
	<button id="sendBtn">전송</button>
	<button id="sendBtn2">전송2</button>
	<button id="sendBtn3">전송3</button>
	<script>
		//문자열을 입력하고 전송 버튼을 눌렀을 때 입력한 문자열을 읽어와서 send.jsp 페이지로 전송이 되게 하기
		document.querySelector("#sendBtn").addEventListener("click",()=>{
			//입력한 문자열 읽어오기
			const msg=document.querySelector("#msg").value;
			//fetch 함수를 호출하면서 get 방식 파라미터로 send.jsp 페이지를 요청하면서 전달한다.
			fetch("send.jsp?msg="+msg)
			.then(res=>res.text()) //리턴예약어를 생략하고 람다식으로 작성
			.then((data)=>{
				console.log(data)
			});
		});
		
		document.querySelector("#sendBtn2").addEventListener("click",()=>{
			//입력한 문자열 읽어오기
			const msg=document.querySelector("#msg").value;
			//fetch 함수를 호출하면서 get 방식 파라미터로 send.jsp 페이지를 요청하면서 전달한다.
			fetch("send2.jsp?msg="+msg)
			.then(res=>res.json()) //res.json으로 리턴하면 parsing 작업을 알아서 해준다.
			.then((data)=>{
				console.log(data)
			});
		})
		
		document.querySelector("#sendBtn3").addEventListener("click",()=>{
			//입력한 문자열 읽어오기
			const msg=document.querySelector("#msg").value;
			//fetch 함수를 호출하면서 get 방식 파라미터로 send.jsp 페이지를 요청하면서 전달한다.
			fetch("send3.jsp?msg="+msg)
			.then(res=>res.json()) //res.json으로 리턴하면 parsing 작업을 알아서 해준다.
			.then((data)=>{
				console.log(data)
			})
			.catch(errMsg=>{
				//에러인 경우 여기서 처리할 수 있다.
				console.log(errMsg);
			});
		});
		
		
		
	</script>
	
</body>
</html>