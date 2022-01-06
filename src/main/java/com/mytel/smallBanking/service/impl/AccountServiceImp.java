package com.mytel.smallBanking.service.impl;

import com.mytel.smallBanking.controller.RegisterRequest;
import com.mytel.smallBanking.model.Account;
import com.mytel.smallBanking.repository.AccountRepository;
import com.mytel.smallBanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseEntity<?> register(RegisterRequest regRequest) {
        Account account = new Account();
        account.setAccountId(regRequest.getAccountId());
        account.setPhone(regRequest.getPhone());
        account.setName(regRequest.getName());
        account.setAvailableBalance(regRequest.getAvailableBalance());
        accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.OK).body(regRequest);
    }

    @Override
    public List<RegisterRequest> findByAllAccount() {
        return accountRepository.queryByAllGetAccount();
    }

    @Override
    public double countBalance() {
        return accountRepository.queryByCountBalance();

    }

    @Override
    public double calculateTotalMoney() {
        return accountRepository.queryByCalculateTotalMoney();
    }


}
