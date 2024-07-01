package com.synergech.EmailPdfService.controllers;



import com.lowagie.text.DocumentException;
import com.synergech.EmailPdfService.Dtos.AccountStatementResponseDTO;
import com.synergech.EmailPdfService.services.HtmlBuilderService;
import com.synergech.EmailPdfService.services.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class EmailPdfController {


    private final PdfService pdfService;
    private final HtmlBuilderService htmlBuilderService;

    public EmailPdfController(PdfService pdfService, HtmlBuilderService htmlBuilderService) {
        this.pdfService = pdfService;
        this.htmlBuilderService = htmlBuilderService;
    }

    @GetMapping("/hello")
    public String buildHTML(Model model) {
        model.addAttribute("accountHolderName", "Vignesh");
        model.addAttribute("statementDate", "2024-06-27");
        model.addAttribute("accountNumber", "656785");
        model.addAttribute("accountType", "updating");
        return "hello";
    }

    @RequestMapping("/hello/pdf")
    @ResponseBody
    public void generatePdf(HttpServletResponse response) throws IOException, DocumentException {
        String accountHolderName = "Vignesh";
        String statementDate = "2024-06-27";
        String accountNumber = "656785";
        String accountType = "updating";

        // Generate HTML content using HtmlBuilderService
        String htmlContent = htmlBuilderService.buildHelloHtml(accountHolderName, statementDate, accountNumber, accountType);

        // Generate PDF from HTML content using PdfService
        byte[] pdfContent = pdfService.generatePdf(htmlContent);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=account-statement.pdf");
        response.getOutputStream().write(pdfContent);
    }

    @GetMapping("/index")
    public void generatePdfAndEmail(@RequestBody AccountStatementResponseDTO dto){




    }
}

