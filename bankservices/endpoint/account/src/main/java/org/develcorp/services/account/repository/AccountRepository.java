package org.develcorp.services.account.repository;

import org.develcorp.services.account.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomerId(Long customerId);

    List<Account> findByAccountNumber(Long accountNumber);
}
