/**
 * 
 */
package com.scarcolo.eventour.functions;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.CharacterData;
import org.passay.PasswordGenerator;

import com.scarcolo.eventour.model.event.Event;
import com.scarcolo.eventour.model.event.EventPlus;

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
		if(code.startsWith("TI-") && code.split("-").length==3) return true;
		else return false;
		//DA FARE MATCHES DEI CODICI CON REGEX
		/*if(code.matches(code))*/  
		/*return false;*/
	}
	
	/**
	 * Convert to date from LocalDateTime.
	 *
	 * @param dateToConvert the date to convert (type LocalDateTime)
	 * @return the date in Date type
	 */
	public static Date convertToDate(LocalDateTime dateToConvert) {
		return java.util.Date
			      .from(dateToConvert.atZone(ZoneId.systemDefault())
			      .toInstant());
	}
	
	/**
	 * Convert to date from LocalDate.
	 *
	 * @param dateToConvert the date to convert (type LocalDate)
	 * @return the date in Date type
	 */
	public static Date convertToDate(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}
	
	/**
	 * Convert to LocalDate from Date.
	 *
	 * @param dateToConvert the date to convert (type Date)
	 * @return the local date in LocalDate
	 */
	public static LocalDate convertToLocalDate(Date dateToConvert) {
	    return LocalDate.ofInstant(
	      dateToConvert.toInstant(), ZoneId.systemDefault());
	}
	
	/**
	 * Convert to LocalDate.
	 *
	 * @param dateToConvert the date to convert (type Date)
	 * @return the local date time in LocalDateTime
	 */
	public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
	    return LocalDateTime.ofInstant(
	      dateToConvert.toInstant(), ZoneId.systemDefault());
	}
	
	/**
	 * Simil type method.
	 *
	 * @param evTyp the first type
	 * @param usTyp the second type to compare
	 * @return true, if successful
	 */
	public static boolean similType(String evTyp, String usTyp) {
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
	
	/**
	 * Order by datetime.
	 *
	 * @param listToOrder the list to order
	 * @return the list
	 */
	public static List<Event> orderByData(List<Event> listToOrder){
		for(int i=0;i<listToOrder.size();i++) {
			for(int j=i+1;j<listToOrder.size();j++) {
				if(listToOrder.get(i).getDataOra().isAfter(listToOrder.get(j).getDataOra())) {
					Event tmp=listToOrder.get(j);
					listToOrder.set(j, listToOrder.get(i));
					listToOrder.set(i, tmp);
				}
			}
		}
		return listToOrder;
		
	}

	/**
	 * Gets the md 5 hash.
	 *
	 * @param input the input string
	 * @return the md 5
	 * @throws NoSuchAlgorithmException md5 library non present
	 */
	public static String getMd5(String input) throws NoSuchAlgorithmException
    {
        try {
  
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
  
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
  
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } 
  
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw e;
        }
    }
	
	
	/**
	 * Generate passay password.
	 *
	 * @param lenght the lenght
	 * @return the string
	 */
	public static String generatePassayPassword(int lenght) {
	    PasswordGenerator gen = new PasswordGenerator();
	    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
	    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
	    lowerCaseRule.setNumberOfCharacters(2);

	    EnglishCharacterData upperCaseChars = EnglishCharacterData.UpperCase;
	    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
	    upperCaseRule.setNumberOfCharacters(2);

	    EnglishCharacterData digitChars = EnglishCharacterData.Digit;
	    CharacterRule digitRule = new CharacterRule(digitChars);
	    digitRule.setNumberOfCharacters(2);

	    CharacterData date=new CharacterData() {

			@Override
			public String getErrorCode() {
				return "";
			}

			@Override
			public String getCharacters() {
				return "!@#$%^&*()_+";
			}
	    	
	    };
	    CharacterRule splCharRule = new CharacterRule(date);
	    splCharRule.setNumberOfCharacters(2);

	    String password = gen.generatePassword(lenght, splCharRule, lowerCaseRule, 
	      upperCaseRule, digitRule);
	    return password;
	}

	/**
	 * Number of event with datetime after now.
	 *
	 * @param eventPlus the event plus data
	 * @return how many events have date after now
	 */
	public static Integer dataFutura(EventPlus[] eventPlus) {
		int i=0;
		for(EventPlus e : eventPlus) {
			if(e.getDataOra().isAfter(LocalDateTime.now())) i++;
		}
		return i;
	}
	
	/**
	 * Distance of two coordinates.
	 *
	 * @param lat1 the latitude 1
	 * @param lng1 the longitude 1
	 * @param lat2 the latitude 2
	 * @param lng2 the longitude 2
	 * @return the air distance
	 */
	public static Double distance(Double lat1, Double lng1, Double lat2, Double lng2) {
		Double[] pointA= {lat1*Math.PI/180,lng1*Math.PI/180};
		Double[] pointB= {lat2*Math.PI/180,lng2*Math.PI/180};
		return 6372.795477598 * Math.acos(Math.sin(pointA[0]) * Math.sin(pointB[0]) + Math.cos(pointA[0]) * Math.cos(pointB[0]) * Math.cos(pointA[1]-pointB[1]));
	}
}
