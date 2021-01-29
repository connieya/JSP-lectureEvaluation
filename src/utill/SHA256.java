package utill;

import java.security.MessageDigest;

public class SHA256 {
	
	public static String getSHA256(String input) {
		System.out.println("SHA256 호출");
		String result ="";
		byte[] b = input.getBytes();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(b);
			
			byte[] bResult = md.digest();
			for(byte data : bResult) {
				System.out.println(data+"");
			}
			System.out.println();
			StringBuffer sb = new StringBuffer();
			for(byte data : bResult) {
				sb.append(Integer.toString(data & 0xff,16));
			}
			result = sb.toString();
			System.out.println("result :" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
