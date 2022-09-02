package org.develcorp.services.account.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.develcorp.services.account.mapper.AccountMapper;
import org.develcorp.services.account.model.dto.AccountDto;
import org.develcorp.services.account.model.dto.TransactionDto;
import org.develcorp.services.account.model.entity.Account;
import org.develcorp.services.account.model.dto.ReportDto;
import org.develcorp.services.account.model.enums.TransactionType;
import org.develcorp.services.account.model.error.AccountError;
import org.develcorp.services.account.repository.AccountRepository;
import org.develcorp.services.account.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    private final WebClient.Builder webClientBuilder;

    AccountServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    TcpClient tcpClient = TcpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    private <T> List<T> getTransactions(long accountId) {
        List<T> transactions = new ArrayList<>();

        try {
            WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .baseUrl("http://endpoint-transaction")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", "http://endpoint-transaction"))
                    .build();
            List<Object> block = client.method(HttpMethod.GET).uri(uriBuilder -> uriBuilder
                            .path("/transactions")
                            .queryParam("accountId", accountId)
                            .build())
                    .retrieve().bodyToFlux(Object.class).collectList().block();
            transactions = (List<T>) block;
        } catch (Exception e) {
            return transactions;
        }
        return transactions;
    }

    private <T> List<T> getTransactions(long accountId, String fromDate, String toDate) {
        List<T> transactions = new ArrayList<>();

        try {
            WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .baseUrl("http://endpoint-transaction")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", "http://endpoint-transaction"))
                    .build();
            List<Object> block = client.method(HttpMethod.GET).uri(uriBuilder -> uriBuilder
                            .path("/transactions/report")
                            .queryParam("cliente", accountId)
                            .queryParam("desde", fromDate)
                            .queryParam("hasta", toDate)
                            .build())
                    .retrieve().bodyToFlux(Object.class).collectList().block();
            transactions = (List<T>) block;
        } catch (Exception e) {
            return transactions;
        }
        return transactions;
    }
    private String getAccountIdentification(long accountId) {
        String identification = null;
            WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .baseUrl("http://endpoint-customer")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", "http://endpoint-customer"))
                    .build();
            JsonNode block = client.method(HttpMethod.GET).uri("/customers/" + accountId)
                    .retrieve().bodyToMono(JsonNode.class).block();

            if(block != null){
                identification = block.get("name").asText();
            }

        return identification;
    }

    private void createFirstTransaction(Account account) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setAccountId(account.getAccountNumber());
        transactionDto.setTransactionType(TransactionType.OPENING);
        transactionDto.setValue(account.getInitialBalance());
        transactionDto.setBalance(account.getInitialBalance());
        transactionDto.setDate(new Date());

        webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .baseUrl("http://endpoint-transaction")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://endpoint-transaction"))
                .build();

        webClientBuilder.build()
                .post()
                .uri("/transactions/opening")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON )
                .body(BodyInserters.fromValue(transactionDto))
                .exchange()
                .block();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();

        for (Account account : accounts) {
            AccountDto accountDto = accountMapper.AccountToAccountDto(account);
            accountDtoList.add(accountDto);
        }

        return accountDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto getByAccountId(Long id) {
        return accountMapper.AccountToAccountDto(accountRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public AccountDto saveAccount(AccountDto accountDto) {
        Account account = accountMapper.AccountDtoToAccount(accountDto);

        if (accountRepository.findById(account.getAccountNumber()).isPresent()) {
            throw AccountError.E002;
        } else {
            account.setStatus(true);
            createFirstTransaction(account);
            return accountMapper.AccountToAccountDto(accountRepository.save(account));
        }
    }

    @Override
    @Transactional
    public AccountDto updateAccount(AccountDto accountDto) {
        Account account = accountMapper.AccountDtoToAccount(accountDto);
        account = accountRepository.findById(account.getAccountNumber()).orElse(null);

        if (account != null) {
            account.setCustomerId(accountDto.getCustomerId());
            account.setAccountType(accountDto.getAccountType());
            account.setInitialBalance(accountDto.getInitialBalance());
            account.setStatus(accountDto.isStatus());
            account.setTransactions(accountDto.getTransactions());

            return accountMapper.AccountToAccountDto(accountRepository.save(account));
        } else {
            throw AccountError.E001;
        }
    }

    @Override
    @Transactional
    public AccountDto deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElse(null);

        if (account != null) {
            try{
                account.setStatus(false);
                return accountMapper.AccountToAccountDto(accountRepository.save(account));
            }catch (Exception e){
                throw AccountError.E004;
            }
        }else {
            throw AccountError.E001;
        }
    }

    @Override
    public List<AccountDto> findByCustomer(Long customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        List<AccountDto> accountDtoList = new ArrayList<>();

        for (Account account : accounts) {
            AccountDto accountDto = accountMapper.AccountToAccountDto(account);
            accountDtoList.add(accountDto);
        }

        return accountDtoList;
    }

    @Override
    public List<ReportDto> getReport(Long accountId, String fromDate, String toDate) {
        List<ReportDto> reports = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        ReportDto reportDto;
        String nameAccount;
        List<Account> accounts = accountRepository.findByCustomerId(accountId);

        System.out.println("Cuenta a consultar" + accounts.size());
        if(accounts.isEmpty()){
            return null;
        }

        nameAccount = getAccountIdentification(accountId);

        for (Account account : accounts) {
            List<TransactionDto> transactionDtoList = mapper.convertValue(getTransactions(account.getAccountNumber(), fromDate, toDate), new TypeReference<List<TransactionDto>>() {});

            if(transactionDtoList != null){
                for (TransactionDto transactionDto : transactionDtoList) {
                    if (transactionDto != null) {
                        reportDto = new ReportDto();
                        reportDto.setAccount(nameAccount);
                        reportDto.setAccountNumber(account.getAccountNumber());
                        reportDto.setAccountType(account.getAccountType().getCode());
                        reportDto.setStatus(account.isStatus());
                        reportDto.setDate(transactionDto.getDate());
                        reportDto.setInitialBalance(account.getInitialBalance());
                        if(transactionDto.getTransactionType().getCode().equals("R")){
                            reportDto.setTransactionValue(transactionDto.getValue().multiply(new BigDecimal(-1)));
                        }else{
                        reportDto.setTransactionValue(transactionDto.getValue());
                        }
                        reportDto.setTransactionBalance(transactionDto.getBalance());

                        reports.add(reportDto);
                    }
                }

                System.out.println("TAMANO LISTA " + reports.size());
            }
        }
        return reports;
    }
}