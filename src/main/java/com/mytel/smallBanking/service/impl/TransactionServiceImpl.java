package com.mytel.smallBanking.service.impl;

import com.mytel.smallBanking.controller.TransactionRequest;

import com.mytel.smallBanking.model.Transaction;
import com.mytel.smallBanking.repository.TransactionRepository;
import com.mytel.smallBanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<?> saveTrans(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionRequest.getTransactionId());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setFromAccount(transactionRequest.getFromAccount());
        transaction.setToAccount(transactionRequest.getToAccount());
        transaction.setTransactionAt(transactionRequest.getTransactionAt());
        transaction.setLastUpdatedAt(transactionRequest.getLastUpdatedAt());
        transaction.setTransactionType(transactionRequest.getTransactionType());
        transaction.setStatus(transactionRequest.getStatus());
        transactionRepository.save(transaction);
        return ResponseEntity.status(HttpStatus.OK).body(transactionRequest);
    }


    @Override
    public ResponseEntity<?> showAllTransactions() {
        return ResponseEntity.status(HttpStatus.OK).body(transactionRepository.findAll());
    }

}
