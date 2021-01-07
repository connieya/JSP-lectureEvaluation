package lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.User;
import utill.DatabaseUtill;

public class LectureDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	public int 강의등록(String userId,Lecture lecture) {
		
		System.out.println("강의등록 메서드 호출");
		System.out.println("userId :  "+userId);
		System.out.println("lecture 객체 : " +lecture);
		String sql = "insert into evaluation"
				+ "(userId, lectureName,professorName,lectureYear,semesterDivide,lectureDivide, evaluationTitle, evaluationContent, totalScore ,lectureScore, joyScore) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		
		conn = DatabaseUtill.dbPool();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, userId);
			pstmt.setNString(2, lecture.getLectureName());
			pstmt.setNString(3, lecture.getProfessorName());
			pstmt.setInt(4, lecture.getLectureYear());
			pstmt.setNString(5, lecture.getSemesterDivide());
			pstmt.setNString(6, lecture.getLectureDivide());
			pstmt.setNString(7, lecture.getEvaluationTitle());
			pstmt.setNString(8, lecture.getEvaluationContent());
			pstmt.setNString(9, lecture.getTotalscore());
			pstmt.setNString(10, lecture.getLecturescore());
			pstmt.setNString(11, lecture.getJoyscore());
			
			System.out.println(" 강의 평가 등록 성공");
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("강의 평가 등록 실패");
		}
		return -1; //db 오류
	}

}
