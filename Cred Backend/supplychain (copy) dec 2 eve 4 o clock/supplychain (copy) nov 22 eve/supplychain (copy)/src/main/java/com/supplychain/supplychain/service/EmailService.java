package com.supplychain.supplychain.service;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;




@Service
public class EmailService {


    @Autowired
private JavaMailSender javaMailSender;
@Value("${spring.mail.username}")private String sender;


    public boolean sendEmail(String subject,String message,String to)
    {
    boolean foo=false;
    String senderEmail="testingirenemprince@gmail.com";
    String senderPassword="hnrtwurqkeikqvci";
    Properties properties=new Properties();
    properties.put("mail.smtp.auth","true");

 properties.put("mail.smtp.starttls.enable","true");

     properties.put("mail.smtp.host","smtp.gmail.com");
    
     properties.put("mail.smtp.port","587");
     Session session = Session.getInstance(properties, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication(){

                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {

            MimeMessage msg = new MimeMessage(session); 

            msg.setFrom(new InternetAddress(senderEmail));

            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 

            msg.setSubject(subject); 
            msg.setText(message); 
            Transport.send(msg); 

            foo = true; // Set the "foo" variable to true after successfully sending emails

        }catch(Exception e){

        }

        return foo; // and return foo variable
    }


    public void sendPdf(
        String subject,
        String attachment,
        String body,
        String toEmail) {
            String sendername="Supplychain management";
            try {
                MimeMessage mailMessage =javaMailSender.createMimeMessage();
                MimeMessageHelper helper= new MimeMessageHelper(mailMessage);
                MimeBodyPart messageBodyPart=new MimeBodyPart();
                helper.setFrom(sender,"Courier management");
                helper.setTo(toEmail);
                helper.setSubject(subject);
                
                BodyPart messageBodyPart1 = new MimeBodyPart();  
                messageBodyPart1.setText(body);  
                System.out.println(toEmail+"=======================================++++++++++++=++++++++++++++++++++++++");
                  
                //4) create new MimeBodyPart object and set DataHandler object to this object      
                MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
              
                DataSource source = new FileDataSource("/home/clindan/Downloads/"+attachment);  
    
                messageBodyPart2.setDataHandler(new DataHandler(source));  
                messageBodyPart2.setFileName(attachment);  
                
                Multipart multipart = new MimeMultipart();  
                multipart.addBodyPart(messageBodyPart1);  
                multipart.addBodyPart(messageBodyPart2);  
                 
                mailMessage.setContent(multipart ); 
                 
                javaMailSender.send(mailMessage);
                System.out.println("Mail sent successfully");
                
            
        }catch(Exception e){

        }
        

    }
}


    


