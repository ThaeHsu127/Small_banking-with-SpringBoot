package com.mytel.smallBanking.service;

import com.mytel.smallBanking.controller.DepositRequest;
import com.mytel.smallBanking.model.Account;
import com.mytel.smallBanking.model.Transaction;
import com.mytel.smallBanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class DepositBusiness {
    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<?> onDeposit(@RequestBody DepositRequest request) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setFromAccount(null);
        transaction.setToAccount(request.getToAccountNo());
        transaction.setAmount(request.getAmount());
        Account account = accountRepository.findById(request.getToAccountNo()).orElseThrow(Exception::new);
        account.setAvailableBalance(account.getAvailableBalance() + request.getAmount());
        accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.OK).body(request);
    }


}
