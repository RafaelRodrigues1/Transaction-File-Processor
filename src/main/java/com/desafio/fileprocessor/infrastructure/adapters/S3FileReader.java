package com.desafio.fileprocessor.infrastructure.adapters;

import com.desafio.fileprocessor.infrastructure.dto.TransactionFileDto;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class S3FileReader implements ItemReader<List<TransactionFileDto>> {


    @Autowired
    private S3Client s3Client;
    @Value("${AWS_CSV_TRANSACTION_BUCKET}")
    private String bucket;
    private String key;

    @BeforeStep
    public void beforeTransactionFileHandleStep(StepExecution stepExecution) {
        this.key = stepExecution.getJobParameters().getString("bucket-key");
    }

    @Override
    public List<TransactionFileDto> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        List<TransactionFileDto> transactionFileDtoList = new ArrayList<>();
        ResponseInputStream<GetObjectResponse> objectResponse = this.s3Client.getObject(objectRequest -> {
            objectRequest.key(this.key)
                    .bucket(this.bucket)
                    .key(this.key)
                    .build();
        });
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(objectResponse))) {
            List<String> lineList = reader.lines().toList();
            lineList = lineList.subList(1, lineList.size());
            lineList.forEach(line -> {
                transactionFileDtoList.add(this.buildTransactionFileDto(line));
            });
        }
        return transactionFileDtoList;
    }

    private TransactionFileDto buildTransactionFileDto(String line) {
        String[] lineArray = line.split(";");
        return new TransactionFileDto(lineArray[0], lineArray[1], lineArray[2], lineArray[3], lineArray[4]);
    }
}
