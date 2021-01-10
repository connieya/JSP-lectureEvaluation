package lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				+ "(userId, lectureName,professorName,lectureYear,semesterDivide,lectureDivide, evaluationTitle, evaluationContent, totalScore ,lectureScore, joyScore,likeCount) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,0)";
		
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
		}finally {
			try {
				
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		}
		return -1; //db 오류
	}
	
	public ArrayList<Lecture> 강의목록() {
		
		ArrayList<Lecture> lecture = new ArrayList<Lecture>();
		String sql = "select * from evaluation";
		
		conn = DatabaseUtill.dbPool();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Lecture lc = new Lecture();
				lc.setEvaluationNo(rs.getInt(1));
				lc.setUserId(rs.getString(2));
				lc.setLectureName(rs.getString(3));
				lc.setProfessorName(rs.getString(4));
				lc.setLectureYear(rs.getInt(5));
				lc.setSemesterDivide(rs.getString(6));
				lc.setLectureDivide(rs.getString(7));
				lc.setEvaluationTitle(rs.getString(8));
				lc.setEvaluationContent(rs.getString(9));
				lc.setTotalscore(rs.getString(10));
				lc.setLecturescore(rs.getString(11));
				lc.setJoyscore(rs.getString(12));
				lc.setLikeCount(rs.getInt(13));
				
				lecture.add(lc);
			
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		}
		
		
		
		return lecture;
	}
	
	
	public ArrayList<Lecture> 검색결과(String lectureDivide ,String searchType , String  search , int pageNumber){
		System.out.println("검색 결과 메서드");
		if(lectureDivide.equals("전체")) {
			lectureDivide = "";
		}
		ArrayList<Lecture> evaluationlist = null;
		
		String sql = "";
		try {
			System.out.println("lecturDivde 값은: "+lectureDivide);
			System.out.println("searchType 값은: "+searchType);
			if(searchType.equals("최신순")) {
				sql = "select * from evaluation where lectureDivide like ? and concat(lectureName ,professorName,evaluationTitle, evaluationContent) like" +
						"? order by evaluationNo desc limit " +pageNumber*5+","+ pageNumber*5+6;
			}else if(searchType.equals("추천순")) {
				sql = "select * from evaluation where lectureDivide like ? and concat(lectureName ,professorName,evaluationTitle, evaluationContent) like" +
						"? order by likeCount desc limit " +pageNumber*5+","+ pageNumber*5+6;
						
			}
			 conn = DatabaseUtill.dbPool();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+lectureDivide+"%");
			pstmt.setString(2, "%"+search+"%");
			
			rs = pstmt.executeQuery(); //select 할 때
			evaluationlist = new ArrayList<Lecture>();
			while(rs.next()) {
				Lecture evaulation = new Lecture(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5),
							rs.getString(6),
							rs.getString(7),
							rs.getString(8),
							rs.getString(9),
							rs.getString(10),
							rs.getString(11),
							rs.getString(12),
							rs.getInt(13)		
						);
				evaluationlist.add(evaulation);
			}
			
			
		}catch(Exception e){
			System.out.println("오류가 발생했다리");
			e.printStackTrace();
			
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		}
		
		return evaluationlist; 
	}
	
	//추천수를 올리는 함수
	public int like(String evaluationNo) {
	String sql ="update evaluation set likeCount = likeCount +1 where evaluationNo=?";
			
			try {
				conn = DatabaseUtill.dbPool();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, evaluationNo);	
				
				
				return pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
			
			return -1;
			
		
		}
	
	
	public int delete(String evaluationNo) {
		String sql ="delete from evaluation where evaluationNo=?";
				
				try {
					conn = DatabaseUtill.dbPool();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, evaluationNo);	
					
					
					return pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
				
				return -1;
				
			
			}
		
	//유저 아이디 값 들고오기
	public String getUserId(String evaluationNo) {
		System.out.println("유저 아이디 값 가져오기");
		String sql = "select userId from evaluation where evaluationNo = ?";
		try {
			conn= DatabaseUtill.dbPool();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,evaluationNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
		
				return rs.getString(1);
			}else {
				System.out.println("해당 유저 정보가 없습니다");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
		
		return null;
	}
	

}
