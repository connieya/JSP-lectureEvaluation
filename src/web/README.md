# MVC 모델 2

## Not Found 404

![image](https://user-images.githubusercontent.com/66653324/105136252-b0594700-5b34-11eb-8f1d-cb97ac338acf.png)

## 디버깅

요청 경로 : /lectureEvaluation/user
맵핑 경로 : /user



__UserController.java__

```java
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
      ....
      
    }  

```


__join.jsp__

```java
	<form action="/lectureEvaluation/user?cmd=join" method="post">
		<div class="form-group">
			<label for="userId">아이디</label> 
		....
		</form>
		

```
