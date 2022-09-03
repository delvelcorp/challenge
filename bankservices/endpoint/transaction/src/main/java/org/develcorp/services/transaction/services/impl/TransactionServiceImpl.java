package org.develcorp.services.transaction.services.impl;

import org.develcorp.services.transaction.mapper.TransactionMapper;
import org.develcorp.services.transaction.model.dto.BalanceDto;
import org.develcorp.services.transaction.model.dto.TransactionDto;
import org.develcorp.services.transaction.model.entity.Balance;
import org.develcorp.services.transaction.model.entity.Transaction;
import org.develcorp.services.transaction.model.enums.TransactionType;
import org.develcorp.services.transaction.model.error.TransactionError;
import org.develcorp.services.transaction.repository.TransactionRepository;
import org.develcorp.services.transaction.services.BalanceService;
import org.develcorp.services.transaction.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private TransactionMapper transactionMapper;

    @Value("${dailyDebitLimit}")
    private BigDecimal max;

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> listAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = transactionMapper.TransactionToTransactionDto(transaction);
            transactionDtoList.add(transactionDto);
        }

        return transactionDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionDto getTransaction(Long id) {
        return transactionMapper.TransactionToTransactionDto(transactionRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public TransactionDto openTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.TransactionDtoToTransaction(transactionDto);
        System.out.println("CUENTA ------------ " +  transaction.getAccountId());;

        System.out.println("INGRESA A GUARDAR");
        BalanceDto balanceDto = new BalanceDto();
        balanceDto.setAccountId(transaction.getAccountId());
        balanceDto.setActualBalance(transaction.getValue());
        balanceDto.setModifiedAt(new Timestamp(new Date().getTime()));
        System.out.println("GUARDA BALANCE");
        balanceService.saveBalance(balanceDto);

        transaction.setBalance(balanceDto.getActualBalance());
        transaction.setStatus(true);
        transaction.setMessage("Opening of " + transaction.getValue());
        System.out.println("GUARDA TRANSACCION");
        return transactionMapper.TransactionToTransactionDto(this.transactionRepository.save(transaction));
        }

    @Override
    @Transactional
    public TransactionDto saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.TransactionDtoToTransaction(transactionDto);
        BalanceDto balanceDto = balanceService.findByAccountId(transaction.getAccountId());

        if (balanceDto == null){
            return null;
        }else{
            switch (transaction.getTransactionType()) {
                case DEPOSIT:
                    balanceDto.setActualBalance(balanceDto.getActualBalance().add(transaction.getValue()));

                    transaction.setBalance(balanceDto.getActualBalance());
                    transaction.setStatus(true);
                    transaction.setMessage("Deposit of " + transaction.getValue());
                    break;
                case WITHDRAWAL:
                    List<TransactionDto> transactions = findByAccountIdAndDateAndTransactionType(transaction.getAccountId(), new Date(), TransactionType.WITHDRAWAL);

                    BigDecimal total = new BigDecimal(0);

                    for (TransactionDto objeto : transactions) {
                        if (objeto.getStatus()) {
                            total = total.add(objeto.getValue().abs());
                        }
                    }

                    if (total.add(transaction.getValue()).compareTo(max) > 0) {
                        transaction.setBalance(balanceDto.getActualBalance());
                        transaction.setStatus(false);
                        transaction.setMessage("Max mont limit " + max + ".");
                    } else {
                        if (balanceDto.getActualBalance().compareTo(transaction.getValue()) < 0) {
                            transaction.setBalance(balanceDto.getActualBalance());
                            transaction.setStatus(false);
                            transaction.setMessage("Balance insufficient");
                        } else {
                            balanceDto.setActualBalance(balanceDto.getActualBalance().subtract(transaction.getValue()));

                            transaction.setBalance(balanceDto.getActualBalance());
                            transaction.setStatus(true);
                            transaction.setMessage("Remove of " + transaction.getValue());
                        }
                    break;
                }
            }
        }

        balanceService.updateBalance(balanceDto);
        return transactionMapper.TransactionToTransactionDto(this.transactionRepository.save(transaction));
    }

    @Override
    @Transactional
    public TransactionDto updateTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.TransactionDtoToTransaction(transactionDto);
        transaction = transactionRepository.findById(transaction.getId()).orElse(null);

        if (transaction != null) {
            transaction.setAccountId(transactionDto.getAccountId());
            transaction.setTransactionType(transactionDto.getTransactionType());
            transaction.setBalance(transactionDto.getBalance());
            transaction.setMessage(transactionDto.getMessage());
            transaction.setDate(transactionDto.getDate());
            transaction.setValue(transactionDto.getValue());

            return transactionMapper.TransactionToTransactionDto(transactionRepository.save(transaction));
        } else {
            throw TransactionError.E001;
        }
    }

    @Override
    @Transactional
    public TransactionDto deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);

        if (transaction != null) {
            try{
                transaction.setStatus(false);
                return transactionMapper.TransactionToTransactionDto(transactionRepository.save(transaction));
            }catch (Exception e){
                throw TransactionError.E004;
            }
        }else {
            throw TransactionError.E001;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> findByAccountId(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = transactionMapper.TransactionToTransactionDto(transaction);
            transactionDtoList.add(transactionDto);
        }

        return transactionDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionDto findByAccountIdLast(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = transactionMapper.TransactionToTransactionDto(transaction);
            transactionDtoList.add(transactionDto);
        }

        Comparator<Transaction> compareById =
                (Transaction o1, Transaction o2) -> o1.getId().compareTo( o2.getId() );

        Collections.sort(transactions, compareById);

        if (transactions.isEmpty()){
            return null;
        }

        return transactionMapper.TransactionToTransactionDto(transactions.get(transactions.size() - 1));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> findByAccountIdAndDateAndTransactionType(Long accountId, Date date, TransactionType transactionType) {
        List<Transaction> transactions = transactionRepository.findByAccountIdAndDateAndTransactionType(accountId, date, transactionType);
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = transactionMapper.TransactionToTransactionDto(transaction);
            transactionDtoList.add(transactionDto);
        }

        return transactionDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> findByAccountIdAndDateBetween(Long accountId, Date fromDate, Date toDate) {
        List<Transaction> transactions = transactionRepository.findByAccountIdAndDateBetween(accountId, fromDate, toDate);
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = transactionMapper.TransactionToTransactionDto(transaction);
            transactionDtoList.add(transactionDto);
        }

        return transactionDtoList;
    }
}