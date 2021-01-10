<%@page import="lecture.LectureDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String userId = null;
	if(session.getAttribute("principal") != null){
		userId = (String) session.getAttribute("principal");
	}
	
	if(userId == null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인이 필요한 서비스 입니다.')");
		script.println("location.href='../user/login.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	
	String evaluationNo = null;
	
	if(request.getParameter("evaluationNo") != null){
		
		evaluationNo = request.getParameter("evaluationNo");
	}
	
	LectureDAO dao = new LectureDAO();
	if(userId.equals(dao.getUserId(evaluationNo))){
		
		int result = dao.delete(evaluationNo);
		if(result ==1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('삭제가 완료되었습니다')");
			script.println("location.href='/lectureEvaluation/index.jsp'");
			script.println("</script>");
			script.close();
			return;
		}else{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터 베이스 오류가 발생했습니다')");
			script.println("history.back()");
			script.println("</script>");
			script.close();
			return;
		}
		
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('권한이 없습니다.')");
		script.println("history.back()");
		script.println("</script>");
		
	}
%>


</body>
</html>