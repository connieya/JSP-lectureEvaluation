package user;

public class User {
	
	private int userNo;
	private String userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userEmailHash;
	private Boolean userEmailChecked;
	
	
	
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserEmailHash() {
		return userEmailHash;
	}
	public void setUserEmailHash(String userEmailHash) {
		this.userEmailHash = userEmailHash;
	}
	public Boolean getUserEmailChecked() {
		return userEmailChecked;
	}
	public void setUserEmailChecked(Boolean userEmailChecked) {
		this.userEmailChecked = userEmailChecked;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(int userNo, String userId, String userName, String userPassword, String userEmail, String userEmailHash,
			Boolean userEmailChecked) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userEmailHash = userEmailHash;
		this.userEmailChecked = userEmailChecked;
	}
	
	
	

}
