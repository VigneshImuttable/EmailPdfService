package com.synergech.EmailPdfService.services;

import com.synergech.EmailPdfService.Dtos.AccountStatementResponseDTO;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class HtmlBuilderService {
    private final SpringTemplateEngine templateEngine;

    public HtmlBuilderService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildHelloHtml(String accountHolderName, String statementDate, String accountNumber, String accountType) {
        Context context = new Context();
        context.setVariable("accountHolderName", accountHolderName);
        context.setVariable("statementDate", statementDate);
        context.setVariable("accountNumber", accountNumber);
        context.setVariable("accountType", accountType);
        return templateEngine.process("hello", context);
    }

    public String buildHelloHtml(AccountStatementResponseDTO dto) {
        Context context = new Context();
        context.setVariable("accountHolderName", dto.getAccountHolderName());
        context.setVariable("accountNumber", dto.getAccountNumber());
        context.setVariable("accountType", dto.getAccountType());
        context.setVariable("branchName", dto.getBranchName());
        context.setVariable("ifscCode", dto.getIfscCode());
        context.setVariable("customerAddress", dto.getEmail());
        context.setVariable("statementDate", new Date()); // or any specific date you want to set
        context.setVariable("transactions", dto.getTransactions());

        // If you have date ranges fromDate and toDate
        context.setVariable("fromDate", formatDateString(dto.getFromDate()));
        context.setVariable("toDate", formatDateString(dto.getToDate()));

        return templateEngine.process("statement", context);
    }

    private String formatDateString(String dateTime) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME);
        return localDateTime.toLocalDate().toString();
    }


}


