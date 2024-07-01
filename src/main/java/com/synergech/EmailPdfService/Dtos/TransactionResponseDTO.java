package com.synergech.EmailPdfService.Dtos;

import com.synergech.EmailPdfService.models.TransactionStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionResponseDTO {

    private long transId;

    private long fromAccountNumber;

    private long toAccountId;

    private Date transDateTime;

    private double amount;

    private TransactionStatus status;

}
