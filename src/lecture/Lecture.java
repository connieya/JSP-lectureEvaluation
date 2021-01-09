package lecture;

public class Lecture {
	
	private int evaluationNo;
	private String userId;
	private String lectureName; // 강의명
	private String professorName; // 교수이름
	private int lectureYear; //  수강연도 (2013 ~ 2021)
	private String semesterDivide; // 수강학기 (1학기,여름,2학기,겨울)
	private String lectureDivide; // 강의구분( 전공, 교양,기타)
	private String evaluationTitle; //강의 평가 제목
	private String evaluationContent; // 강의 평가 내용
	private String totalscore; // 총점
	private String joyscore; // 재미 점수
	private String lecturescore; // 강의 점수
	private int likeCount;
	
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getEvaluationNo() {
		return evaluationNo;
	}
	public void setEvaluationNo(int evaluationNo) {
		this.evaluationNo = evaluationNo;
	}
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	public int getLectureYear() {
		return lectureYear;
	}
	public void setLectureYear(int lectureYear) {
		this.lectureYear = lectureYear;
	}
	public String getSemesterDivide() {
		return semesterDivide;
	}
	public void setSemesterDivide(String semesterDivide) {
		this.semesterDivide = semesterDivide;
	}
	public String getLectureDivide() {
		return lectureDivide;
	}
	public void setLectureDivide(String lectureDivide) {
		this.lectureDivide = lectureDivide;
	}
	public String getEvaluationTitle() {
		return evaluationTitle;
	}
	public void setEvaluationTitle(String evaluationTitle) {
		this.evaluationTitle = evaluationTitle;
	}
	public String getEvaluationContent() {
		return evaluationContent;
	}
	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}
	public String getTotalscore() {
		return totalscore;
	}
	public void setTotalscore(String totalscore) {
		this.totalscore = totalscore;
	}
	public String getJoyscore() {
		return joyscore;
	}
	public void setJoyscore(String joyscore) {
		this.joyscore = joyscore;
	}
	public String getLecturescore() {
		return lecturescore;
	}
	public void setLecturescore(String lecturescore) {
		this.lecturescore = lecturescore;
	}
	@Override
	public String toString() {
		return "Lecture [evaluationNo=" + evaluationNo + ", lectureName=" + lectureName + ", professorName="
				+ professorName + ", lectureYear=" + lectureYear + ", semesterDivide=" + semesterDivide
				+ ", lectureDivde=" + lectureDivide + ", evaluationTitle=" + evaluationTitle + ", evaluationContent="
				+ evaluationContent + ", totalscore=" + totalscore + ", joyscore=" + joyscore + ", lecturescore="
				+ lecturescore + "]";
	}
	
	
	
	

}
