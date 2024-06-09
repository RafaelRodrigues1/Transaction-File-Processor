package com.desafio.fileprocessor.infrastructure.repositories;

import com.desafio.fileprocessor.infrastructure.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
}
