package com.desafio.fileprocessor.infrastructure.adapters;

import com.desafio.fileprocessor.infrastructure.aws.S3;
import com.desafio.fileprocessor.infrastructure.dto.TransactionFileDto;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class S3FileReader implements ItemReader<List<TransactionFileDto>> {


    @Autowired
    private S3Client s3Client;
    @Value("${AWS_CSV_TRANSACTION_BUCKET}")
    private String bucket;
    @Value("${TO_PROCESS_DIRECTORY}")
    private String toProcessDirectory;
    @Value("${PROCESSING_DIRECTORY}")
    private String processingDirectory;

    @Override
    public List<TransactionFileDto> read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
        List<TransactionFileDto> transactionFileDtoList = new ArrayList<>();
        String fileName = "";
        try {
            S3Object s3Object = this.getNextFileObject();
            fileName = this.getFileName(s3Object);
            ResponseInputStream<GetObjectResponse> objectResponse = this.s3Client.getObject(objectRequest -> {
                objectRequest
                        .bucket(this.bucket)
                        .key(s3Object.key())
                        .build();
            });

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(objectResponse))) {
                List<String> lineList = reader.lines().toList();
                lineList = lineList.subList(1, lineList.size());
                lineList.forEach(line -> {
                    transactionFileDtoList.add(this.buildTransactionFileDto(line));
                });
            }
            this.moveToProcessing(s3Object, fileName);
        }catch (NoSuchElementException ex){
            System.out.println("Não há arquivos para integrar");
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return transactionFileDtoList;
    }

    private S3Object getNextFileObject() {
        List<S3Object> contents = this.s3Client.listObjectsV2(builder -> builder
                .bucket(this.bucket)
                .prefix(this.toProcessDirectory)
                .startAfter(this.toProcessDirectory)
                .maxKeys(1)
                .build()).contents();
        return contents.getFirst();
    }

    private void moveToProcessing(S3Object s3Object, String fileName) {
        if(s3Object == null || fileName == null) throw new IllegalArgumentException("s3Object or fileName null");

        this.s3Client.copyObject(copyObjectBuilder -> copyObjectBuilder
                .sourceBucket(this.bucket)
                .sourceKey(s3Object.key())
                .destinationBucket(this.bucket)
                .destinationKey(this.processingDirectory + fileName)
                .build());

        this.s3Client.deleteObject(deleteObjectBuilder -> deleteObjectBuilder.
                bucket(this.bucket)
                .key(s3Object.key())
                .build());
    }

    private String getFileName(S3Object s3Object) {
        String[] keyParts = s3Object.key().split("/");
        return keyParts[keyParts.length - 1];
    }

    private TransactionFileDto buildTransactionFileDto(String line) {
        String[] lineArray = line.split(";");
        return new TransactionFileDto(lineArray[0], lineArray[1], lineArray[2], lineArray[3], lineArray[4]);
    }
}
