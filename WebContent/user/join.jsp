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
				class="form-control " maxlength="13"  placeholder="아이디를 입력하세요" id="userId" name="userId" required/>
		</div>
		<div class="form-group">
			<label for="userName">이름</label> 
			<input type="text"
				class="form-control" maxlength="6" placeholder="이름을 입력하세요" id="userName" name="userName" required>
		</div>
		<div class="form-group">
			<label for="userPassword">비밀번호</label> 
			<input type="password"
				class="form-control" maxlength="12"  placeholder="비밀번호를 입력하세요" id="userPassword" name="userPassword" required>
		</div>
		<div class="form-group">
			<label for="userEmail">이메일</label> 
			<input type="email"
				class="form-control " placeholder="이메일을 입력하세요" id="userEmail" name="userEmail" required>
		</div>
		
		 <div class="form-group">
			<label for="userAddr">주소</label> 
			<input type="text"
				class="form-control " placeholder="주소를 입력하세요" id="userAddr" name="userAddr" required>
		</div>
		<input class="btn-primary" type="submit" value="회원가입" />
		<button type="button" class="btn-success" onclick="goPopup();">주소검색</button>
	</form>
	</div>
	</div>
	<script>
	
	
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/lectureEvaluation/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr){
	
		var addrEI =document.querySelector("#userAddr")
		//옛나 방식
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		//document.form.roadFullAddr.value = roadFullAddr;
		addrEI.value =  roadFullAddr
		
		
}

</script>
	
	
</body>
</html>