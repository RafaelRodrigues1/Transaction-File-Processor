package com.desafio.fileprocessor.infrastructure.adapters;

import com.desafio.fileprocessor.domain.models.Transaction;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class DatabaseWriter implements ItemWriter<List<Transaction>> {

    @Override
    public void write(Chunk<? extends List<Transaction>> chunk) throws Exception {
        System.out.println("databaseWriter");
    }
}
