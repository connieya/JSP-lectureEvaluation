package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ApiServletTest1
 */
@WebServlet("/test1")
public class ApiServletTest1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiServletTest1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//response.setContentType("text/html; charset=utf-8");  // 1번
		//response.setContentType("text/plain; charset=utf-8"); // 2번
		//response.setContentType("application/json; charset=utf-8");// 3번
	
		PrintWriter out = response.getWriter();
		String reqUrl = request.getRequestURI();
		String mappingUrl = request.getServletPath();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>HelloServlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>HelloServlet</h1>");
		out.println("<h3>요청 경로는 : "+reqUrl+"</h3>");
		out.println("<h3>맵핑 경로는 : "+mappingUrl+"</h3>");
		out.println("</body>");
		out.println("</html>");
		out.close();
		// 위의 1번의 경우 html 파일이니깐 3문장의 글자가 보인다.
		// 위의 2번인 경우  plain이니깐 그냥 html 코드 그대로 다 보인다.
		// 위의 3번인 경우 json 파일로 지정했는데, html 코드 그대로 보이네?? 음.. ㅋ.
	
	
		// 결론, 요청할 때는 contentType이 중요하지 않다. 왜? 내가 직접 지정하니깐
		// 대신 응답할때는 중요하다. 왜냐 브라우저가 받기 때문에 그에 맞게 지정해줘야한다잉~~
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String food = request.getParameter("food");
		
		System.out.println("food : " +food);
		
		// 여기서 X-WWW-form-urlencoded로 데이터를 던지면 food : 김치볶음밥
		// json으로 던지면 food : null 이다.
		
		//response.setContentType("application/json; charset=utf-8");
		System.out.println("food : " +food);
		// application/json 으로 설정했는데도 null 값이 뜬다.
		BufferedReader br = request.getReader();
		String input;
		StringBuffer buffer = new StringBuffer();
		while(( input= br.readLine() ) != null) {
			buffer.append(input);
			
		}
		System.out.println(buffer.toString());
	}

	// 이제야 json으로 받아진다. 대신 위의 값은 null이고
	//buffer.toString() 이부분이 json으로 받아진다.
}
