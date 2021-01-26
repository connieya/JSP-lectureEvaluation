# Ajax 

## 사용 방법

- Javascript XML 객체 (기본) 
- jQuery 라이브러리 - ajax 함수  (이번 프로젝트에서 사용)
- fetch() 함수    (react.js 에서 사용해봄)
- axios() 함수  (Vue.js 공부 중에 발견 , 최근에 많이 사용한다고 한다.)


## 정의

Asynchronous Javascript and XML
자바스크립트를 통해서 서버에 데이터를 요청하고 json을 응답받는다.


## ex

`기본`
```html

<div id="ex">
<h2>The XMLHttpRequest Object</h2>
<button type="button" onclick="loadDoc()">Change Content</button>
</div>

<script>
function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("ex").innerHTML =
      this.responseText;
    }
  };
  			
  xhttp.open("GET", "../index.jsp", true);
  xhttp.send();
}
</script>

```



-------------------------

`jQuery`

```html

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<body>
	<button onclick="isCheck()">아이디 중복체크</button>
	<div id="demo">	</div>
	
	<script>
		function isCheck(){
			$.ajax("http://localhost:8090/lectureEvaluation/ajax").done(function(data){
				alert(data);
			});
			
		}
		
	/* 	통신 완료 jQuery로 ajax 통신 끝  */
	</script>
</body>

```


`fetch`

```html


<body>
	<button onclick="isCheck()">아이디 중복체크</button>
	<div id="demo">	</div>
	
	<script>
		function isCheck(){
			fetch("http://localhost:8090/lectureEvaluation/ajax").then(function(data){
				return data.text();
			}).then(function(data){
				alert(data);
			});
			
				
		}
	</script>
</body>

```
