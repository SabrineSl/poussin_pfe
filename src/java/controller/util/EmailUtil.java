/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author HP
 */
public class EmailUtil {

    public static boolean sendMail(String from, String password, String message, String to, String subject,String fileAttachment) throws MessagingException {
        System.out.println("hanii :D");
        String host = "smtp.gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.connectiontimeout", 30000);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage mimeMessage = new MimeMessage(session);
//
        
        try {
            mimeMessage.setFrom(new InternetAddress(from));
//            InternetAddress[] toAdress=new InternetAddress[to.length];
//            for(int i=0;i<toAdress.length;i++){
            System.out.println("hanii f emailUtil");
            mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(to));
//            }
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message, "UTF-8", "html");
            if(fileAttachment!=null){
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(message);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            
            DataSource source = new FileDataSource(fileAttachment);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileAttachment);
            multipart.addBodyPart(messageBodyPart);
            mimeMessage.setContent(multipart);
            }
            System.out.println("haa lcontent " + mimeMessage.getContent());
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException me) {
            me.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
