package org.develcorp.services.transaction.mapper;

import org.develcorp.services.transaction.model.dto.TransactionDto;
import org.develcorp.services.transaction.model.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction TransactionDtoToTransaction(TransactionDto transactionDto);

    TransactionDto TransactionToTransactionDto(Transaction transaction);
}
