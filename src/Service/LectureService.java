package Service;

import lecture.Lecture;
import lecture.LectureDAO;

public class LectureService {
	
	private LectureDAO dao;
	
	public LectureService() {
		dao = new LectureDAO();
	}
	
	
	public int 강의평가등록(String userId , Lecture lecture) {
		
		
		return dao.강의등록(userId, lecture);
	}

}
