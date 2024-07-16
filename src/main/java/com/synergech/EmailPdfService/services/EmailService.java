package com.synergech.EmailPdfService.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synergech.EmailPdfService.Dtos.AccountStatementResponseDTO;
import com.synergech.EmailPdfService.Dtos.EmailTemplate;
import com.synergech.EmailPdfService.util.EmailUtil;
import org.apache.kafka.common.utils.Bytes;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class EmailService {


    public void handleSendEmailEvent(String toEmail, byte[] pdfBytes) {

        String body = EmailTemplate.body;
        String from = EmailTemplate.from;
        String subject = EmailTemplate.subject;

        //Cofiguring the email
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vigneshairag@gmail.com", "ylbdueaceqrokjtl");
            }
        };
        Session session = Session.getInstance(props, auth);
        EmailUtil.sendEmail(session, toEmail, subject, body,pdfBytes);
    }
}
