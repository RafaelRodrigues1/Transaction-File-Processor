package com.desafio.fileprocessor;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
@EnableJpaRepositories
public class FileProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileProcessorApplication.class, args);
    }

}
