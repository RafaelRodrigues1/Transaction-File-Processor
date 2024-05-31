package com.desafio.fileprocessor.infrastructure.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledFileProcessorJob {

    @Scheduled(cron = "${file-processor.job.cron}")
    public void processFile() {
        System.out.println("AOP");
    }

}
