package com.mytel.smallBanking.service;

import com.mytel.smallBanking.controller.DepositRequest;
import com.mytel.smallBanking.model.Account;
import com.mytel.smallBanking.model.Transaction;
import com.mytel.smallBanking.repository.AccountRepository;
import com.mytel.smallBanking.repository.TransactionRepository;
import com.mytel.smallBanking.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class DepositService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;


    public ResponseEntity<?> onDeposit(DepositRequest request) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setFromAccount(null);
        transaction.setToAccount(request.getToAccountNo());
        transaction.setAmount(request.getAmount());
        transaction.setStatus("Success");
        transaction.setTransactionAt(LocalDateTime.now());
        transaction.setLastUpdatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
        Account account = accountRepository.findById(request.getToAccountNo()).orElseThrow(Exception::new);
        account.setAvailableBalance(account.getAvailableBalance() + request.getAmount());
        accountRepository.save(account);

        TransactionResponse depositResponse=new TransactionResponse();
        depositResponse.setCurrentAmount(account.getAvailableBalance());
        depositResponse.setTransactionType("Deposit");
        depositResponse.setStatus("Successfully Deposit");
        return ResponseEntity.status(HttpStatus.OK).body(depositResponse);




    }


}
