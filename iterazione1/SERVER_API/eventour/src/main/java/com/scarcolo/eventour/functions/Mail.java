package com.scarcolo.eventour.functions;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.scarcolo.eventour.model.event.Event;

// TODO: Auto-generated Javadoc
/**
 * The Class Mail.
 */
public class Mail {
	
	/** The properties. */
	static Properties prop;
	
	/**
	 * The generic send mail function.
	 *
	 * @param destination the destination address
	 * @param obj the object of a password
	 * @param msg the message to send
	 * @throws MessagingException the messaging exception
	 */
	private static void sendMail(String destination, String obj, String msg) throws MessagingException
	{
		prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.mailtrap.io");
		prop.put("mail.smtp.port", "25");
		prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
		Session session = Session.getInstance(prop, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("88037fa3d5088f", "45768831cff825");
		    }
		});
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("eventourcs@gmail.com"));
		message.setRecipients(
		  Message.RecipientType.TO, InternetAddress.parse(destination));
		message.setSubject(obj);

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);
	}
	
	/**
	 * Send delete event mail.
	 *
	 * @param destination the destination address
	 * @param event the event data
	 * @return true, if successful
	 */
	public static boolean sendDeleteEventMsg(String destination, Event event){
		String hh=("0"+event.getDataOra().getHour());
		String mm=("0"+event.getDataOra().getMinute());
		String msg = "Ciao, purtroppo dobbiamo informarti che l'evento \""+
				  event.getTitle()+
				  "\" che si svolge in data " +
				  event.getDataOra().getDayOfMonth() + "/"+event.getDataOra().getMonthValue()+"/"+event.getDataOra().getYear()+
				  " alle ore "+hh.substring(hh.length()-2)+":"+mm.substring(mm.length()-2)+
				  " e' stato cancellato dall'organizzatore. <br>";
				  if(event.getPrice()>0){msg+="Il rimborso del biglietto verrà effettuato nei prossimi giorni.<br><br>";}
				  msg+="Buona giornata<br><br> <b>Il team evenTour<b>";
		
		try{
			sendMail(destination,"Cancellazione evento "+event.getTitle(),msg);
			return true;
		} catch (MessagingException e) {
			return false;
		}
	}
	
}
