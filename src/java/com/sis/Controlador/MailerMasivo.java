package com.sis.Controlador;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.inject.Inject;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailerMasivo {

public static void send(String para,String sujeto,String mensaje) throws UnsupportedEncodingException{
final String user = "rromero.rsrg@gmail.com"; //cambiará en consecuencia al servidor utilizado
final String pass= "Steven182.44";
String nuevoMensaje="<h1 style=\"font-size: 20px; color:#009ed6; font-weight: bold; text-transform: uppercase ; \">SIS" + "</h1>" + "<img src='https://http2.mlstatic.com/impresora-multifuncion-ricoh-D_NQ_NP_982705-MLA20871487974_092016-F.jpg'/ style=\"float: left; height: 80px; width: 100px;\"><p>" +mensaje+ "<br>\n"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "</p> \n"
                    + "<br>\n"
                    + "<p style=\"color:#009ed6;font-weight: bold;\" > Gracias por formar parte de nuestro sistema de información. </p> ";

//1st paso) Obtener el objeto de sesión

Properties props = new Properties();
props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");



Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, pass);
    }
});



//2nd paso)compose message
try {
    //Archivos adjuntos
BodyPart texto=new MimeBodyPart();
texto.setContent(nuevoMensaje,"text/html");
/*BodyPart adjunto = new MimeBodyPart();
adjunto.setDataHandler(new DataHandler(new FileDataSource("d:/cartagena.txt")));
adjunto.setFileName("cartagena.txt");*/
    

MimeMultipart multiparte=new MimeMultipart();
multiparte.addBodyPart(texto);
//multiparte.addBodyPart(adjunto);
 MimeMessage message = new MimeMessage(session);
 message.setFrom(new InternetAddress(user,"SIS"));
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(para));
 message.setSubject(sujeto);
 message.setContent(multiparte,"text/html; charset=utf-8");

 //3rd paso)send message
 Transport.send(message);

 System.out.println("Done");

 } catch (MessagingException e) {
	throw new RuntimeException(e);
 }
	
}
}
