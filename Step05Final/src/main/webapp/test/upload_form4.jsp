<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/upload_form4.jsp</title>
<style>
	#image{
		display:none;
	}
	#profileImage{
		width:200px;
		height:200px;
		border:1px solid #cecece;
		border-radius:50%;
	}
	#image{
		display:none;
	}
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
</head>
<body>
	<div class="container">
		<h3>이미지 단독 업로드 테스트</h3>
		<a href="javascript:" id="profileLink">
			<svg class="animate__animated animate__jello animate__infinite" xmlns="http://www.w3.org/2000/svg" width="200" height="200" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
				  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
				  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
			</svg>
		</a>
		<br />
		<!-- 화면에 보이지 않는 이미지 선택할 input type="file" -->
		<input type="file" id="image" accept="image/*" />
	</div>
	<script>
		document.querySelector("#profileLink").addEventListener("click",()=>{
			//input type="file"요소를 강제 클릭해서 파일 선택 창을 만든다.
			document.querySelector("#image").click();
		})
		//새로운 이미지가 선택되었을 때
		document.querySelector("#image").addEventListener("change",(e)=>{
			//전송할 file 데이터를 FormData 객체에 담는다.
			const fileData=e.target.files[0];
			const data=new FormData();
			//myImage라는 파라미터명으로 fileData를 담는다.
			data.append("myImage",fileData);
			//fetch()함수를 이용해서 페이지 전환없이 업로드
			fetch("upload3.jsp",{
				method:"post",
				body:data
			})
			.then(res=>res.json())
			.then(data=>{
				console.log(data);
				//img 요소를 만들 문자열 구성
				let img=`<img id="profileImage" src="${pageContext.request.contextPath}/upload/\${data.saveFileName}">`;
				//링크안에 출력하면서 html 형식으로 해석되도록 한다.
				document.querySelector("#profileLink").innerHTML=img;
			});
		})
	</script>
</body>
</html>