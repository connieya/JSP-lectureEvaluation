<%@page import="like.LikeDAO"%>
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
<%!
	public static String getClientIP(HttpServletRequest request){
	
	String ip = request.getHeader("X-FORWARDED-FOR");
	if(ip == null || ip.length() == 0){
		ip = request.getHeader("Proxy-Client-IP");
	}
	if(ip == null || ip.length() == 0){
		ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if(ip == null || ip.length() == 0){
		ip = request.getRemoteAddr();
	}
	
	return ip;
}

%>

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
	}
	
	String evaluationNo = null;
	
	if(request.getParameter("evaluationNo") != null){
		
		evaluationNo = request.getParameter("evaluationNo");
	}
	

	
	
	LectureDAO dao = new LectureDAO();
	LikeDAO likeDao = new LikeDAO();
	int result = likeDao.like(userId, evaluationNo, getClientIP(request));
		if(result ==1){
			result = dao.like(evaluationNo);
			if(result ==1){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('추천이 완료되었습니다')");
				script.println("location.href='/lectureEvaluation/index.jsp'");
				script.println("</script>");
				
			}else{
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('데이터 베이스 오류가 발생했습니다')");
				script.println("history.back()");
				script.println("</script>");
			}	
		
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 추천을 누른 글입니다.')");
		script.println("history.back()");
		script.println("</script>");
		
	}
%>


</body>
</html>