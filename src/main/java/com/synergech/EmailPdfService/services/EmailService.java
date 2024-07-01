package com.synergech.EmailPdfService.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synergech.EmailPdfService.util.EmailUtil;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class EmailService {

    private ObjectMapper objectMapper;

    public EmailService(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }


    public void handleSendEmailEvent(String message) {

//         event;
//        try {
//            event = objectMapper.readValue(message, SendEmailEventDto.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        String to = event.getTo();
//        String body = event.getBody();
//        String from = event.getFrom();
//        String subject = event.getSubject();

        //Cofiguring the email
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vigneshairag@gmail.com", "ylbdueaceqrokjtl");
            }
        };
        Session session = Session.getInstance(props, auth);

//        EmailUtil.sendEmail(session, to, subject, body);


    }
}
