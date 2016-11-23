package com.huta.userfront.service;

import com.huta.userfront.domain.PrimaryAccount;
import com.huta.userfront.domain.SavingsAccount;

public interface AccountService {
    PrimaryAccount createPrimaryAccount();

    SavingsAccount createSavingsAccount();
}
