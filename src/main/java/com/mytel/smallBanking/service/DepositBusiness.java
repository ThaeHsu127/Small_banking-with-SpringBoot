package com.mytel.smallBanking.service;

import com.mytel.smallBanking.controller.DepositRequest;
import com.mytel.smallBanking.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepositBusiness {
    public ResponseEntity<?> onDeposit(DepositRequest request){
        Transaction transaction = new Transaction();
        transaction.setFromAccount(null);
        System.out.println(transaction.getFromAccount());
        transaction.setToAccount(request.getToAccountNo());
        return ResponseEntity.status(HttpStatus.OK).body(request);
    }

}
