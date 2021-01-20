<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
	input.form-control{
		width: 40%;
		margin: 0 auto;
	}
</style>
</head>
<body>
<%@ include file="../components/header.jsp" %>
<br/>
	<div class="container" style="text-align: center;" > 
	<div class="jumbotron">
		<h1>회원 가입 페이지</h1>
	<form action="/lectureEvaluation/user?cmd=join" method="post">
		<div class="form-group">
			<label for="userId">아이디</label> 
			<input type="text"
				class="form-control " placeholder="아이디를 입력하세요" id="userId" name="userId"/>
		</div>
		<div class="form-group">
			<label for="userName">이름</label> 
			<input type="text"
				class="form-control " placeholder="이름을 입력하세요" id="userName" name="userName">
		</div>
		<div class="form-group">
			<label for="userPassword">비밀번호</label> 
			<input type="password"
				class="form-control " placeholder="비밀번호를 입력하세요" id="userPassword" name="userPassword">
		</div>
		<div class="form-group">
			<label for="userEmail">이메일</label> 
			<input type="email"
				class="form-control " placeholder="이메일을 입력하세요" id="userEmail" name="userEmail">
		</div>
		<input class="btn-primary" type="submit" value="회원가입" />
	</form>
	</div>
	</div>
</body>
</html>