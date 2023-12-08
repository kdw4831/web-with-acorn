<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/protected/pwd_updateform.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
	<div class="container" id="app">
		<h1>비밀 번호 수정 페이지</h1>
		<form action="pwd_update.jsp" method="post" id="myForm">
			<div class="mb-2">
				<label class="form-label" for="pwd">기존 비밀번호</label>
				<input class="form-control" @input="onPwdInput" v-bind:class="{'is-invalid': !isPwdValid, 'is-valid':isPwdValid}" type="password" name="pwd" id="pwd"/>
				<div class="invalid-feedback">반드시 입력하세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="newPwd">새 비밀번호</label>
				<input class="form-control" type="password" name="newPwd" id="newPwd"/>
				<small class="form-text">반드시 입력하고 아래의 확인란과 동일해야 합니다.</small>
				<div class="invalid-feedback">새 비밀번호를 확인하세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="newPwd2">새 비밀번호 확인</label>
				<input class="form-control" type="password" id="newPwd2"/>
			</div>
			<button class="btn btn-success" type="submit" v-bind:disabled="!isPwdValid">수정하기</button>
			<button class="btn btn-danger" type="reset">리셋</button>		
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script>
		new Vue({
			el:"#app",
			data:{
				isPwdValid:false
			},
			methods:{
				onPwdInput(e){
					//현재까지 입력한 비밀번호
					const pwd=e.target.value;
					//공백이 아닌 한글자가 한번이상 반복 되어야 통과 되는 정규표현식
					const reg_pwd=/[\S]+/;
					if(reg_pwd.test(pwd)){
						this.isPwdValid=true;
					}else{
						this.isPwdValid=false;
					}
				}
			}
		});
	</script>
</body>
</html>






