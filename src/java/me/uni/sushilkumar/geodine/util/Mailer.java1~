/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.uni.sushilkumar.geodine.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sushil
 */
public class Mailer {
    private Session session;
    private final String username;
    private final String password;
    public Mailer(String username,String password)
    {
        this.username=username;
        this.password=password;
        init(username,password);
    }
    
    public final void init(String username,String password)
    {
        Properties props=new Properties();
        props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "465");
        
        session=Session.getInstance(props, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Mailer.this.username,Mailer.this. password);
			}
        });
        
    }
    
    public boolean send(String recipient,String subject,String body) 
    {
        boolean status=false;
        try {
            
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setContent(body,"text/html");
            Transport.send(message);
            status=true;
            
        } catch (MessagingException ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public static void main(String[] args)
    {
        Mailer mailer=new Mailer("info.geodine@gmail.com","Sarvesh0@");
        String message="This is a demo mail from geodine.com send using the mail Daemon created by <strong><a href=\"http://sushilkumar.uni.me\">Sushil Kumar</a></strong> ";
        boolean status=mailer.send("0120sushil@gmail.com", "Demo Mail", message);
        if(status)
            System.out.println("Success...");
        else
            System.out.println("Failure!!!");
    }
    
}
