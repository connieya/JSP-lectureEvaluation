# 주소 API

![image](https://user-images.githubusercontent.com/66653324/105454113-b67e2d80-5cc4-11eb-9c42-67f9b0a978f1.png)


### API 신청 <br/>

주소 승인키가 발급 되면 값을 기억하기위해 따로 저장

---- 

![image](https://user-images.githubusercontent.com/66653324/105454435-30161b80-5cc5-11eb-9a46-bad1ee043e79.png)


### API정보/요청변수/출력결과

요청변수로
confmkey, returnUrl, resultType를 사용
resultType은 4번 사용


<br/>
출력결과는 roadFullAddr 를 사용

![image](https://user-images.githubusercontent.com/66653324/105456443-833d9d80-5cc8-11eb-9fb1-f33fe1599695.png)




### 구현

```html
<button type="button" class="btn-success" onclick="goPopup();">주소검색</button>
```


```html
<script>
	
	
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup(){
	var pop = window.open("/lectureEvaluation/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
function jusoCallBack(roadFullAddr){
	
		var addrEI =document.querySelector("#userAddr")
		addrEI.value =  roadFullAddr	
}
</script>

```
