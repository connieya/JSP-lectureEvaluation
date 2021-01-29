<%@page import="java.io.PrintWriter"%>
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
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('세션이 만료 되었습니다. 다시 로그인해주세요')");
		script.println("location.href='/lectureEvaluation/userServlet?cmd=loginForm'");
		script.println("</script>");
	}
	
	
%>

<br/>

	<div class="container" style="text-align: center;" >
	<div class="jumbotron">
		<h1>회원 수정</h1>
	<form action="/lectureEvaluation/userServlet?cmd=userUpdateAction" method="post">
		<input type="hidden" name="sessionValue" value=<%=pr %>  />
		<div class="form-group">
			<label for="userId">아이디</label> 
			<input type="text"
				class="form-control " placeholder="아이디를 입력하세요" id="userId" name="userId" value="${users.userId }" readonly/>
		</div>
		<div class="form-group">
			<label for="userName">이름</label> 
			<input type="text"
				class="form-control " placeholder="이름을 입력하세요" id="userName" name="userName" value="${users.userName }">
		</div>
		<div class="form-group">
			<label for="userPassword">비밀번호</label> 
			<input type="password"
				class="form-control " placeholder="비밀번호를 입력하세요" id="userPassword" name="userPassword" value="${users.userPassword }"  maxlength="2048">
		</div>
		<div class="form-group">
			<label for="userAddr">주소</label> 
			<input type="text"
				class="form-control" id="userAddr" name="userAddr" value="${users.userAddr}"  />
		</div>
			<button type="button" class="btn-success" onclick="goPopup();">주소검색</button>
		<div class="form-group">
			<label for="userEmail">이메일</label> 
			<input type="email"
				class="form-control " placeholder="이메일을 입력하세요" id="userEmail" name="userEmail" value="${users.userEmail }" readonly>
		</div>
		
		<input class="btn btn-primary" type="submit" value="회원수정" />
<%-- 		<a href="/lectureEvaluation/user/userDeleteAction.jsp?userId=<%=userId %>" class="btn btn-danger" onclick="return confirm('정말 탈퇴 하시겠습니까?')">회원탈퇴</a> --%>
		<a href="/lectureEvaluation/userServlet?cmd=userDelete&userId=<%=pr %>" class="btn btn-danger" onclick="return confirm('정말 탈퇴 하시겠습니까?')">회원탈퇴</a>
		<a href="/lectureEvaluation/lectureServlet?cmd=emailSend" class="btn btn-warning" style="color:white;" >이메일 인증</a>
	</form>
	</div>
	</div>
	<script>
	function goPopup(){
		
		var pop = window.open("/lectureEvaluation/userServlet?cmd=jusoPop","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 

	}


	function jusoCallBack(roadFullAddr){
		
			var addrEI =document.querySelector("#userAddr")
			//옛날 방식
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			//document.form.roadFullAddr.value = roadFullAddr;
			addrEI.value =  roadFullAddr
				
	}
	</script>
</body>
</html>