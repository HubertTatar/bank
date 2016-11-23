package com.huta.userfront.dao;

import com.huta.userfront.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {
    SavingsAccount findByAccountNumber (long accountNumber);
}
