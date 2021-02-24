package Service;

import lecture.Lecture;
import lecture.LectureDAO;
import like.LikeDAO;

public class LectureService {
	
	private LectureDAO dao;
	private LikeDAO likedao;
	
	public LectureService() {
		dao = new LectureDAO();
	}
	
	
	public int 강의평가등록(String userId , Lecture lecture) {
		
		
		return dao.강의등록(userId, lecture);
	}
	
	public int 추천누르기(String userId , String evaluationNo, String userIP) {
			System.out.println("추천누르기 서비스 :"+userId+evaluationNo+userIP);
			LikeDAO likeDAO = new LikeDAO();
			int result = likeDAO.like(userId, evaluationNo, userIP);
			if(result ==1 ) {
				return dao.like(evaluationNo);
			}else {
				return -2; // 추천 이미 누른글
			}
	}
	public int 강의삭제하기(String userId, String evaluationNo) {
		
		if(userId.equals(dao.getUserId(evaluationNo))){
			return dao.delete(evaluationNo);
			
		}else {
			return -2;
		}
	}

}
