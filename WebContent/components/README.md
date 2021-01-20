페이지를 전환하는데 오류가 발생하였는데,
<br/>
상대경로 -> 절대경로 수정 후 <br/>
디버깅 성공!! <br/>


`header.jsp`
`상대 경로`

```html
<ul class="navbar-nav">
    <li class="nav-item active">
      <a class="nav-link" href="../index.jsp">Home</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="user/join.jsp">회원가입</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="../user/login.jsp">로그인</a>
    </li>
    <li class="nav-item">
      <a class="nav-link disabled" href="#">Disabled</a>
    </li>
  </ul>
```
`절대 경로`
```html
<ul class="navbar-nav">
    <li class="nav-item active">
      <a class="nav-link" href="/lectureEvaluation/index.jsp">Home</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/lectureEvaluation/user/join.jsp">회원가입</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/lectureEvaluation/user/login.jsp">로그인</a>
    </li>
    <li class="nav-item">
      <a class="nav-link disabled" href="#">Disabled</a>
    </li>
  </ul>

```