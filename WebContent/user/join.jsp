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
	#check{
	
		display: grid ;
		
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../components/header.jsp" %>
<br/>
	<div class="container" style="text-align: center;" > 
	<div class="jumbotron">
		<h1>회원 가입 페이지</h1>
	<form action="/lectureEvaluation/user?cmd=join" method="post" onsubmit="return valid()">
		<div class="form-group" id="check">
			<div>
			<label for="userId">아이디</label> 
			<input type="text"																	
				class="form-control " maxlength="13"  placeholder="아이디를 입력하세요" id="userId" name="userId" required/>
			</div>
			<div>
			<button class="btn-info"  type="button" onclick="idCheck()" >아이디 중복검사</button>
			</div>			
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
	var isChecking = false;
	
	function valid(){
		
		if(isChecking == false){
			alert("아이디 중복체크를 해주세요")
		}
		
		return isChecking;
		
			
	}
	
	function idCheck(){
		
		
		var userId = $("#userId").val();
		console.log("userId: ",userId)
		if(userId == ""){
			alert("아이디를 입력하세요")
		}else{
			$.ajax({
				type : "post",
				url : "/lectureEvaluation/user?cmd=idCheck",
				data : userId,
				contentType :"/text/plain; charset=utf-8",
				dataType : "text"  // 응답 받을 데이터의 타입을 적으면 자바스크립트 오브젝트로 파싱해줌 
			}).done(function(data){
				if(data ==="ok"){
					isChecking = false;
					alert("이미 사용중인 아이디 입니다.")
				}else{
					isChecking = true;
					alert('사용 가능한 아이디입니다.')
				}
			});
			
		}
		
		
	}
		/* 
		get 방식으로 하면
		/lectureEvaluation/user?cmd=idCheck&userId=gunny6026  */
	
function goPopup(){
	
	var pop = window.open("/lectureEvaluation/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 

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