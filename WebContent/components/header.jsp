<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<style>
	nav{
		display:flex;
		justify-content: center;
		
	}
</style>
</head>
<body>
 <%
	String principal = null;

 if(session.getAttribute("principal") !=null){
	 principal = (String) session.getAttribute("principal");
	 
	 
	 

 };
%> 
<nav class="navbar navbar-expand-sm bg-dark navbar-dark" >
	
  <ul class="navbar-nav">
    <li class="nav-item active">
      <a class="nav-link " href="/lectureEvaluation/index.jsp">Home</a>
    </li>
    <c:choose>
    <c:when test="${sessionScope.principal != null }">
     <li class="nav-item">
    <%--   <a class="nav-link" href="/lectureEvaluation/user/userUpdate.jsp?userId=<%=principal %>">회원정보</a> --%>
	  <a class="nav-link" href="/lectureEvaluation/userServlet?cmd=userUpdate">회원정보</a>      
    </li>
     <li class="nav-item">
     <a class="nav-link" onclick="return confirm('로그아웃 하시겠습니까?')" href="/lectureEvaluation/userServlet?cmd=logout">로그아웃</a>
   	  </li>
    
    </c:when>
    
    <c:otherwise>
     <li class="nav-item">
      <a class="nav-link" href="/lectureEvaluation/userServlet?cmd=joinForm">회원가입</a>
    </li>
    <li class="nav-item">
      <!-- <a class="nav-link" href="/lectureEvaluation/user/login.jsp">로그인</a>  -->
     <!--  <a class="nav-link" href="/lectureEvaluation/user?cmd=loginForm">로그인</a>  -->
     <a class="nav-link" href="<%=request.getContextPath() %>/userServlet?cmd=loginForm">로그인</a>
     
    </li>
    </c:otherwise>
    </c:choose>
    
    <li class="nav-item">
      <!-- <a class="nav-link" href="/lectureEvaluation/board/boardList.jsp">게시판</a> 필터걸어놔서 안된다잉~ -->
    	<a class="nav-link" href="/lectureEvaluation/boardServlet?cmd=boardList">게시판</a>
    </li>
     <% if(principal == null) 
    {
    
    %>
   
    <%
    }else{
    	
    %>
    
    <%
    } 
    %>
  </ul>
  
  
   <form class="form-inline justify-content-end text-right" action="/lectureEvaluation/index.jsp">
    <input class="form-control mr-sm-2 text-primary" name="search" type="text" placeholder="Search">
    <button class="btn btn-success" type="submit">Search</button>
  </form>
</nav>

</body>
</html>