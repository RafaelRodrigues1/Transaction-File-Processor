package com.desafio.fileprocessor.infrastructure.dto;

public record TransactionFileDto (
        String transactionId,
        String accountNumber,
        String transactionDate,
        String amount,
        String transactionType
) {
}
