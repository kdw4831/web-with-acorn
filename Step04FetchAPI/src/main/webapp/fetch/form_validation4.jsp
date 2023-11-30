
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/fetch/form_validation4.jsp</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>폼 유효성 검증 style 테스트</h1>
		<form action="signup.jsp" method="post" class="animate__animated animate__zoomInLeft  ">
			<div>
				<label class="form-label" for="nick">닉네임</label>
				<input class="form-control" type="text" name="nick" id="nick"/>
				<div class=form-text>영문자 대소문자만 가능 합니다.</div>
				<div class="invalid-feedback">사용할 수 없는 닉네임 입니다!</div>
				<div class="valid-feedback">사용 가능한 닉네임 입니다.</div>
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
			<div>
				<label class="form-label" for="comment">하고 싶은 말</label>
				<textarea class="form-control animate__animated "  name="comment" id="comment"  rows="10"></textarea>
				<div class="form-text">100글자 이내로 입력 해주세요</div>
				<div class="form-text">글자 수: <strong id="textCount">0</strong></div>
			</div>
			<button class="btn btn-primary"  type="submit" disabled>가입</button>
		</form>
	</div>
	<script>
		//닉네임 유효성 여부를 관리할 변수
		let isNickValid=false;
		//비밀번호 유효성 여부를 관리할 변수 
		let isPwdValid=false;
		//하고싶은 말 유효성 여부를 관리할 변수
		let isCommentValid=true;
		
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
	
		const checkForm=()=>{
			//만일 닉네임도 유효하고 비밀번호도 유효하고 그리고 하고싶은말도 유효하다면
			if(isNickValid && isPwdValid &&isCommentValid){
				//전송 버튼에 disabled 속성을 제거하고
				document.querySelector("[type=submit]").removeAttribute("disabled");
			}else{
				//전송 버튼에 disabled 속성을 추가한다.
				document.querySelector("[type=submit]").setAttribute("disabled", "");
			}
		};
		//닉네임을 검증할 정규표현식 객체 
		const regNick=/^[a-zA-Z]+$/;
		//닉네임을 입력하고 포커스를 다른곳으로 이동했을 때 검증 수행하기(blur는 focus를 잃었을 때 발생하는 이벤트)
		document.querySelector("#nick").addEventListener("blur", ()=>{
			//현재까지 입력한 닉네임을 읽어온다.
			let inputNick=document.querySelector("#nick").value;			
			// 원래는 긴코드를 if문안에 넣어야하지만 만족하지 않는경우를 아무것도 리턴하지않으면 fetch에 값이 들어가지 않는다.
			if(!regNick.test(inputNick)){ 
				document.querySelector("#nick").classList.add("is-invalid");
				//사용할 수 없는 닉네임이라는 의미에서 false를 넣어준다.
				isNickValid=false;
				checkForm();
				return;// 함수를 여기서 종료해라
			}
			
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
					isNickValid=true;
				}else{
					document.querySelector("#nick").classList.add("is-invalid");
					//사용할 수 없는 닉네임이라는 의미에서 false를 넣어준다.
					isNickValid=false;
				}
				checkForm();
				
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
				isPwdValid=true;
			}else{
				document.querySelector("#pwd").classList.add("is-invalid");
				//비밀번호가 유효하지 않다는 의미에서 false를 넣어준다.
				isPwdValid=false;
			}
			checkForm();
			
		}
		
		document.querySelector("#pwd").addEventListener("input", checkPwd);
		document.querySelector("#pwd2").addEventListener("input", checkPwd);
		
		//Textarea 유효성
		document.querySelector("#comment").addEventListener("input",(e)=>{
			// 이 함수에는 발생한 이벤트에 대한 정보를 가지고 있는 event 객체가 매개변수에 전달된다.
			console.log(e);
			//입력한 문자열 읽어오기(e.target은 이벤트가 발생한 바로 그 요소의 참조값이다.)
			const msg=e.target.value;
			//문자열의 길이
			const length=msg.length;// e.target.value.length 해서 한번에 읽어올 수 있다.
			//만일 100글자 초과로 입력한다면
			if(length>100){
				e.target.classList.add("is-invalid");
				isCommentValid=false;
				//애니메이션 효과를 주기위해
				e.target.classList.add("animate__shakeX");
				//"animationend" 이벤트가 일어 났을 때 "animate__shakeX" 클래스를 제거 해보세요
				
				/*e.target.addEventListener("animationend",()=>{
					e.target.classList.remove("animate__shakeX");
				})*/
				
				//클래스 제거하기
				e.target.addEventListener("animationend",()=>{
					e.target.classList.remove("animate__shakeX");
				},{once:true} );
			}else{
				//document.querySelector("#comment")  == e.target 이거지
				e.target.classList.remove("is-invalid");
				isCommentValid=true;
			}
			
			document.querySelector("#textCount").innerText=length;
			checkForm();			
			
			
			
		});
		
		
		
	</script>
</body>
</html>