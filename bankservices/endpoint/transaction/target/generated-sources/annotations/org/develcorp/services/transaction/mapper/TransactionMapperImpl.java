package org.develcorp.services.transaction.mapper;

import javax.annotation.Generated;
import org.develcorp.services.transaction.model.dto.TransactionDto;
import org.develcorp.services.transaction.model.entity.Transaction;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-02T16:29:01-0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public Transaction TransactionDtoToTransaction(TransactionDto transactionDto) {
        if ( transactionDto == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setId( transactionDto.getId() );
        transaction.setDate( transactionDto.getDate() );
        transaction.setTransactionType( transactionDto.getTransactionType() );
        transaction.setValue( transactionDto.getValue() );
        transaction.setBalance( transactionDto.getBalance() );
        if ( transactionDto.getStatus() != null ) {
            transaction.setStatus( transactionDto.getStatus() );
        }
        transaction.setMessage( transactionDto.getMessage() );
        transaction.setAccountId( transactionDto.getAccountId() );

        return transaction;
    }

    @Override
    public TransactionDto TransactionToTransactionDto(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setId( transaction.getId() );
        transactionDto.setDate( transaction.getDate() );
        transactionDto.setTransactionType( transaction.getTransactionType() );
        transactionDto.setValue( transaction.getValue() );
        transactionDto.setBalance( transaction.getBalance() );
        transactionDto.setStatus( transaction.isStatus() );
        transactionDto.setMessage( transaction.getMessage() );
        transactionDto.setAccountId( transaction.getAccountId() );

        return transactionDto;
    }
}
