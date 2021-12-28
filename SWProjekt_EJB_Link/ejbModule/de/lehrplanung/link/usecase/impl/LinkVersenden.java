package de.lehrplanung.link.usecase.impl;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import de.lehrplanung.link.usecase.ILinkVersenden;
import de.lehrplanung.mitglieder.entity.FGMitgliedTO;
import de.lehrplanung.mitglieder.entity.FachgruppeTO;
import de.lehrplanung.mitglieder.entity.impl.FGMitglied;
import de.lehrplanung.planung.entity.SemesterTO;


@Stateless
public class LinkVersenden implements ILinkVersenden {

	@Override
	public void linkVersenden(SemesterTO semesterTO) {
		
		final String fromEmail = "lehrplanung.osnabrueck@gmail.com"; //requires valid gmail id
		final String password = "SoftwareProjekt2021"; // correct password for gmail id
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
		//create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		final String toEmail = "niklas.flaspoehler@hs-osnabrueck.de"; // can be any email id
		
		//FG von semester abfragen
		FachgruppeTO fachgruppeTO = semesterTO.getFachgruppeTO();
		
		//FGMitglieder laden
		List<FGMitgliedTO> fgMitgliederTO = fachgruppeTO.getFgMitglieder();
		
		//FGMitglieder EMails als zusammenhaengender String
		String eMailListe = "";
		for(FGMitgliedTO fgMitgliedTO : fgMitgliederTO) {
			eMailListe = eMailListe +","+fgMitgliedTO.geteMail();
		}
		
		//Link erstellen
		final String link = "http://localhost:8080/SWProjekt_JSF_Client/pages/public/VeranstaltungsEingabe.xhtml?semester="+semesterTO.getSemesterId();
		
		//EMail Inhalt erstellen
		final String subject = "Neue Planungsverwaltung";
		final String body = "Hallo liebe Kolleginnen und Kollegen,<br>"
				+ "unter nachfolgendem Link ist die Planungsverwaltung fuer das neue Semester moeglich.<br> "
				+ "Bitte tragt euch ein.<br>"
				+ "<a href="+link+">Hier klicken</href>";
		
		//EMail senden
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-Planungsverwaltung"));

	      msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

	      msg.setSubject(subject, "UTF-8");

	      //msg.setText(body, "UTF-8");
	      msg.setContent(body, "text/html");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(eMailListe, false));
	      System.out.println("Message is ready");
    	  Transport.send(msg);  

	      System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	}
	
}