package com.scarcolo.eventour.functions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import com.scarcolo.eventour.model.booking.Booking;
import com.scarcolo.eventour.model.event.Event;

public class Mail {
	static Properties prop;
	
	private static void sendMail(String destination, String obj, String title1, String title2, String msg) throws MessagingException, IOException
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
		
		InputStream inputStream = new FileInputStream("./src/main/java/com/scarcolo/eventour/functions/mailModel/textMail.txt");
		StringBuilder resultStringBuilder = new StringBuilder();
	    try (BufferedReader br
	      = new BufferedReader(new InputStreamReader(inputStream))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append("\n");
	        }
	    }
	    String textHTML=resultStringBuilder.toString();
		
	    textHTML=textHTML.replaceFirst("##title1##", title1).replaceFirst("##title2##", title2).replaceFirst("##text##",msg);
	    
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(textHTML, "text/html; charset=utf-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);
	}
	
	public static boolean sendDeleteEventMsg(String destination, Event event) throws IOException{
		String hh=("0"+event.getDataOra().getHour());
		String mm=("0"+event.getDataOra().getMinute());
		String msg = "Ciao, purtroppo dobbiamo informarti che l'evento \""+
				  event.getTitle()+
				  "\" che si svolge in data " +
				  event.getDataOra().getDayOfMonth() + "/"+event.getDataOra().getMonthValue()+"/"+event.getDataOra().getYear()+
				  " alle ore "+hh.substring(hh.length()-2)+":"+mm.substring(mm.length()-2)+
				  " e' stato cancellato dall'organizzatore. <br>";
				  if(event.getPrice()>0){msg+="Il rimborso del biglietto verrà effettuato nei prossimi giorni.<br><br>";}
				  msg+="Buona giornata<br><br> <p style=\"text-align:right\"><b>Il team evenTour</b></p>";

		try{
			sendMail(destination,"Cancellazione evento "+event.getTitle(),"Gentile cliente","SCUSACI!",msg);
			return true;
		} catch (MessagingException e) {
			return false;
		}
	}
	
	public static boolean sendBookingEventMsg(String destination, Event event, Booking book) throws IOException{
		String hh=("0"+event.getDataOra().getHour());
		String mm=("0"+event.getDataOra().getMinute());
		System.out.println(book.getId());
		String msg = "Ciao,<br><br> ti confermiamo la prenotazione all'evento \""+
				  event.getTitle()+
				  "\" che si svolge in data " +
				  event.getDataOra().getDayOfMonth() + "/"+event.getDataOra().getMonthValue()+"/"+event.getDataOra().getYear()+
				  " alle ore "+hh.substring(hh.length()-2)+":"+mm.substring(mm.length()-2)+
				  ". <br> La tua prenotazione e' riportata di seguito: <br>"+
				  "<ul><li><b>Nome evento: </b> "+event.getTitle()+"</li>"+
				  "<li><b>Numero posti prenotati: </b> "+book.getPrenotedSeat()+"</li>"+
				  "<li><b>Codice prenotazione: </b> "+book.getId()+"</li></ul>"+
				  "<center><img src=\"https://api.qrserver.com/v1/create-qr-code/?size=400x400&data="+book.getId()+"\" width=\"400px\" height=\"400px\"></center>"+
				  "<br>Se hai effettuato un pagamento, nei prossimi giorni troverai nella tua casella mail la ricevuta d'acquisto.<br><br>";
				 
				  msg+="Buona giornata<br><br> <p style=\"text-align:right\"><b>Il team evenTour</b></p>";

		try{
			sendMail(destination,"Prenotazione evento "+event.getTitle(),"Gentile cliente","PRENOTAZIONE EFFETTUATA!",msg);
			return true;
		} catch (MessagingException e) {
			return false;
		}
	}
	
	public static boolean sendBookingEventMsg(String destination, Event[] events) throws IOException{
		String msg = "Ciao,<br><br> ecco degli eventi che ti proponiamo questo mese:<br><br>";
		for(Event event : events) {
			String hh=("0"+event.getDataOra().getHour());
			String mm=("0"+event.getDataOra().getMinute());
		
				  msg+="<ul><li><b>Nome evento: </b> "+event.getTitle()+"</li>"+
				  "<li><b>Data: </b> "+event.getDataOra().getYear()+"</li>"+
				  "<li><b>Codice prenotazione: </b> "+book.getId()+"</li></ul>"+
				  "<center><img src=\"https://api.qrserver.com/v1/create-qr-code/?size=400x400&data="+book.getId()+"\" width=\"400px\" height=\"400px\"></center>"+
				  "<br>Se hai effettuato un pagamento, nei prossimi giorni troverai nella tua casella mail la ricevuta d'acquisto.<br><br>";
				 
				  msg+="Buona giornata<br><br> <p style=\"text-align:right\"><b>Il team evenTour</b></p>";

		try{
			sendMail(destination,"Prenotazione evento "+event.getTitle(),"Gentile cliente","PRENOTAZIONE EFFETTUATA!",msg);
			return true;
		} catch (MessagingException e) {
			return false;
		}
	}
	}
	
}
