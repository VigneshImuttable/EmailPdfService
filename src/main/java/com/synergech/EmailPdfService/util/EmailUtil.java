package com.synergech.EmailPdfService.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.util.Date;

public class EmailUtil {

    /**
     * Utility method to send simple HTML email
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static void sendEmail(Session session, String toEmail, String subject, String body, byte[] pdfBytes){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("account.info@bankofbarrota.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Create a multipart message for attachment
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Second part is the attachment
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(pdfBytes, "application/pdf");
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName("AccountStatement.pdf");
            multipart.addBodyPart(attachmentBodyPart);

            // Set the complete message parts
            msg.setContent(multipart);

            // Send the message
            Transport.send(msg);
            System.out.println("Email Sent Successfully with PDF attachment!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}