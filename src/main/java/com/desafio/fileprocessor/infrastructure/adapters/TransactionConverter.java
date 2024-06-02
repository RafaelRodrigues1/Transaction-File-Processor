package com.desafio.fileprocessor.infrastructure.adapters;

import com.desafio.fileprocessor.domain.models.Transaction;
import com.desafio.fileprocessor.infrastructure.dto.TransactionFileDto;
import org.springframework.batch.item.ItemProcessor;

public class TransactionConverter implements ItemProcessor<TransactionFileDto, Transaction> {

    @Override
    public Transaction process(TransactionFileDto item) throws Exception {
        System.out.println("transactionConverter");
        return null;
    }
}
