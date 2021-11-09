/**
 * 
 */
package functions;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author stefa
 *
 */
public class Functionalities {

	public static boolean isValidEmailAddress(String mail) {
		 boolean result = true;
		 try {
		     InternetAddress emailAddr = new InternetAddress(mail);
		     emailAddr.validate();
		 } catch (AddressException ex) {
		      result = false;
		 }
		 return result;
	}

	public static boolean isValidCode(String code) {
		//DA FARE MATCHES DEI CODICI
		/*if(code.matches(code))*/  return true;
		/*return false;*/
	}
	
}
