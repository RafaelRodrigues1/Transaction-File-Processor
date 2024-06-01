package com.desafio.fileprocessor.infrastructure.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionFileProcessor {

    @Bean
    public Job transactionFileProcessorJob(
            @Qualifier("transactionFileHandleStep") Step transactionFileHandleStep,
            @Qualifier("mailNotificationJobListener") JobExecutionListener jobExecutionListener,
            JobRepository jobRepository) {
        return new JobBuilder("transactionFileProcessorJob", jobRepository)
                .listener(jobExecutionListener)
                .start(transactionFileHandleStep)
                .build();
    }
}
