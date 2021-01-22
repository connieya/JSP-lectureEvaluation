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
			fetch("http://localhost:8090/lectureEvaluation/ajax").then(function(data){
				return data.text();
			}).then(function(data){
				alert(data);
			});
			
				
		}
		
	/* 	통신 완료 fetch로 ajax 통신 끝  */
	</script>
</body>
</html>