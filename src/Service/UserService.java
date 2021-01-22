package Service;

import user.User;
import user.UserDAO;

public class UserService {
	
	private UserDAO userDao;
	
	public UserService() {
		userDao = new UserDAO();
	}
	
	public int 회원가입(User user) {
		int result = userDao.join(user);
		
		return result;
	}
	
	public User 로그인(User user) {
		
		return null;
	}

}
