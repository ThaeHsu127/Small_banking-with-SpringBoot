package com.mytel.smallBanking.service.impl;

import com.mytel.smallBanking.model.Account;
import com.mytel.smallBanking.repository.AccountRepository;
import com.mytel.smallBanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findByAllAccount() {
        return accountRepository.queryByAllGetAccount();
    }

    @Override
    public Account countBalance() {
        return accountRepository.queryByCountBalance();
    }

    @Override
    public Account calculateTotalMoney(double availableBalance) {
        return accountRepository.queryByCalculateTotalMoney(availableBalance);
    }
}
