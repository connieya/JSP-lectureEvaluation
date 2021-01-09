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
	input.form-control{
		width: 40%;
		margin: 0 auto;
	}
</style>
</head>
<body>
<%@ include file="components/header.jsp" %>
<br/>
	
	<%
		String userId = null;
		if(session.getAttribute("principal") !=null){
			userId = (String) session.getAttribute("principal");
		}
	%>

		<section class="container">
	<form method="get" action="./index.jsp" class="form-inline mt-3">
		<select name="lectureDivide" class="form-control mx-1 mt-2">
			<option value="전체">전체</option>
			<option value="전공">전공</option>
			<option value="교양">교양</option>
			<option value="기타">기타</option>
		</select>
		<input type="text" name="search" class="form-control mx-1 mt-2" placeholder="내용을 입력하세요"/>
		<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
		<% if(userId == null){
		
			
			%>
		<a href="/lectureEvaluation/user/login.jsp" class="btn btn-primary mx-1 mt-2" data-toggle="modal" onclick="alert('로그인 후 사용 가능합니다.')" >등록하기</a>
		<a href="#" class="btn btn-danger mx-1 mt-2" data-toggle="modal" onclick="alert('로그인 후 사용 가능합니다.')">신고하기</a>	
		<%
		}else{
			
		
		%>
		<a href="#registerModal" class="btn btn-primary mx-1 mt-2" data-toggle="modal" >등록하기</a>
		<a href="#reportModal" class="btn btn-danger mx-1 mt-2" data-toggle="modal">신고하기</a>
		<%
		}
		%>
	</form>
	
		<%
		LectureDAO lecuture = new LectureDAO();
		
		ArrayList<Lecture> e = new ArrayList<Lecture>();
		e = lecuture.강의목록();

		for(int i=0; i<e.size(); i++){
			
		
	%>
	
	<div class="card bg-light mt-3">
		<div class="card-header bg-light">
		 	<div class="row">
		 		<div class="col-8 text-left"><%=e.get(i).getLectureName() %>&nbsp;[<%=e.get(i).getLectureDivide() %>]<small><%=e.get(i).getProfessorName() %></small></div>
		 		<div class="col-4 text-right">
		 			<p style="font-style: italic; "><%=e.get(i).getEvaluationNo() %></p>
		 		</div>
		 	</div>
		</div>
		<div class="card-body">
			<div class="row">
			<h5 class="col-8 text-left"><%=e.get(i).getEvaluationTitle() %>&nbsp;<small>(<%=e.get(i).getLectureYear() %>&nbsp;<%=e.get(i).getSemesterDivide() %>)</small></h5>
			<p class="col-4 text-right" style="font-style: italic;">작성자:&nbsp;<%= e.get(i).getUserId() %></p>
			</div>
			<p class="card-text"><%=e.get(i).getEvaluationContent() %></p>
			<div class="row">
				<div class="col-9 text-left">
				  총합<span style="color:red;"><%=e.get(i).getTotalscore() %></span>	
				  강의<span style="color:red;"><%=e.get(i).getLecturescore() %></span>
				  재미<span style="color:red;'"><%=e.get(i).getJoyscore() %></span>
				  <span style="color:green;">(추천:<%=e.get(i).getLikeCount() %>)</span>
				</div>
				<div class="col-3 text-right">
					<a href="./likeAction.jsp?evaluationId" onclick="return confirm('추천 하시겠습니까?')">추천</a>
					<a href="./DeleteAction.jsp?evaluationId" onclick="return confirm('삭제 하시겠습니까?')">삭제</a>				
				</div>
			</div>
		</div>
	</div>
	<% 
	}
	%>
	
	
	</section>
	
	<!-- 강의 평가 모달 -->
	
	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">평가 등록</h5>
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
					</button>
				</div><!--  modal header -->
				<div class="modal-body">
					<form action="/lectureEvaluation/evaluation/evaluationRegisterAction.jsp" method="post">
						<div class="form-row">
							<div class="form-group col">
								<label for="">강의명</label>
								<input type="text" name="lectureName" class="form-control" maxlength="40" />
							</div>
							<div class="form-group col">
								<label for="">교수명</label>
								<input type="text" name="professorName" class="form-control" maxlength="40" />
							</div>
						</div>
						<div class="form-row">
					<div class="form-group col-sm-4">
						<label for="">수강연도</label>
						<select name="lectureYear" class="form-control">
							<option value="2013">2013</option>
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
							<option value="2018">2018</option>
							<option value="2019">2019</option>
							<option value="2020" selected>2020</option>
							<option value="2021">2021</option>
						</select>
						
					</div>
					<div class="form-group col-sm-4">
						<label for="">수강 학기</label>
						<select name="semesterDivide" id="" class="form-control">
								<option value="1학기" selected>1학기</option>
								<option value="여름학기">여름학기</option>
								<option value="2학기">2학기</option>
								<option value="겨울학기">겨울학기</option>
						</select>
					</div>
						<div class="form-group col-sm-4">
						<label for="">강의 구분</label>
						<select name="lectureDivide" id="" class="form-control">
								<option value="전공">전공</option>
								<option value="교양" selected>교양</option>
								<option value="기타">기타</option>
						</select>
					</div>
				</div><!--  div form row -->
				<div class="form-group col">
					<label for="">제목</label>
					<input type="text" name="evaluationTitle" class="form-control" maxlength="40" />
				</div>
				<div class="form-group col">
					<label for="">내용</label>
					<textarea  name="evaluationContent" class="form-control" maxlength="2048" ></textarea>
				</div>
				<div class="form-row">
				 	<div class="form-group col-sm-3">
				 		<label for="">총합</label>
				 		<select name="totalscore" class="form-control">
				 			<option value="A" >A</option>
				 			<option value="B" >B</option>
				 			<option value="C" >C</option>
				 			<option value="D" >D</option>
				 			<option value="F" >F</option>
				 		</select>
				 	
				 	</div>
				 	<div class="form-group col-sm-3">
				 		<label for="">재미</label>
				 		<select name="joyscore" class="form-control">
				 			<option value="A" >A</option>
				 			<option value="B" >B</option>
				 			<option value="C" >C</option>
				 			<option value="D" >D</option>
				 			<option value="F" >F</option>
				 		</select>
				 	
				 	</div>
				 	<div class="form-group col-sm-3">
				 		<label for="">강의</label>
				 		<select name="lecturescore" class="form-control">
				 			<option value="A" >A</option>
				 			<option value="B" >B</option>
				 			<option value="C" >C</option>
				 			<option value="D" >D</option>
				 			<option value="F" >F</option>
				 		</select>
				 	
				 	</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					<button type="submit" class="btn btn-primary">등록하기</button>
				
				</div>
					</form>
				</div><!--  div modal body -->
				
			
				
				
			</div> <!-- div modal content -->
			
		</div> <!-- div modal dialog -->
	</div> <!-- div modal fade -->
	
<!-- 	신고하기 모달 -->
	<div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">신고하기</h5>
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="/lectureEvaluation/evaluation/reportAction.jsp" method="post">
	
				<div class="form-group">
					<label for="">신고 제목</label>
					<input type="text" name="reportTitle" class="form-control" maxlength="40" />
				</div>
				<div class="form-group">
					<label for="">신고 내용</label>
					<textarea  name="reportContent" class="form-control" maxlength="2048" ></textarea>
				</div>		
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					<button type="submit" class="btn btn-danger">신고하기</button>
				
				</div>
					</form>
				</div><!--  div modal body -->
				
			
				
				
			</div> <!-- div modal content -->
			
		</div> <!-- div modal dialog -->
	</div> <!-- div modal fade -->
	<footer class="bg-dark mt-4 p-5 text-center" style="color:#ffffff;">
		CopyRight &copy; 2020 박건희 All Rights Reserved.
	</footer>
	
	
</body>
</html>