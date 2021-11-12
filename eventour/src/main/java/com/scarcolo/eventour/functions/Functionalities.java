/**
 * 
 */
package com.scarcolo.eventour.functions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
	
	public static Date convertToDate(LocalDateTime dateToConvert) {
		return java.util.Date
			      .from(dateToConvert.atZone(ZoneId.systemDefault())
			      .toInstant());
	}
	public static Date convertToDate(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}
	
	public static LocalDate convertToLocalDate(Date dateToConvert) {
	    return LocalDate.ofInstant(
	      dateToConvert.toInstant(), ZoneId.systemDefault());
	}
	
	public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
	    return LocalDateTime.ofInstant(
	      dateToConvert.toInstant(), ZoneId.systemDefault());
	}
	
}
