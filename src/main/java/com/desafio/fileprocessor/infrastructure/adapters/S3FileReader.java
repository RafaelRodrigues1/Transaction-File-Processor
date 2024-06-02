package com.desafio.fileprocessor.infrastructure.adapters;

import com.desafio.fileprocessor.infrastructure.dto.TransactionFileDto;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class S3FileReader implements ItemReader<List<TransactionFileDto>> {

    @Override
    public List<TransactionFileDto> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("transactionFileReader");
        return null;
    }
}
