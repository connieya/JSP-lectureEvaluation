<%@page import="utill.SHA256"%>
<%@page import="user.User"%>
<%@page import="user.UserDAO"%>
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
<% 
	String pr = null;
	if(session.getAttribute("principal") != null){
		pr = (String) session.getAttribute("principal");
	}
	
	UserDAO dao = new UserDAO();


	User user = new User();
	user = dao.getUser(pr);
	//String code = null;
	// code = user.getUserEmailHash();
	
	//String userId  =null;
	 String userId =user.getUserId();
%>
<br/>
	<div class="container" style="text-align: center;" >
	<div class="jumbotron">
		<h1>회원 수정</h1>
	<form action="userUpdateAction.jsp?id=<%=pr %>" method="post">
		<div class="form-group">
			<label for="userId">아이디</label> 
			<input type="text"
				class="form-control " placeholder="아이디를 입력하세요" id="userId" name="userId" value=<%=user.getUserId() %> readonly/>
		</div>
		<div class="form-group">
			<label for="userName">이름</label> 
			<input type="text"
				class="form-control " placeholder="이름을 입력하세요" id="userName" name="userName" value=<%=user.getUserName() %>>
		</div>
		<div class="form-group">
			<label for="userPassword">비밀번호</label> 
			<input type="password"
				class="form-control " placeholder="비밀번호를 입력하세요" id="userPassword" name="userPassword" value=<%=user.getUserPassword() %>>
		</div>
		<div class="form-group">
			<label for="userEmail">이메일</label> 
			<input type="email"
				class="form-control " placeholder="이메일을 입력하세요" id="userEmail" name="userEmail" value=<%=user.getUserEmail() %> readonly>
		</div>
		<input class="btn btn-primary" type="submit" value="회원수정" />
		<a href="/lectureEvaluation/user/userDeleteAction.jsp?userId=<%=userId %>" class="btn btn-danger" onclick="return confirm('정말 탈퇴 하시겠습니까?')">회원탈퇴</a>
		<a href="/lectureEvaluation/emailSendAction.jsp?" class="btn btn-warning" style="color:white;" >이메일 인증</a>
	</form>
	</div>
	</div>
</body>
</html>