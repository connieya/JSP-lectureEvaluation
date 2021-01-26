# Filter 사용

`web.xml`

```xml
<filter>

		<filter-name>CharConfig</filter-name>
		<filter-class>config.CharConfig</filter-class>
	</filter>
	
	<filter>
		<filter-name>forbiddenUrlConfig</filter-name>
		<filter-class>config.ForbiddenUrlConfig</filter-class>
	</filter>

<!-- 	매핑 순서 1번 -->
	<filter-mapping>
		<filter-name>CharConfig</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
<!-- 
	매핑 순서 2번 -->
	<filter-mapping>
		<filter-name>forbiddenUrlConfig</filter-name>
		<url-pattern>*.jsp</url-pattern>
		
	</filter-mapping>
```

필터사용

```java
public class ForbiddenUrlConfig implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

			HttpServletRequest request  = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			
			System.out.println("ForbiddenUrlConfig 접근");
			System.out.println(request.getRequestURL());
			System.out.println(request.getRequestURI());
			
			if(request.getRequestURI().equals("/lectureEvaluation/") || request.getRequestURI().equals("/lectureEvaluation/index.jsp")) {
				chain.doFilter(request, response);
			}else {
				PrintWriter out = response.getWriter();
				out.print("잘못된 접근입니다.");
				out.flush();
			}			
	}		
}
```

  /lectureEvaluation/ 또는 /lectureEvaluation/index.jsp 에 해당하는 uri만 허용 
  
  -------------
  
## RequestDispathcher 사용
![image](https://user-images.githubusercontent.com/66653324/105807892-256fc500-5fea-11eb-99e2-e3b764ba3f57.png)

<br/>
RequestDispathcher는 톰켓이 생성하는 request와 reponse를 재사용한다. <br/>
그렇기 때문에 톰켓에 다시 접근하는 것이 아니라 내부적으로 움직인다.
<br/>

반면 sendRedirect는 톰켓은 나갔다가 다시 들어오기 때문에
필터를 다시 거치게 된다. <br/>

그래서 페이지에 대한 요청을 RequestDispatcher으로 해야한다.





 
  