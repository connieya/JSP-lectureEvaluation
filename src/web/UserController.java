package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import Service.UserService;
import user.User;
import utill.SHA256;
import utill.Script;

/**
 * Servlet implementation class UserController
 */

// 요청 경로는 : /lectureEvaluation/user
// 맵핑 경로 : /user
@WebServlet("/userServlet")
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
	
	//http://localhost:8090/lectureEvaluation/user?cmd=''''
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			System.out.println("doProcess 호출");
			String cmd = request.getParameter("cmd");
			// 커맨드 요청
			HttpSession session = request.getSession();
			
			System.out.println("cmd 값은 : " +cmd);
			UserService userService = new UserService();
			// http://localhost:8090//lectureEvaluation/user?cmd=join
			if(cmd.equals("join")) {
				String userId = request.getParameter("userId");
				String userName = request.getParameter("userName");
				String userPassword = request.getParameter("userPassword");
				String userEmail = request.getParameter("userEmail");
				String userAddr = request.getParameter("userAddr");
				
				User user = new User();
				user.setUserId(userId);
				user.setUserName(userName);
				user.setUserPassword(userPassword);
				user.setUserEmail(userEmail);
				user.setUserEmailHash(SHA256.getSHA256(userEmail));
				user.setUserAddr(userAddr);
				
				//System.out.println("회원가입 : " +user);
				
				int result = userService.회원가입(user);
				if(result ==1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('회원가입이 완료 되었습니다.')");
					script.println("location.href ='/lectureEvaluation/index.jsp'");
					script.println("</script>");
					script.close();
					//Script.responseData(response, "회원가입 성공");
					//response.sendRedirect("/lectureEvaluation/index.jsp");
					
				}else {
					Script.back(response, "회원가입 실패");
				}
			}else if(cmd.equals("login")) {
				String userId = request.getParameter("userId");
				String userPassword = request.getParameter("userPassword");
				User user = new User();
				user.setUserId(userId);
				user.setUserPassword(userPassword);
				
				int result = userService.로그인(user);
				if(result ==1) {
					
					session.setAttribute("principal", user.getUserId());
					System.out.println(user);
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('로그인 성공')");
					script.println("location.href ='/lectureEvaluation/index.jsp'");
					script.println("</script>");
					
				}else {
					Script.back(response, "로그인 실패");
				}
				
				
				
			}else if(cmd.equals("idCheck")) {
				// 이걸로는 받을 수 없다 text/plain 이기 때문에
//				request.getParameter("userId");
				BufferedReader br = request.getReader();
				String userId  = br.readLine();
				System.out.println("userId : "+userId);
				
				String result = userService.아이디중복체크(userId);
				System.out.println("result!!! : "+result);
				if(result.equals("ok")) {
					PrintWriter out = response.getWriter();
					out.print("ok");
					out.flush();
				}
				
			}else if(cmd.equals("loginForm")) {
			//	response.sendRedirect("/lectureEvaluation/user/login.jsp");
//				톰켓에서 나갔다가 다시 들어오기 때문에 필터에서 걸린다. 
				
				RequestDispatcher dis =
						request.getRequestDispatcher("user/login.jsp");
				
				dis.forward(request, response);
			}else if(cmd.equals("joinForm")) {
				RequestDispatcher dis =
						request.getRequestDispatcher("user/join.jsp");
				
				dis.forward(request, response);
			}else if(cmd.equals("logout")) {
				RequestDispatcher dis =
						request.getRequestDispatcher("user/logout.jsp");
				
				dis.forward(request, response);
			}else if(cmd.equals("userUpdate")) {
				String userId = null;
				if( session.getAttribute("principal") != null) {
					userId = (String) session.getAttribute("principal");
				}
				User user = new User();
				
				user = userService.유저정보가져오기(userId);
				
				request.setAttribute("users", user);
				RequestDispatcher dis =
						request.getRequestDispatcher("user/userUpdate.jsp");
				
				dis.forward(request, response);
			}else if(cmd.equals("userUpdateAction")) {
				String userId = request.getParameter("sessionValue");
				String userName = request.getParameter("userName");
				String userPassword = request.getParameter("userPassword");
				String userAddr = request.getParameter("userAddr");
				int result =userService.회원수정(userId ,userName, userPassword, userAddr);
				if(result ==1 ){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('회원 수정완료')");
					script.println("location.href='/lectureEvaluation/index.jsp'");
					script.println("</script>");
				}else{
					Script.back(response, "회원수정 실패");
				}
				
			
			}else if(cmd.equals("userDelete")) {
				String userId = request.getParameter("userId");
				
				int result = userService.회원탈퇴(userId);

				if(result ==1 ){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('회원탈퇴가 완료되었습니다. ')");
					script.println("location.href='/lectureEvaluation/userServlet?cmd=logout'");
					script.println("</script>");
				}else{
					Script.back(response, "오류가 발생했습ㄴ디ㅏ.");
				}
			}else if(cmd.equals("logout")) {
				RequestDispatcher dis =
						request.getRequestDispatcher("user/logout.jsp");
				
				dis.forward(request, response);
				
			}else if(cmd.equals("jusoPop")) {
				RequestDispatcher dis =
						request.getRequestDispatcher("user/jusoPopup.jsp");
				
				dis.forward(request, response);
				
			}
			
		}
	
	

}
