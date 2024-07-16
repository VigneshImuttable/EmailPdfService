package com.synergech.EmailPdfService.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.transaction.TransactionStatus;

import java.util.Date;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFilterResponseDTO {

    private Long transId;

    private Long fromAccountNumber;

    private Long toAccountId;

    private String transDateTime;

    private double amount;

    private String status;

}
