package com.mytel.smallBanking.service;

import com.mytel.smallBanking.controller.TransferRequest;
import com.mytel.smallBanking.model.Account;
import com.mytel.smallBanking.model.Transaction;
import com.mytel.smallBanking.repository.AccountRepository;
import com.mytel.smallBanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
public class TransferService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public ResponseEntity<?> onTransfer(@RequestBody TransferRequest transferRequest) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setFromAccount(transferRequest.getFromAccountNo());
        transaction.setToAccount(transferRequest.getToAccountNo());
        transaction.setAmount(transferRequest.getAmount());
        transaction.setStatus("Success");
        transaction.setTransactionAt(LocalDateTime.now());
        transaction.setLastUpdatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
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

    public ResponseEntity<?> onTransferFee(@RequestBody TransferRequest request) throws Exception {
        Transaction transFee = new Transaction();
        transFee.setFromAccount(request.getFromAccountNo());
        transFee.setToAccount(request.getToAccountNo());
        transFee.setAmount(request.getAmount());
        transFee.setStatus("Success");
        transFee.setTransactionAt(LocalDateTime.now());
        transFee.setLastUpdatedAt(LocalDateTime.now());
        transactionRepository.save(transFee);
        Account fromAccount = accountRepository.findById(transFee.getFromAccount())
                .orElseThrow(Exception::new);
        Account toAccount = accountRepository.findById(transFee.getToAccount())
                .orElseThrow(() -> new Exception());
        fromAccount.setAvailableBalance(fromAccount.getAvailableBalance()-request.getAmount()- request.getFee());
        toAccount.setAvailableBalance(toAccount.getAvailableBalance()+request.getAmount());
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        return ResponseEntity.status(HttpStatus.OK).body(request);

    }
}
