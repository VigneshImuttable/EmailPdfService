package com.synergech.EmailPdfService.models;

import lombok.Data;

import java.util.Date;


@Data
public class TransactionFilterResponseDTO {

    private Long transId;

    private Long fromAccountNumber;

    private Long toAccountId;

    private String transDateTime;

    private Double amount;

    private String status;

}
