package org.develcorp.services.transaction.services.impl;

import org.develcorp.services.transaction.external.dto.account.AccountDto;
import org.develcorp.services.transaction.external.dto.customer.CustomerDto;
import org.develcorp.services.transaction.external.service.impl.AccountServiceImpl;
import org.develcorp.services.transaction.external.service.impl.CustomerServiceImpl;
import org.develcorp.services.transaction.mapper.TransactionMapper;
import org.develcorp.services.transaction.model.dto.BalanceDto;
import org.develcorp.services.transaction.model.dto.TransactionDto;
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

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private CustomerServiceImpl customerService;

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
    public TransactionDto byTransactionId(Long id) {
        return transactionMapper.TransactionToTransactionDto(transactionRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public TransactionDto postInitialTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.TransactionDtoToTransaction(transactionDto);
        BalanceDto balanceDto;

        balanceDto = new BalanceDto();
        balanceDto.setAccountNumber(transaction.getAccountNumber());
        balanceDto.setInitialBalance(transaction.getValue());
        balanceDto.setDate(new Timestamp(new Date().getTime()));
        balanceService.saveBalance(balanceDto);

        transaction.setBalance(balanceDto.getInitialBalance());
        transaction.setStatus(true);
        transaction.setMessage("Opening of " + transaction.getValue());
        return transactionMapper.TransactionToTransactionDto(this.transactionRepository.save(transaction));
        }

    @Override
    @Transactional
    public TransactionDto saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.TransactionDtoToTransaction(transactionDto);
        BalanceDto balanceDto = balanceService.findByAccountNumber(transaction.getAccountNumber());

        AccountDto accountDto =  accountService.getAccountNumber(transaction.getAccountNumber());
        CustomerDto customerDto =  customerService.getCustomerId(accountDto.getCustomerId());


        if (balanceDto == null){
            return null;
        }else{
            switch (transaction.getTransactionType()) {
                case DEPOSIT:
                    balanceDto.setCustomer(customerDto.getName());
                    balanceDto.setDate(new Date());
                    balanceDto.setAccountNumber(accountDto.getAccountNumber());
                    balanceDto.setAccountType(accountDto.getAccountType().getCode());
                    balanceDto.setInitialBalance(balanceDto.getInitialBalance());
                    balanceDto.setStatus(true);
                    balanceDto.setValue(transaction.getValue());
                    balanceDto.setBalance(balanceDto.getInitialBalance().add(transaction.getValue()));

                    transaction.setBalance(balanceDto.getInitialBalance());
                    transaction.setStatus(true);
                    transaction.setMessage("Deposit of " + transaction.getValue());
                    break;
                case WITHDRAWAL:
                    List<TransactionDto> transactions = findByAccountNumberAndDateAndTransactionType(transaction.getAccountNumber(), new Date(), TransactionType.WITHDRAWAL);

                    BigDecimal total = new BigDecimal(0);

                    for (TransactionDto objeto : transactions) {
                        if (objeto.getStatus()) {
                            total = total.add(objeto.getValue().abs());
                        }
                    }

                    if (total.add(transaction.getValue()).compareTo(max) > 0) {
                        transaction.setBalance(balanceDto.getBalance());
                        transaction.setStatus(false);
                        transaction.setMessage("Max mont limit " + max + ".");
                    } else {
                        if (balanceDto.getBalance().compareTo(transaction.getValue()) < 0) {
                            transaction.setBalance(balanceDto.getBalance());
                            transaction.setStatus(false);
                            transaction.setMessage("Balance insufficient");
                        } else {
                            balanceDto.setBalance(balanceDto.getBalance().subtract(transaction.getValue()));

                            transaction.setBalance(balanceDto.getBalance());
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
            transaction.setAccountNumber(transactionDto.getAccountNumber());
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
    public List<TransactionDto> findByAccountNumber(Long accountNumber) {
        List<Transaction> transactions = transactionRepository.findByAccountNumber(accountNumber);
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = transactionMapper.TransactionToTransactionDto(transaction);
            transactionDtoList.add(transactionDto);
        }

        return transactionDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionDto findByAccountNumberLast(Long accountNumber) {
        List<Transaction> transactions = transactionRepository.findByAccountNumber(accountNumber);
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = transactionMapper.TransactionToTransactionDto(transaction);
            transactionDtoList.add(transactionDto);
        }

        Comparator<Transaction> compareById =
                Comparator.comparing(Transaction::getId);

        transactions.sort(compareById);

        if (transactions.isEmpty()){
            return null;
        }

        return transactionMapper.TransactionToTransactionDto(transactions.get(transactions.size() - 1));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> findByAccountNumberAndDateAndTransactionType(Long accountNumber, Date date, TransactionType transactionType) {
        List<Transaction> transactions = transactionRepository.findByAccountNumberAndDateAndTransactionType(accountNumber, date, transactionType);
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = transactionMapper.TransactionToTransactionDto(transaction);
            transactionDtoList.add(transactionDto);
        }

        return transactionDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> findByAccountNumberAndDateBetween(Long accountNumber, Date fromDate, Date toDate) {
        List<Transaction> transactions = transactionRepository.findByAccountNumberAndDateBetween(accountNumber, fromDate, toDate);
        List<TransactionDto> transactionDtoList = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionDto transactionDto = transactionMapper.TransactionToTransactionDto(transaction);
            transactionDtoList.add(transactionDto);
        }

        return transactionDtoList;
    }
}