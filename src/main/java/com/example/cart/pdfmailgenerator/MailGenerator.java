package com.example.cart.pdfmailgenerator;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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

import com.example.cart.model.Invoice;
import com.example.cart.model.User;

public class MailGenerator {
	
	public boolean generateMail(User userCust,Invoice invoice) {
		// TODO Auto-generated method stub
		
		User userDTO = userCust;
		String to=userDTO.getEmail();//change accordingly  
		  final String user="shoppingcart367@gmail.com";//change accordingly  
		  final String password="";//change accordingly  
		   
		  //1) get the session object     
		  Properties properties = System.getProperties();  
		  properties.setProperty("mail.smtp.host", "smtp.gmail.com");  
//		  properties.put("mail.smtp.auth", "true"); 
		  
		  	properties.put("mail.smtp.auth", "true");  
		    properties.put("mail.smtp.port", "465");  
		    properties.put("mail.debug", "true");  
		    properties.put("mail.smtp.socketFactory.port", "465");  
		    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
		    properties.put("mail.smtp.socketFactory.fallback", "false");  
		  
		  Session session = Session.getInstance(properties,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication(user,password);  
		   }  
		  });  
		     
		  //2) compose message     
		  try{  
			
				
		    MimeMessage message = new MimeMessage(session);  
		    message.setFrom(new InternetAddress(user));  
		    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		    message.setSubject("Order Confirmed");  
		      
		    //3) create MimeBodyPart object and set your message text     
		    BodyPart messageBodyPart1 = new MimeBodyPart();  
//		    messageBodyPart1.setText("<h1>Hi your order has been confirmed, </h1>"); 
		    
		    messageBodyPart1.setContent("<h1>Hi your order has been confirmed, </h1><br><h2>Please find the invoice below</h2>", "text/html");
		    
		    
		      
		    //4) create new MimeBodyPart object and set DataHandler object to this object      
		    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
		    
		    MimeBodyPart messageBodyPart3 = new MimeBodyPart(); 
		  
		    
		    
		    String filename = "/Users/mahadevan/Documents/shopping-cart-project-pdf/Invoice"+invoice.getId()+".pdf";//change accordingly  
		    DataSource source = new FileDataSource(filename);  
		    messageBodyPart2.setDataHandler(new DataHandler(source));  
		    messageBodyPart2.setFileName("Invoice.pdf");  
		     
		     
		    //5) create Multipart object and add MimeBodyPart objects to this object      
		    Multipart multipart = new MimeMultipart();  
		    multipart.addBodyPart(messageBodyPart1);  
		    multipart.addBodyPart(messageBodyPart2);  
//		    multipart.addBodyPart(messageBodyPart3); 
		  
		    //6) set the multiplart object to the message object  
		    message.setContent(multipart );  
		     
		    //7) send message  
		    Transport.send(message);  
		   
		   System.out.println("message sent....");  
		   return true;
		   
		   }catch (Exception ex) {
			   
			   ex.printStackTrace();
			   return false;
			   
		   }  

		
	}
	
}
