package org.develcorp.services.transaction.repository;

import org.develcorp.services.transaction.model.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    Balance findByAccountId(Long accountId);


}
