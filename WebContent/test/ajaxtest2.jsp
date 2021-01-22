<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="box">
<h2>아이디 중복 검사</h2>
<button type="button" onclick="loadDoc()">중복 검사</button>
</div>

<script>
function loadDoc() {
	
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      if(this.responseText === "ok") {
    	  var box = document.querySelector("#box")
    	  box.innerHTML = "아이디 중복! 다른 아이디 사용하셈"
    	  
      }else {
    	  alert("아이디 사용가능")
      }
    
    }
  };
  xhttp.open("GET", "http://localhost:8090/lectureEvaluation/ajax", true);
  xhttp.send();
}
</script>
</body>
</html>