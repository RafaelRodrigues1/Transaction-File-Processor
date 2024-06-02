package com.desafio.fileprocessor.infrastructure.steps;

import com.desafio.fileprocessor.domain.models.Transaction;
import com.desafio.fileprocessor.infrastructure.adapters.DatabaseWriter;
import com.desafio.fileprocessor.infrastructure.adapters.S3FileReader;
import com.desafio.fileprocessor.infrastructure.adapters.TransactionConverter;
import com.desafio.fileprocessor.infrastructure.dto.TransactionFileDto;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class TransactionFileHandle {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public Step transactionFileHandleStep(
            JobRepository jobRepository,
            ItemReader<List<TransactionFileDto>> transactionFileReader,
            ItemProcessor<List<TransactionFileDto>, List<Transaction>> transactionConverter,
            ItemWriter<List<Transaction>> databaseWriter) {
        return new StepBuilder("transactionFileHandleStep", jobRepository)
                .<List<TransactionFileDto>, List<Transaction>>chunk(1, platformTransactionManager)
                .reader(transactionFileReader)
                .processor(transactionConverter)
                .writer(databaseWriter)
                .build();
    }

    @Bean
    public S3FileReader transactionFileReader() {
        return new S3FileReader();
    }

    @Bean
    public TransactionConverter transactionConverter() {
        return new TransactionConverter();
    }

    @Bean
    public DatabaseWriter databaseWriter() {
        return new DatabaseWriter();
    }
}
