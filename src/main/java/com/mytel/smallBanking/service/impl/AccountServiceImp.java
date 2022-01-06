package com.mytel.smallBanking.service.impl;

import com.mytel.smallBanking.model.Account;
import com.mytel.smallBanking.repository.AccountRepository;
import com.mytel.smallBanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findByAllAccount() {
        return accountRepository.queryByAllGetAccount();
    }

    @Override
    public double countBalance() {return accountRepository.queryByCountBalance();

    }

    @Override
    public double calculateTotalMoney() {
        return accountRepository.queryByCalculateTotalMoney();
    }
}
