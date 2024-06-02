package com.desafio.fileprocessor.infrastructure.adapters;

import com.desafio.fileprocessor.domain.models.Transaction;
import com.desafio.fileprocessor.infrastructure.dto.TransactionFileDto;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

public class TransactionConverter implements ItemProcessor<List<TransactionFileDto>, List<Transaction>> {

    @Override
    public List<Transaction> process(List<TransactionFileDto> itemList) throws Exception {
        System.out.println("transactionConverter");
        return null;
    }
}
