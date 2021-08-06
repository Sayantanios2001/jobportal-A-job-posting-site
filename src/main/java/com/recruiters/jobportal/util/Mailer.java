package com.recruiters.jobportal.util;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.recruiters.jobportal.constants.ApplicationConstants;

public class Mailer {

	public static void send(String from,String password,String to,String sub,String msg){  
        //Get properties object    
        Properties props = new Properties();    
        props.put(ApplicationConstants.MAIL_SMTP_HOST, ApplicationConstants.MAIL_SMTP_HOST_VALUE);    
        props.put(ApplicationConstants.MAIL_SMTP_SOCKET_FACTORY_PORT, ApplicationConstants.MAIL_SMTP_SOCKET_FACTORY_PORT_VALUE);    
        props.put(ApplicationConstants.MAIL_SMTP_SOCKET_FACTORY_CLASS,    
                  ApplicationConstants.MAIL_SMTP_SOCKET_FACTORY_CLASS_VALUE);    
        props.put(ApplicationConstants.MAIL_SMTP_AUTH, ApplicationConstants.MAIL_SMTP_AUTH_VALUE);    
        props.put(ApplicationConstants.MAIL_SMTP_PORT, ApplicationConstants.MAIL_SMTP_PORT_VALUE);    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication(from,password);  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
         message.setSubject(sub);    
         message.setText(msg);    
         //send message  
         Transport.send(message);    
         System.out.println("message sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);}    
           
  }  
  
public static void main(String[] args) {    
   //from,password,to,subject,message  
//   Mailer.send("portaljob1000@gmail.com","Qwerty@2020","arnab.santra85@gmail.com","Activate you job portal account","How r u?");  
   //change from, password and to
	System.out.println("Random String: "+new RandomString(10).nextString());
}
}
