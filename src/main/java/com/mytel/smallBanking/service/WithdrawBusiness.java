package com.mytel.smallBanking.service;

import com.mytel.smallBanking.controller.WithdrawRequest;
import com.mytel.smallBanking.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WithdrawBusiness {
    public ResponseEntity<?> onWithdraw(WithdrawRequest withdrawRequest){
        Transaction transaction= new Transaction();
        transaction.setToAccount(null);
        transaction.setFromAccount(withdrawRequest.getFromAccountNo());
    return ResponseEntity.status(HttpStatus.OK).body(withdrawRequest);

    }
}
