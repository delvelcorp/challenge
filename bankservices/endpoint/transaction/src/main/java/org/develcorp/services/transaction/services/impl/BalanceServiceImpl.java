package org.develcorp.services.transaction.services.impl;

import org.develcorp.services.transaction.mapper.BalanceMapper;
import org.develcorp.services.transaction.model.dto.BalanceDto;
import org.develcorp.services.transaction.model.entity.Balance;
import org.develcorp.services.transaction.model.error.BalanceError;
import org.develcorp.services.transaction.repository.BalanceRepository;
import org.develcorp.services.transaction.services.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private BalanceMapper balanceMapper;

    @Override
    @Transactional(readOnly = true)
    public BalanceDto getById(Long id) {
        return balanceMapper.BalanceToBalanceDto(balanceRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public BalanceDto saveBalance(BalanceDto balanceDto) {
        Balance balance = balanceMapper.BalanceDtoToBalance(balanceDto);

        if (balanceRepository.findById(balance.getId()) != null) {
            throw BalanceError.E002;
        } else {
            return balanceMapper.BalanceToBalanceDto(balanceRepository.save(balance));
        }
    }

    @Override
    @Transactional
    public BalanceDto updateBalance(BalanceDto balanceDto) {
        Balance balance = balanceMapper.BalanceDtoToBalance(balanceDto);
        balance = balanceRepository.findById(balance.getId()).orElse(null);

        if (balance != null) {
            balance.setAccountId(balanceDto.getAccountId());
            balance.setActualBalance(balanceDto.getActualBalance());
            balance.setModifiedAt(balanceDto.getModifiedAt());

            return balanceMapper.BalanceToBalanceDto(balanceRepository.save(balance));
        } else {
            throw BalanceError.E001;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BalanceDto findByAccountId(Long accountId) {
        return balanceMapper.BalanceToBalanceDto(balanceRepository.findByAccountId(accountId));
    }
}