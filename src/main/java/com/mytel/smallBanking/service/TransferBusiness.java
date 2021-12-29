package com.mytel.smallBanking.service;

import com.mytel.smallBanking.controller.TransferRequest;
import com.mytel.smallBanking.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransferBusiness {
    public ResponseEntity<?> onTransfer(TransferRequest transferRequest){
        Transaction transaction= new Transaction();
        transaction.setFromAccount(transferRequest.getFromAccountNo());
        transaction.setToAccount(transferRequest.getToAccountNo());
        return ResponseEntity.status(HttpStatus.OK).body(transferRequest);



    }
}
