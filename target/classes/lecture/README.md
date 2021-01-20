![image](https://user-images.githubusercontent.com/66653324/104120842-49da6900-537d-11eb-8733-0082787fa113.png)

강의 등록 할 때 <script> 태그를 넣어 사이트를 공격할 수 있다.
<br/> XSS 공격에 방어하기 위해 <script> 태그를 다른 문자로 치환해서 방어해보자 <br/>

`LectureDAO.java`
```java
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, userId);
			pstmt.setNString(2, lecture.getLectureName().replaceAll("<", "&alt;").replaceAll(">", "&gt;").replaceAll("\r\n","<br>"));
			pstmt.setNString(3, lecture.getProfessorName().replaceAll("<", "&alt;").replaceAll(">", "&gt;").replaceAll("\r\n","<br>"));
			pstmt.setInt(4, lecture.getLectureYear());
			pstmt.setNString(5, lecture.getSemesterDivide());
			pstmt.setNString(6, lecture.getLectureDivide());
			pstmt.setNString(7, lecture.getEvaluationTitle().replaceAll("<", "&alt;").replaceAll(">", "&gt;").replaceAll("\r\n","<br>"));
			pstmt.setNString(8, lecture.getEvaluationContent().replaceAll("<", "&alt;").replaceAll(">", "&gt;").replaceAll("\r\n","<br>"));
			pstmt.setNString(9, lecture.getTotalscore());
			pstmt.setNString(10, lecture.getLecturescore());
			pstmt.setNString(11, lecture.getJoyscore());
			
```