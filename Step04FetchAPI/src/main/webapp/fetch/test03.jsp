<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/test03.jsp</title>
</head>
<body>
	<button id="getBtn">회원정보 가져오기</button>
	<p>번호: <strong id="num"></strong></p>
	<p>이름: <strong id="name"></strong></p>
	<p>주소: <strong id="addr"></strong></p>
	
	<button id="getBtn2">친구 목록 가져오기</button>
	<ul id="friend">
		
	</ul>
	
	<script>
		/*
			친구 목록 가져오기 버튼을 눌럿을 때 get_friend.jsp 페이지로 fetch() 요청을 하고
			응답되는 데이터를 이용해서 친구 이름을 ul에 li요소를 이용해서 목록을 출력해보세요
		*/
		
		document.querySelector("#getBtn2").addEventListener("click",()=>{
			fetch("get_friend.jsp")
			.then(res=>res.json())
			.then((data)=>{
				console.log(data[0])
				//data는 ["김구라","해골",...] 형식의 배열이다. \${pageContext.request.contextPath}
				data.forEach((item)=>{
					// item은 문자열이다.(친구 이름)
					//const li=document.createElement("li");
					//li.innerText=item;
					//위 두개를 이용해서 하려면
					// append함수로 더해줘야한다. documet....).append(li)
					
					//const li= "<li>"+item+"</li>"
					const li =`<li>\${item}</li>`;
					// li 요소를 만들어 낼 수 있는 문자열을 전달해서 실제 li요소를 만들어서 원하는 곳에 끼워넣기
					document.querySelector("#friend").insertAdjacentHTML("beforeend",li);
				})
			
			})
		})
	

		/*
			회원 정보 가져오기 버튼을 눌렀을 때 get_member.jsp 페이지로 fetch() 요청을 하고
			응답되는 데이터를 이용해서 위에 회원의 번호, 이름, 주소를 출력해 보세요
		*/
		document.querySelector("#getBtn").addEventListener("click",()=>{
			fetch("get_member.jsp")
			.then(res=>	res.json())
			.then((data)=>{
				console.log(data)
				//data는 object 임으로 . 찍어서 바로 데이터를 참조할 수 있다.
				document.querySelector("#num").innerText=data.num;
				document.querySelector("#name").innerText=data.name;
				document.querySelector("#addr").innerText=data.addr;
			})
		})
	</script>
</body>
</html>