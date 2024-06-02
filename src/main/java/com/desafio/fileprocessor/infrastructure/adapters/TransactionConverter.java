package com.desafio.fileprocessor.infrastructure.adapters;

import com.desafio.fileprocessor.domain.models.Transaction;
import com.desafio.fileprocessor.domain.models.enums.TransactionType;
import com.desafio.fileprocessor.infrastructure.dto.TransactionFileDto;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionConverter implements ItemProcessor<List<TransactionFileDto>, List<Transaction>> {

    @Override
    public List<Transaction> process(List<TransactionFileDto> transactionFileDtoList) throws Exception {
         return transactionFileDtoList.stream().map(transactionFileDto -> {
            LocalDateTime transactionDate = LocalDateTime
                    .parse(transactionFileDto.transactionDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            BigDecimal transactionAmount = new BigDecimal(transactionFileDto.amount().replace(",", "."));
            TransactionType transactionType = TransactionType.valueOf(transactionFileDto.transactionType().toUpperCase());
            return new Transaction(
                    transactionFileDto.transactionId(),
                    transactionFileDto.accountNumber(),
                    transactionDate,
                    transactionAmount,
                    transactionType
            );
        }).toList();
    }
}
