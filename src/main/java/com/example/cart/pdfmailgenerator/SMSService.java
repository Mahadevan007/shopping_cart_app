package com.example.cart.pdfmailgenerator;

import com.example.cart.model.Invoice;
import com.example.cart.model.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSService {
	
	private static final String ACCOUNT_SID = "AC9acacc69ddcbd2abb55be92a6acf4845";
	private static final String AUTH_TOKEN = "ba1845c6b1e174a7e15edb3b277ae566";
	
	public boolean sendSMS(User user,Invoice invoice) {
		// TODO Auto-generated method stub  
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
		try {
			
			Message message = Message.creator(new PhoneNumber("+91"+user.getPhonenumber()+""), new PhoneNumber("+16094546716"), "Hi "+user.getUsername()+", Your order has been confirmed. \nYour invoice id is "+invoice.getId()+".\n Your total price to be paid is "+invoice.getTotal()).create();
			
			System.out.println(message.getSid());
			System.out.println("Invoice Message sent");
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}