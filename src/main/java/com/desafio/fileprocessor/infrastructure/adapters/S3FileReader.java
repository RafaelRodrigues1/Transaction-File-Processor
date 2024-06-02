package com.desafio.fileprocessor.infrastructure.adapters;

import com.desafio.fileprocessor.infrastructure.dto.TransactionFileDto;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class S3FileReader implements ItemReader<TransactionFileDto> {

    @Override
    public TransactionFileDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("transactionFileReader");
        return null;
    }
}
