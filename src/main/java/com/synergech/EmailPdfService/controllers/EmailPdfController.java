package com.synergech.EmailPdfService.controllers;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import com.synergech.EmailPdfService.Dtos.AccountStatementResponseDTO;
import com.synergech.EmailPdfService.services.EmailService;
import com.synergech.EmailPdfService.services.HtmlBuilderService;
import com.synergech.EmailPdfService.services.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@Slf4j
public class EmailPdfController {


    private final PdfService pdfService;
    private final HtmlBuilderService htmlBuilderService;
    private final EmailService emailService;

    private final ObjectMapper objectMapper;

    public EmailPdfController(PdfService pdfService, HtmlBuilderService htmlBuilderService,EmailService emailService,ObjectMapper objectMapper) {
        this.pdfService = pdfService;
        this.htmlBuilderService = htmlBuilderService;
        this.emailService=emailService;
        this.objectMapper=objectMapper;
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

    @RequestMapping("/index/pdf")
    @ResponseBody
    public void generatePdfAndEmail(@RequestBody AccountStatementResponseDTO dto, HttpServletResponse response) throws IOException, DocumentException {
        String htmlContent = htmlBuilderService.buildHelloHtml(dto);
        byte[] pdfContent = pdfService.generatePdf(htmlContent);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=account-statement.pdf");
        response.getOutputStream().write(pdfContent);
    }

    @KafkaListener(topics = "emailpdf", groupId = "AccountStatement")
    public void generatePdfAndEmailThroughKafka(String message) throws DocumentException, IOException {
        AccountStatementResponseDTO dto=null;
        try {
             dto = objectMapper.readValue(message, AccountStatementResponseDTO.class);
            // Further processing with the DTO
        } catch (IllegalArgumentException e) {
            // Handle the exception appropriately
            e.printStackTrace(); // Or log the exception or throw a custom exception
        }
        log.info("deserialized");
        log.info(dto.toString());
        String htmlContent = htmlBuilderService.buildHelloHtml(dto);
        byte[] pdfContent = pdfService.generatePdf(htmlContent);
        emailService.handleSendEmailEvent(dto.getEmail(),pdfContent);
    }
}

