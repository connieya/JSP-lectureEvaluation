<%@page import="utill.SHA256"%>
<%@page import="user.User"%>
<%@page import="user.UserDAO"%>
<%@page import="java.io.PrintWriter"%>
<% request.setCharacterEncoding("UTF-8"); %>
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
	String userPassword = null;
	String userEmail = null;
	String userName = null;
	if(request.getParameter("userId") !=null){
	
		userId =request.getParameter("userId");
		
	}
	if(request.getParameter("userPassword") !=null){
		
		userPassword =request.getParameter("userPassword");
		
	}
	if(request.getParameter("userEmail") !=null){
		
		userEmail =request.getParameter("userEmail");
		
	}
	if(request.getParameter("userName") !=null){
			
		userName =request.getParameter("userName");
			
		}
	if(userId == null || userPassword ==null || userEmail==null || userName ==null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다.')");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	UserDAO userDao = new UserDAO();
	
	User user = new User();
	user.setUserId(userId);
	user.setUserPassword(userPassword);
	user.setUserName(userName);
	user.setUserEmail(userEmail);
	user.setUserEmailHash(SHA256.getSHA256(userEmail));
	
	int result = userDao.join(user);
	if (result == -1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 존재하는 아이디 입니다.')");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원가입이 완료 되었습니다.')");
		script.println("location.href ='emailSendAction.jsp'");
		script.println("</script>");
		script.close();
	}

%>

</body>
</html>