package com.mytel.smallBanking.service.impl;

import com.mytel.smallBanking.controller.RegisterRequest;
import com.mytel.smallBanking.model.Account;
import com.mytel.smallBanking.repository.AccountRepository;
import com.mytel.smallBanking.response.CountResponse;
import com.mytel.smallBanking.response.TotalEmoneyResponse;
import com.mytel.smallBanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<?> showAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountRepository.findAll());
    }

    @Override
    public ResponseEntity<?> countBalance() {
        CountResponse countResponse=new CountResponse();
        countResponse.setTotalCount(accountRepository.queryByCountBalance());
        countResponse.setResult("Success");
        return ResponseEntity.status(HttpStatus.OK).body(countResponse);
    }

    @Override
    public ResponseEntity<?> calculateTotalMoney() {
        TotalEmoneyResponse totalResponse=new TotalEmoneyResponse();
        totalResponse.setTotalMoney(accountRepository.queryByCalculateTotalMoney());
        totalResponse.setResult("Success");
        return ResponseEntity.status(HttpStatus.OK).body(totalResponse);
    }


}
