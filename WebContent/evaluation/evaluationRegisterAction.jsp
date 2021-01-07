<%@page import="lecture.LectureDAO"%>
<%@page import="java.io.PrintWriter"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="lecture" class="lecture.Lecture" scope="page" />
<jsp:setProperty property="*" name="lecture"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../components/header.jsp" %>
	<% 
		String userId = null;
		if(session.getAttribute("principal") != null){
			userId = (String) session.getAttribute("principal");
		}
		
		if(userId == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인이 필요한 서비스입니다.')");
			script.println("location.href='/lectureEvaluation/user/login.jsp'");
			script.println("</script>");
		}
		
		LectureDAO dao = new LectureDAO();
		int result = dao.강의등록(userId ,lecture);
		if(result ==1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('강의 평가가 등록되었습니다.')");
			script.println("location.href='/lectureEvaluation/index.jsp'");
			script.println("</script>");
		}else{
			
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생하였습니다. 강의 평가 등록 실패 ')");
			script.println("location.href='/lectureEvaluation/index.jsp'");
			script.println("</script>");
			
		}
	
	%>

</body>
</html>