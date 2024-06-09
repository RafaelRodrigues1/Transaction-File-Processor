package com.desafio.fileprocessor.infrastructure.adapters;

import com.desafio.fileprocessor.domain.models.Transaction;
import com.desafio.fileprocessor.infrastructure.entities.TransactionEntity;
import com.desafio.fileprocessor.infrastructure.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseWriter implements ItemWriter<List<Transaction>> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    @Override
    public void write(Chunk<? extends List<Transaction>> chunk) throws Exception {
        if(chunk.getItems().isEmpty()) return;
        chunk.getItems()
                .forEach(items -> items
                        .stream()
                        .map(transaction -> new TransactionEntity(
                                transaction.transactionId(),
                                transaction.accountNumber(),
                                transaction.transactionDate(),
                                transaction.amount(),
                                transaction.transactionType(),
                                transaction.transactionType().getOperationType(),
                                transaction.transactionType().getOperationType().getMovementType()
                        )
                ).forEach(transactionEntity -> this.transactionRepository.save(transactionEntity)));
    }
}
