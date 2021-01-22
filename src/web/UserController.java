package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import Service.UserService;
import user.User;
import utill.SHA256;
import utill.Script;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
		
		}
	
	//http://localhost:8080/blog/user?cmd=''''
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			Session session;
			System.out.println("doProcess 호출");
			String cmd = request.getParameter("cmd");
			// 커맨드 요청
			
			System.out.println("cmd 값은 : " +cmd);
			UserService userService = new UserService();
			// http://localhost:8090//lectureEvaluation/user?cmd=join
			if(cmd.equals("join")) {
				String userId = request.getParameter("userId");
				String userName = request.getParameter("userName");
				String userPassword = request.getParameter("userPassword");
				String userEmail = request.getParameter("userEmail");
				
				User user = new User();
				user.setUserId(userId);
				user.setUserName(userName);
				user.setUserPassword(userPassword);
				user.setUserEmail(userEmail);
				user.setUserEmailHash(SHA256.getSHA256(userEmail));
				
				System.out.println("회원가입 : " +user);
				
				int result = userService.회원가입(user);
				if(result ==1) {
					Script.responseData(response, "회원가입 성공");
					response.sendRedirect("index.jsp");
				}else {
					Script.back(response, "회원가입 실패");
				}
			}else if(cmd.equals("login")) {
				String userId = request.getParameter("userId");
				String userPassword = request.getParameter("userPassword");
				User user = new User();
				
				user.setUserId(userId);
				user.setUserPassword(userPassword);
				
				
			}
			
		}
	
	

}
