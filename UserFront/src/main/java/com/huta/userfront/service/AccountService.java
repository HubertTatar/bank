package com.huta.userfront.service;

import com.huta.userfront.domain.PrimaryAccount;
import com.huta.userfront.domain.PrimaryTransaction;
import com.huta.userfront.domain.SavingsAccount;
import com.huta.userfront.domain.SavingsTransaction;

import java.security.Principal;

public interface AccountService {
    PrimaryAccount createPrimaryAccount();

    SavingsAccount createSavingsAccount();

    void withdraw(String accountType, double amount, Principal principal);

    void deposit(String accountType, double amount, Principal principal);


}
