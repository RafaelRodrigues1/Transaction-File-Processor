package com.desafio.fileprocessor.infrastructure.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3 {

    @Autowired
    private AwsConfig awsConfig;

    @Bean
    public S3Client getS3Client() {
        return S3Client.builder()
                .credentialsProvider(awsConfig.credentialsProvider())
                .region(awsConfig.getRegion())
                .build();
    }
}
