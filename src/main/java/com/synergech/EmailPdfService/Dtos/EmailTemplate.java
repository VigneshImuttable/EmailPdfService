package com.synergech.EmailPdfService.Dtos;

import lombok.Getter;


public class EmailTemplate {

    public static final String from = "bankinfo@accounts.com";
    public static final String body = """
            Dear Customer,
            
            Thank you for opting for the account statement using our banking app. 
            
            Please find the attached PDF file containing your account statement from the date you provided.
            
            Best regards!
            
            Warning: This is a machine-generated email; please do not reply.
            """;
    public static final String subject="Account Statement";
}
