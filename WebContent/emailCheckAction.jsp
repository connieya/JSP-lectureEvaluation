<%@page import="utill.SHA256"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="user.UserDAO"%>
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
	
		String code = null;
		if(request.getParameter("code") != null){
			code = (String) request.getParameter("code");
		}
		
		UserDAO dao = new UserDAO();
		
		if( (session.getAttribute("principal") != null)){
			userId = (String) session.getAttribute("principal");
		}
		
		if(userId == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인이 필요합니다.')");
			script.println("location.href ='login.jsp'");
			script.println("</script>");
			script.close();
			return;
		}
		
		String userEmail  = dao.getUserEmail(userId);
		boolean isRight =  ( new SHA256().getSHA256(userEmail).equals(code) ) ? true : false;
		// 네이버 메일로 인증 확인메일이 오면 클릭하는 것
		
		if(isRight == true){
			dao.setUserEmailChecked(userId);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('인증에 성공했습니다.')");
			script.println("location.href='index.jsp'");
			script.println("</script>");
			script.close();
		}else{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효 하지 않은 코드입니다.')");
			script.println("location.href='index.jsp'");
			script.println("</script>");
			script.close();
		}
	%>
	
</body>
</html>