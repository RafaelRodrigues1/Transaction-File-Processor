package com.desafio.fileprocessor.infrastructure.steps;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class MailNotification implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("afterJob");
    }
}
