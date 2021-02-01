<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	td  a  {
		color: black;
	}
	li > a{
		color :black;
	}
	div.pageBox{
		margin-left: auto;
		margin-right: auto;
		text-align: center;
	}
</style>
</head>
<body>
<%@ include file="../components/header.jsp" %>
<div class="container">
 <h1>자유게시판</h1>
	<div class="row">
           
           	 <div class="form-inline d-flex justify-content-end" style="width:100%;" >
          
           <c:choose>
           	<c:when test="${sessionScope.principal != null }">
           	  <a class="btn btn-info " href="/lectureEvaluation/boardServlet?cmd=boardForm">글쓰기</a>
           	</c:when>
           	<c:otherwise>
           	  <a class="btn btn-info" onclick="NotRegister()">글쓰기</a>
           	</c:otherwise>
           </c:choose>
          
           	</div>
  <table class="table table-bordered" style="text-align: center; border : 1px solid #ddddddd ; margin-top : 10px; ">
  
    <thead>
      <tr>
        <th style="background-color: #eeeeee;">글번호</th>
        <th style="background-color: #eeeeee;">제목</th>
        <th style="background-color: #eeeeee;">작성자</th>
        <th style="background-color: #eeeeee;">조회수</th>
        <th style="background-color: #eeeeee;">작성일</th>
      </tr>
    </thead>
    <tbody>
   <c:forEach var="board" items="${boards}">
   
      <tr>
        <td>${board.bno}</td>
        <td><a href="/lectureEvaluation/boardServlet?cmd=detail&bno=${board.bno}">${board.title }</a></td>
        <td>${board.userId }</td>
        <td>${board.readCount }</td>
        <td>${board.createDate}</td>
      </tr>
    </c:forEach>
     
    </tbody>
  </table>
  
  </div>  <!-- div row -->
  
  <div class="pageBox" style="margin: 5px auto; ">
  <ul class="pagination" style="color: #000000">
  <li class="page-item"><a class="page-link" href="#">Previous</a></li>
  <li class="page-item"><a class="page-link" href="#">1</a></li>
  <li class="page-item"><a class="page-link" href="#">2</a></li>
  <li class="page-item"><a class="page-link" href="#">3</a></li>
  <li class="page-item"><a class="page-link" href="#">Next</a></li>
	</ul>
</div>
  
</div>


<script>
	function NotRegister(){
		alert("로그인 후 사용가능합니다.")
		
		location.href = "/lectureEvaluation/userServlet?cmd=loginForm"
	}
</script>

<%@ include file="../components/footer.jsp" %>
</body>


</html>