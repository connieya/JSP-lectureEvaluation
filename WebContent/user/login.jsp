<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file="../components/header.jsp" %>

<!-- 	x-www-form-urlencoded  -->
	<section class="container mt-5" style="max-width: 560px;">
 	<form action="/lectureEvaluation/user?cmd=login" method="post">  
	<!-- <form action="./loginAction.jsp" method="post"> -->
		<div class="form-group">
			<label for="">아이디</label>
			<input type="text" name="userId" class="form-control" />
		</div>
			<div class="form-group">
			<label for="">비밀번호</label>
			<input type="password" name="userPassword" class="form-control" />
		</div>
		<button type="submit" class="btn btn-primary">로그인</button>
		
	</form>

	</section>


<footer class="bg-dark mt-4 p-5 text-center" style="color:#ffffff;">
		CopyRight &copy; 2020 박건희 All Rights Reserved.
	</footer>
</body>
</html>