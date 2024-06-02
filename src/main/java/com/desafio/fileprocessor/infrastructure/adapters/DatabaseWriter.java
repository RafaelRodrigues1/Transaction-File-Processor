package com.desafio.fileprocessor.infrastructure.adapters;

import com.desafio.fileprocessor.domain.models.Transaction;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class DatabaseWriter implements ItemWriter<Transaction> {

    @Override
    public void write(Chunk<? extends Transaction> chunk) throws Exception {
        System.out.println("databaseWriter");
    }
}
