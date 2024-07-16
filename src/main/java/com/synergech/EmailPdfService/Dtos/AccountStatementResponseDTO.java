package com.synergech.EmailPdfService.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.synergech.EmailPdfService.models.TransactionFilterResponseDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatementResponseDTO {
    private TransactionFilterResponseDTO[] transactions;
    private String accountNumber;
    private String accountHolderName;
    private String accountType;
    private String branchName;
    private String ifscCode;
    private String email;
    private String fromDate;
    private String toDate;
}
