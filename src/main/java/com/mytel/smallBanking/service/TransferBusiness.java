package com.mytel.smallBanking.service;

import com.mytel.smallBanking.controller.TransferRequest;
import com.mytel.smallBanking.model.Account;
import com.mytel.smallBanking.model.Transaction;
import com.mytel.smallBanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransferBusiness {
    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<?> onTransfer(TransferRequest transferRequest) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setFromAccount(transferRequest.getFromAccountNo());
        transaction.setToAccount(transferRequest.getToAccountNo());
        Account fromAccount = accountRepository.findById(transaction.getFromAccount())
                .orElseThrow(Exception::new);
        Account toAccount = accountRepository.findById(transaction.getToAccount())
                .orElseThrow(() -> new Exception());
        fromAccount.setAvailableBalance(fromAccount.getAvailableBalance() - transferRequest.getAmount());
        toAccount.setAvailableBalance(toAccount.getAvailableBalance() + transferRequest.getAmount());
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        return ResponseEntity.status(HttpStatus.OK).body(transferRequest);


    }
}
