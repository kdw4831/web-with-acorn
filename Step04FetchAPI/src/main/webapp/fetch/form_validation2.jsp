
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/form_validation2.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>폼 유효성 검증 style 테스트</h1>
		<form action="signup.jsp" method="post">
			<div>
				<label class="form-label" for="nick">닉네임</label>
				<input class="form-control" type="text" name="nick" id="nick"/>
				<div class="invalid-feedback">이미 존재하는 닉네임 입니다!</div>
			</div>
			<div>
				<label for="pwd">비밀번호</label>
				<input type="password" class="form-control " name="pwd" id="pwd"/>
				<div class="invalid-feedback">비밀 번호를 확인하세요</div>
			</div>
			<div>
				<label for="pwd2">비밀번호 확인</label>
				<input type="password" class="form-control" id="pwd2"/>
			</div>
			<button class="btn btn-primary"  type="submit"  >가입</button>
		</form>
	</div>
	<script>
		//닉네임 유효성 여부를 관리할 변수
		let isNickValid=false;
		//비밀번호 유효성 여부를 관리할 변수 
		let isPwdValid=false;
		
		/*
			1.닉네임을 입력했을 때 유효성 여부를 변수에 저장한다.
			2.비밀번호를 입력했을 때 유효성 여부를 변수에 저장한다.
			3.두 변수에 있는 값이 모두 true인 경우 일때만 가입 버튼의 disabled 속성을 제거하고
			나머지 경우에는 disabled 속성을 추가한다.
			4.적절한 시점에 그 함수를 호출하게 하면 된다.
			
			- disabled 속성 추가 하는 방법
			버튼의 참조값.setAttribute("disabled","")
			- disabled 속성 제거 하는 방법
			버튼의 참조값.removeAttribute("disabled")
		*/
		
		
		
		
		if(isNickValid==true && isPwdValid==true){
			document.querySelector("[type=submit]").removeAttribute("disabled");
			
		}else{
			document.querySelector("[type=submit]").setAttribute("disabled","");
		
		}
		/*
		const checkForm=()=>{
			//만일 닉네임도 유효하고 비밀번호도 유효하다면
			if(isNickValid && isPwdValid){
				//전송 버튼에 disabled 속성을 제거하고
				document.querySelector("[type=submit]").removeAttribute("disabled");
			}else{
				//전송 버튼에 disabled 속성을 추가한다.
				document.querySelector("[type=submit]").setAttribute("disabled","");
			}
		};
		*/
	
		//닉네임을 입력했을때 실행할 함수 등록
		document.querySelector("#nick").addEventListener("input", ()=>{
			//현재까지 입력한 닉네임을 읽어온다.
			let inputNick=document.querySelector("#nick").value;
			//fetch() 함수를 이용해서 get 방식으로 입력한 닉네임을 보내고 사용가능 여부를 json 으로 응답받는다.
			fetch("${pageContext.request.contextPath}/fetch/check_nick.jsp?nick="+inputNick)
			.then(res=>res.json())
			.then(data=>{
				//일단 클래스를 제거한 후에 
				document.querySelector("#nick").classList.remove("is-valid");
				document.querySelector("#nick").classList.remove("is-invalid");
				//data 는 {canUse:true} or {canUse:false} 형태의 object 이다.
				if(data.canUse){
					document.querySelector("#nick").classList.add("is-valid");
					//사용할 수 있는 닉네임이라는 의미에서 true를 넣어준다.
					//isNickValid=true;
				}else{
					document.querySelector("#nick").classList.add("is-invalid");
					//사용할 수 없는 닉네임이라는 의미에서 false를 넣어준다.
					//isNickValid=false;
				}
				//checkForm();
				isNickValid=document.querySelector("#nick").classList.contains("is-valid");
			});
			
		});
		const checkPwd=()=>{
			//양쪽에 입력한 비밀번호를 읽어와서
			let pwd=document.querySelector("#pwd").value;
			let pwd2=document.querySelector("#pwd2").value;
			//양쪽을 같게 입력하면 is-valid 를 추가하고 그렇지 않으면 is-invalid 를 추가한다.
			document.querySelector("#pwd").classList.remove("is-valid");
			document.querySelector("#pwd").classList.remove("is-invalid");
			if(pwd == pwd2){
				document.querySelector("#pwd").classList.add("is-valid");
				//비밀번호가 유효하다는 의미에서 true를 넣어준다.
				//isPwdValid=true;
			}else{
				document.querySelector("#pwd").classList.add("is-invalid");
				//비밀번호가 유효하지 않다는 의미에서 false를 넣어준다.
				//isPwdValid=false;
			}
			//checkForm();
			isPwdValid=document.querySelector("#pwd").classList.contains("is-valid")
		}
		
		document.querySelector("#pwd").addEventListener("input", checkPwd);
		document.querySelector("#pwd2").addEventListener("input", checkPwd);
		
		
	</script>
</body>
</html>