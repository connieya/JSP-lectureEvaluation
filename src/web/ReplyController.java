package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.google.gson.Gson;

import Service.BoardService;
import Service.LectureService;
import Service.ReplyService;
import Service.UserService;
import board.Board;
import board.CommonRespDto;
import board.UpdateReqDto;
import lecture.Lecture;
import reply.Reply;
import reply.SaveReqDto;
import user.User;
import utill.SHA256;
import utill.Script;

/**
 * Servlet implementation class UserController
 */

// 요청 경로는 : /lectureEvaluation/board
// 맵핑 경로 : /board
@WebServlet("/replyServlet")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	
		
		
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
		/*
		 * sendRedirect는 필터를 거친다. 그래서 RequestDispatcher를 사용해야 한다.
		 */
		
		
		/*
		 * 왜냐하면 RequestDispathcher는 톰켓이 생성하는 request와 response를 재사용한다. 다시 접근하는게 아니라
		 * 내부적으로 움직인다.
		 * 
		 * sendRedirect는 다시 나갔다 들어와서 필터를 타야한다.
		 */
		}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			String cmd = request.getParameter("cmd");
			System.out.println("cmd 값은 : " +cmd);
			ReplyService replyService = new ReplyService();
			HttpSession session = request.getSession();
			
			if(cmd.equals("register")) {
//				form 태그로 요청한것이 아니라 ajax로 json으로 요청해서 밑에서 처럼 할 수 없다. 
//				int bno = Integer.parseInt(request.getParameter("bno"));
//				String content = request.getParameter("content");
//				String userId = request.getParameter("userId");
				
				BufferedReader br = request.getReader();
				String reqData = br.readLine();
				System.out.println("reqData :" + reqData);
				Gson gson = new Gson();
//									json을 자바오브젝트로 변환 
				SaveReqDto dto = gson.fromJson(reqData, SaveReqDto.class);
				System.out.println("댓글 dto : "+dto);
				
				Reply reply = null;
				CommonRespDto<Reply> RespDto = new CommonRespDto<>();
				int result = replyService.댓글쓰기(dto); 
				//result = 댓글 번호 rno
				if(result != -1) {
					reply = replyService.댓글찾기(result);
					RespDto.setStatusCode(1);
					RespDto.setData(reply);
					
				}else {
					RespDto.setStatusCode(-1);
				}
				
				
				 // 1,-1
				 //어차피 insert만 할거라서 응답할게 없지만 그냥 넣음
				
				String responseData = gson.toJson(RespDto);
				System.out.println("responseData : "+ responseData);
				
				
				// 요청이 들어온 ajax에게 응답해줌
				Script.responseData(response, responseData);
				
//				if(result ==1) {
//					Script.responseData(response, responseData);
//				}else {
//					Script.responseData(response, responseData);
//				}
//				 위에 로직도 필요 없다 왜냐하면 reponseData안에 1 or -1 값이
//					담길 것이고 그러면 내가 요청한 ajax에서 분개해주면 된다 
				
			}else if(cmd.equals("delete")) {
				int rno = Integer.parseInt(request.getParameter("rno"));
				
				int result = replyService.댓글삭제(rno);
				
				CommonRespDto resp = new CommonRespDto<>();
				resp.setStatusCode(result);
				
				Gson gson = new Gson();
				String resultData = gson.toJson(resp);
				
				Script.responseData(response,resultData);
			}
			
			
	}
	
	

}
