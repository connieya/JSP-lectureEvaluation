<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="../components/header.jsp" %>
<div class="container">
 <h1>자유게시판</h1>
 ${sessionScope.principal}
	<div class="row">
           
           <c:choose>
           	<c:when test="${sessionScope.principal != null }">
           	  <a class="btn btn-info" href="/lectureEvaluation/board?cmd=boardForm">글쓰기</a>
           	</c:when>
           	<c:otherwise>
           	  <a class="btn btn-info" onclick="NotRegister()">글쓰기</a>
           	</c:otherwise>
           </c:choose>
          
  <table class="table table-bordered" style="text-align: center; border: 1px solid #ddddddd">
  
    <thead>
      <tr>
        <th style="background-color: #eeeeee;">글번호</th>
        <th style="background-color: #eeeeee;">작성자</th>
        <th style="background-color: #eeeeee;">제목</th>
        <th style="background-color: #eeeeee;">조회수</th>
        <th style="background-color: #eeeeee;">작성일</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td>gunny</td>
        <td>안녕</td>
        <td>0</td>
        <td>2021-01-25</td>
      </tr>
     
    </tbody>
  </table>
  </div>
</div>

<script>
	function NotRegister(){
		alert("로그인 후 사용가능합니다.")
		
		location.href = "/lectureEvaluation/user/login.jsp"
	}
</script>
</body>
</html>