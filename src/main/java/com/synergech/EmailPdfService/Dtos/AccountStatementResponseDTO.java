package com.synergech.EmailPdfService.Dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AccountStatementResponseDTO {

    private List<TransactionResponseDTO> transactionResponseDTOList;

    private String accountNumber;

    private String accountHolderName;

    private String accountType;

    private String branchName;

    private String ifscCode;

    private String address;

    private Date fromDate;

    private Date toDate;

}
