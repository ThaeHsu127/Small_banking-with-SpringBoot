package com.mytel.smallBanking.service;

import com.mytel.smallBanking.controller.TransactionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    ResponseEntity<?> saveTrans(TransactionRequest transaction);

    ResponseEntity<?> showAllTransactions();
}
