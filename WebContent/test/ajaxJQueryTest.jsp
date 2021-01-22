<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<button onclick="isCheck()">아이디 중복체크</button>
	<div id="demo">	</div>
	
	<script>
		function isCheck(){
			$.ajax("http://localhost:8090/lectureEvaluation/ajax").done(function(data){
				alert(data);
			});
			
		}
		
	/* 	통신 완료 jQuery로 ajax 통신 끝  */
	</script>
</body>
</html>