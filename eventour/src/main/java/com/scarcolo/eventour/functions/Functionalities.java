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

import com.scarcolo.eventour.model.booking.PaymentRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class Functionalities.
 *
 * @author stefa
 */
public class Functionalities {

	/**
	 * Checks if is valid email address.
	 *
	 * @param mail the mail
	 * @return true, if is valid email address
	 */
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

	/**
	 * Checks if is valid code.
	 *
	 * @param code the code
	 * @return true, if is valid code
	 */
	public static boolean isValidCode(String code) {
		//DA FARE MATCHES DEI CODICI
		/*if(code.matches(code))*/  return true;
		/*return false;*/
	}
	
	/**
	 * Convert to date.
	 *
	 * @param dateToConvert the date to convert
	 * @return the date
	 */
	public static Date convertToDate(LocalDateTime dateToConvert) {
		return java.util.Date
			      .from(dateToConvert.atZone(ZoneId.systemDefault())
			      .toInstant());
	}
	
	/**
	 * Convert to date.
	 *
	 * @param dateToConvert the date to convert
	 * @return the date
	 */
	public static Date convertToDate(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}
	
	/**
	 * Convert to local date.
	 *
	 * @param dateToConvert the date to convert
	 * @return the local date
	 */
	public static LocalDate convertToLocalDate(Date dateToConvert) {
	    return LocalDate.ofInstant(
	      dateToConvert.toInstant(), ZoneId.systemDefault());
	}
	
	/**
	 * Convert to local date time.
	 *
	 * @param dateToConvert the date to convert
	 * @return the local date time
	 */
	public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
	    return LocalDateTime.ofInstant(
	      dateToConvert.toInstant(), ZoneId.systemDefault());
	}
	
	/**
	 * Simil type.
	 *
	 * @param evTyp the ev typ
	 * @param usTyp the us typ
	 * @return true, if successful
	 */
	public static boolean similType(String evTyp, String usTyp) {
		System.out.println(evTyp+ " " +usTyp);
		String[] splittedE=evTyp.split("\\.");
		String[] splittedU=usTyp.split("\\.");
		if(!splittedE[0].equalsIgnoreCase(splittedU[0]))
			return false;
		if(splittedE.length>2) {
			if(!splittedE[1].equalsIgnoreCase(splittedU[1]))
				return false;
		}
		return true;
	}

	public static String checkerPayment(PaymentRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
