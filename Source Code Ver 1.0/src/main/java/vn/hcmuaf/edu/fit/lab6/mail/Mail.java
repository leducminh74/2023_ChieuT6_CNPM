package vn.hcmuaf.edu.fit.lab6.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

import java.util.Properties;

public class Mail {
    private static String username = "ducminh0573@gmail.com";
    private static String password = "xitnuudkuodrrzuu";

    public static boolean sendMail(String to, String subject, String content){
        Session session = connect();
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "Box Perfume"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(content,"text/html");
            Transport.send(message);
            return true;

        }catch (UnsupportedEncodingException | MessagingException e){
            e.printStackTrace();
            return  false;
        }
    }

    private static Session connect(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return  new PasswordAuthentication(username,password);
            }
        });

    }

    public static void main(String[] args) {
        System.out.println(sendMail("ducminh0573@gmail.com","Test", "123"));
    }
}
