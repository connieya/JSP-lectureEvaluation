package like;

public class LikeDTO {

	String userId;
	int evaluationNo;
	String userIP;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getEvaluationNo() {
		return evaluationNo;
	}
	public void setEvaluationNo(int evaluationNo) {
		this.evaluationNo = evaluationNo;
	}
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	public LikeDTO(String userId, int evaluationNo, String userIP) {
		super();
		this.userId = userId;
		this.evaluationNo = evaluationNo;
		this.userIP = userIP;
	}
	
	public LikeDTO() {
	}
	
	
}
