package com.synergech.EmailPdfService.services;

import com.synergech.EmailPdfService.Dtos.AccountStatementResponseDTO;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

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


        return null;
    }
}


