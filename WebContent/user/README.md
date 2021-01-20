null vs ""

```java
if(userId == "" || userPassword =="" || userEmail=="" || userName =="") {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다.')");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
```

null = no vaule <br/>
"" = empty ( 0 length string)
