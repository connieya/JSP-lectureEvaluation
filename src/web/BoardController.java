package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import Service.BoardService;
import Service.UserService;
import user.User;
import utill.SHA256;
import utill.Script;

/**
 * Servlet implementation class UserController
 */

// 요청 경로는 : /lectureEvaluation/board
// 맵핑 경로 : /board
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
		
		}
	
	//http://localhost:8080/lectureEvaluaiton/board?cmd=''''
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			System.out.println("doProcess 호출");
			String cmd = request.getParameter("cmd");
			
			
			System.out.println("cmd 값은 : " +cmd);
			
			BoardService boardService = new BoardService();
			
			if(cmd.equals("boardForm")) {
				response.sendRedirect("/lectureEvaluation/board/boardForm.jsp");
			}
			
	}
	
	

}
