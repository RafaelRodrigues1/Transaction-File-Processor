package com.desafio.fileprocessor.domain.models;

import com.desafio.fileprocessor.domain.models.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(
        String transactionId,
        String accountNumber,
        LocalDateTime transactionDate,
        BigDecimal amount,
        TransactionType transactionType
) {
}
