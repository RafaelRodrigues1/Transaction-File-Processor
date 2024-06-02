package com.desafio.fileprocessor.infrastructure.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;

@Configuration
public class AwsConfig {

    @Value("${AWS_ACCESS_KEY_ID}")
    private String accessId;
    @Value("${AWS_SECRET_ACCESS_KEY}")
    private String secretKey;
    @Value("${AWS_REGION}")
    private String region;

    public StaticCredentialsProvider credentialsProvider() {
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessId, secretKey);
        return StaticCredentialsProvider.create(awsBasicCredentials);
    }

    public Region getRegion() {
        return Region.of(region);
    }
}
