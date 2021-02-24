<%@page import="java.net.URLEncoder"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@page import="java.io.PrintWriter"%>
<%@page import="lecture.Lecture"%>
<%@page import="java.util.ArrayList"%>
<%@page import="lecture.LectureDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
	body {
		height : 100%;
		margin : 0;
		padding: 0;
	}
	html{
		height: 100%;
		margin: 0;
		padding: 0;
	}
	
	*{
		box-sizing: border-box;
		-webkit-box-sizing : border-box;
		-moz-box-sizing: border-box;
	}
	.wrap{
		min-height: 100%;
		position : relative;
		padding-bottom: 110px;
		margin-top: 0px;
		
		
	}
	
		input.form-control{
		width: 40%;
		margin: 0 auto;
	}	
</style>

</head>
<body>
<%@ include file="components/header.jsp" %>
<br/>
<%-- ${sessionScope.principal} --%>
	
	<% 
	    String lectureDivide = "전체";
		String searchType = "최신순";
		String search ="";
		int pageNumber = 0;
		
		if(request.getParameter("lectureDivide") != null){
			lectureDivide = request.getParameter("lectureDivide");
		}
		if(request.getParameter("searchType") != null){
			searchType = request.getParameter("searchType");
		}
		if(request.getParameter("search") != null){
			search = request.getParameter("search");
		}
		if(request.getParameter("pageNumber") != null){
			
			try{
				
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			}catch(Exception e){
				System.out.println("검색페이지 오류");
			}
		}
	
		String userId = null;
		if(session.getAttribute("principal") !=null){
			userId = (String) session.getAttribute("principal");
		}
	%>
	
	
	<div class="wrap">
		<section class="container">
	<form method="get" action="./index.jsp" class="form-inline mt-3">
		<select name="lectureDivide" class="form-control mx-1 mt-2">
			<option value="전체">전체</option>
			<option value="전공" <%if(lectureDivide.equals("전공")) out.println("selected"); %>>전공</option>
			<option value="교양" <%if(lectureDivide.equals("교양")) out.println("selected"); %>>교양</option>
			<option value="기타" <%if(lectureDivide.equals("기타")) out.println("selected"); %>>기타</option>
		</select>
		<select name="searchType" class="form-control mx-1 mt-2">
			<option value="최신순">최신순</option>
			<option value="추천순" <%if(searchType.equals("추천순")) out.println("selected"); %>>추천순</option>
		</select>
		<input type="text" name="search" class="form-control mx-1 mt-2" placeholder="내용을 입력하세요"/>
		<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
		</form>
		<% if(userId == null){
		
		%>
		<a href="/lectureEvaluation/user/login.jsp" class="btn btn-primary mx-1 mt-2" data-toggle="modal" onclick="alert('로그인 후 사용 가능합니다.')" >등록하기</a>
	
		<%
		}else{
			
		%>
		<a href="#registerModal" class="btn btn-primary mx-1 mt-2" data-toggle="modal" >등록하기</a>
		
		<%
		}
		%>
		<%
		LectureDAO lecuture = new LectureDAO();
		
		ArrayList<Lecture> e = new ArrayList<Lecture>();
		e = lecuture.검색결과(lectureDivide, searchType, search, pageNumber);
		if(e !=null)
			for(int i=0; i<e.size(); i++){
				if(i ==5) break;
				Lecture evaluationList = e.get(i);
		
		
	%>
	
	<div class="card bg-light mt-3">
		<div class="card-header bg-light">
		 	<div class="row">
		 		<div class="col-8 text-left"><%=evaluationList.getLectureName() %>&nbsp;[<%=evaluationList.getLectureDivide() %>]<small><%=evaluationList.getProfessorName() %></small></div>
		 		<div class="col-4 text-right">
		 			<p style="font-style: italic; "><%=evaluationList.getEvaluationNo() %></p>
		 		</div>
		 	</div>
		</div>
		<div class="card-body">
			<div class="row">
			<h5 class="col-8 text-left"><%=evaluationList.getEvaluationTitle() %>&nbsp;<small>(<%=evaluationList.getLectureYear() %>&nbsp;<%=evaluationList.getSemesterDivide()%>)</small></h5>
			<p class="col-4 text-right " style="font-style: italic;">작성자:&nbsp;<%= evaluationList.getUserId() %></p>
			</div>
			<p class="card-text"><%=evaluationList.getEvaluationContent() %></p>
			<div class="row">
				<div class="col-9 text-left">
				  총합<span style="color:red;"><%=evaluationList.getTotalscore() %></span>	
				  강의<span style="color:red;"><%=evaluationList.getLecturescore() %></span>
				  재미<span style="color:red;'"><%=evaluationList.getJoyscore() %></span>
				  <span style="color:green;">(추천:<%=evaluationList.getLikeCount() %>)</span>
				</div>
				<div class="col-3 text-right">
					
					<a href="/lectureEvaluation/lectureServlet?cmd=like&evaluationNo=<%=evaluationList.getEvaluationNo() %>" onclick="return confirm('추천 하시겠습니까?')">추천</a>
					<a href="/lectureEvaluation/lectureServlet?cmd=delete&evaluationNo=<%=evaluationList.getEvaluationNo() %>" onclick="return confirm('삭제 하시겠습니까?')">삭제</a>				
				</div>
			</div>
		</div>
	</div>
	<% 
	}
	%>
	
	
	</section>
	<ul class="pagination justify-content-center mt-3">
		<li class="page-item">
		<%
			if(pageNumber <= 0){	
		%>
			<a  class="page-link disabled" onclick="disabled" style="cursor: default;"  >이전</a>
		<%
			}else{
		%>
			<a class="page-link" href="./index.jsp?lectureDivide=<%= URLEncoder.encode(lectureDivide, "UTF-8")%>&searchType=
			<%=URLEncoder.encode(searchType, "UTF-8") %>&search=<%=URLEncoder.encode(search, "UTF-8") %>&pageNumber=<%= pageNumber -1 %> ">이전</a>
		<%
			}
		%>
		</li>
		<li>
		<%
			if(e.size() < 6){			
		%>
			<a  class="page-link disabled">다음</a>
		<%
			}else{
		%>
			<a class="page-link" href="./index.jsp?lectureDivide=<%= URLEncoder.encode(lectureDivide, "UTF-8")%>&searchType=
			<%=URLEncoder.encode(searchType, "UTF-8") %>&search=<%=URLEncoder.encode(search, "UTF-8") %>&pageNumber=<%= pageNumber +1 %> ">다음</a>
		<%
			}
		%>
		</li>
	</ul>
<%@ include file="/components/footer.jsp" %>
	 </div>  <!-- div. wrap -->
	<!--강의평가 등록 모달  -->
	<%@ include file="/modal/lecture.jsp" %>
	
	<!-- 신고하기 모달 -->
	<%@ include file="/modal/report.jsp" %>
	
</body>
</html>