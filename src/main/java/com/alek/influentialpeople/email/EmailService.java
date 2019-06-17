package com.alek.influentialpeople.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class EmailService {

	String hardcodedPathToCredentials = "C:\\Users\\Aleks\\Desktop\\credentials.txt";
	String email;
	String password;

	public void sendMail(String activationParameter) throws IOException {
		// Setting up configurations for the email connection to the Google SMTP server
		// using TLS
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(hardcodedPathToCredentials)));
		email=bufferedReader.readLine();
		password=bufferedReader.readLine();
		Properties props = new Properties();
		props.put("mail.smtp.host", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		// Establishing a session with required user details
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email,password);
			}
		});
		try {
			// Creating a Message object to set the email content
			MimeMessage msg = new MimeMessage(session);
			// Storing the comma seperated values to email addresses
			String to = "olek50000@o2.pl";
			/*
			 * Parsing the String with defualt delimiter as a comma by marking the boolean
			 * as true and storing the email addresses in an array of InternetAddress
			 * objects
			 */
			InternetAddress[] address = InternetAddress.parse(to, true);
			// Setting the recepients from the address variable
			msg.setRecipients(Message.RecipientType.TO, address);
			String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
			msg.setSubject("Sample Mail : " + timeStamp);
			msg.setSentDate(new Date());
			msg.setContent("<h1>Hello</h1><br><br><a href=\"http://localhost:8080/user/sign-up?user_id="+activationParameter+">Activation link</a>", "text/html; charset=utf-8");
			
			msg.setText("Sampel System Generated mail");
			msg.setHeader("XPriority", "1");
			Transport.send(msg);
			System.out.println("Mail has been sent successfully");
		} catch (MessagingException mex) {
			System.out.println("Unable to send an email" + mex);
		}
	}
}
