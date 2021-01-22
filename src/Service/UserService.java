package Service;

import user.User;
import user.UserDAO;

public class UserService {
	
	private UserDAO userDao;
	
	public UserService() {
		userDao = new UserDAO();
		// 다른 로직에서 계속 new 할 필요 없이 여기서 한번 선언
	}
	
	public int 회원가입(User user) {
		int result = userDao.join(user);
		
		return result;
	}
	
	public User 로그인(User user) {
		
		return null;
	}

}
