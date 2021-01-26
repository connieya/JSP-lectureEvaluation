<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<%@ include file="../components/header.jsp" %>
<div class="container">
 <h1>자유게시판</h1>
 ${sessionScope.principal}
	<div class="row">
           
           <c:choose>
           	<c:when test="${sessionScope.principal != null }">
           	  <a class="btn btn-info" href="/lectureEvaluation/boardServlet?cmd=boardForm">글쓰기</a>
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
   <c:forEach var="board" items="${boards}">
      <tr>
        <td>${board.bno}</td>
        <td>${board.userId }</td>
        <td>${board.title }</td>
        <td>${board.readCount }</td>
        <td>${board.createDate}</td>
      </tr>
    </c:forEach>
     
    </tbody>
  </table>
  </div>
</div>

<script>
	function NotRegister(){
		alert("로그인 후 사용가능합니다.")
		
		location.href = "/lectureEvaluation/userServlet?cmd=loginForm"
	}
</script>
</body>
</html>