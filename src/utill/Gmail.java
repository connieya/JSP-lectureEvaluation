package utill;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator {
	
	protected PasswordAuthentication getPasswordAuthenticaiton() {
		
		return new PasswordAuthentication("gunny6026@gmail.com","szszs2019gdx^^");
	}

	
}
