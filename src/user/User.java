package user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private int userNo;
	private String userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userEmailHash;
	private String userAddr;
	private Boolean userEmailChecked;
	
	
	
	
	
	
	
	
	
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
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
	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userName=" + userName + ", userPassword="
				+ userPassword + ", userEmail=" + userEmail + ", userEmailHash=" + userEmailHash + ", userAddr="
				+ userAddr + ", userEmailChecked=" + userEmailChecked + "]";
	}
	
	
	

}
