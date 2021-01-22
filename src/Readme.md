# 아이디 중복체크

`join.jsp`

```html
<button class="btn-info"  type="button" onclick="idCheck()" >아이디 중복검사</button>


```

```javascript
function idCheck(){
		
		
		var userId = $("#userId").val();
		console.log("userId: ",userId)
		if(userId == ""){
			alert("아이디를 입력하세요")
		}else{
			$.ajax({
				type : "post",
				url : "/lectureEvaluation/user?cmd=idCheck",
				data : userId,
				contentType :"/text/plain; charset=utf-8",
				dataType : "text"  // 응답 받을 데이터의 타입을 적으면 자바스크립트 오브젝트로 파싱해줌 
			}).done(function(data){
				if(data ==="ok"){
					isChecking = false;
					alert("이미 사용중인 아이디 입니다.")
				}else{
					isChecking = true;
					alert('사용 가능한 아이디입니다.')
				}
			});
			
		}
		
		
	}

```


```html
<form action="/lectureEvaluation/user?cmd=join" method="post" onsubmit="return valid()">

</form>

```

```javascript
var isChecking = false;
	
	function valid(){
		
		if(isChecking == false){
			alert("아이디 중복체크를 해주세요")
		}
		
		return isChecking;
		
			
	}

```


`UserController.java`


```java
else if(cmd.equals("idCheck")) {

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
				
			}

```