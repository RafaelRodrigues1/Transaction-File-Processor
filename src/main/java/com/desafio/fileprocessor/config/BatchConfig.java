package com.desafio.fileprocessor.config;

import com.desafio.fileprocessor.infrastructure.steps.MailNotification;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    private PlatformTransactionManager platformTransactionManager;

    public BatchConfig(PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public JobExecutionListener mailNotificationJobListener() {
        return new MailNotification();
    }
}
