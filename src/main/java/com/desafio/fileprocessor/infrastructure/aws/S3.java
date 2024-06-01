package com.desafio.fileprocessor.infrastructure.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

@Component
public class S3 {

    @Autowired
    private AwsConfig awsConfig;

    public void build() {
        S3Client s3 = S3Client.builder()
                .credentialsProvider(awsConfig.credentialsProvider())
                .region(awsConfig.getRegion())
                .build();

        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);

        listBucketsResponse.buckets().forEach(bucket -> {
            System.out.println(bucket.name());
        });
    }
}
