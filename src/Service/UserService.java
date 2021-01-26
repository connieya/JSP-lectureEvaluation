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
	
	public int 로그인(User user) {
		
		int result  = userDao.login(user);
		System.out.println("로그인 결과 :"+result);
		return result;
	}
	
	public String 아이디중복체크(String userId) {
		
		String result = userDao.idCheck(userId);
		System.out.println("result : " +result  );
		if(result.equals("ok")) {
			System.out.println("아이디 중복");
			
			return "ok";
			
		}
		
		return "no";
		
	}
	
	public int 회원수정(String userId, String userName, String userPassword, String userAddr) {
		
		return userDao.회원수정(userId, userName, userPassword, userAddr);
		
	}
}
