package com.mytel.smallBanking.service;

import com.mytel.smallBanking.controller.WithdrawRequest;
import com.mytel.smallBanking.model.Account;
import com.mytel.smallBanking.model.Transaction;
import com.mytel.smallBanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WithdrawBusiness {
    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<?> onWithdraw(WithdrawRequest withdrawRequest) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setToAccount(null);
        transaction.setFromAccount(withdrawRequest.getFromAccountNo());
        Account account = accountRepository.findById(withdrawRequest.getFromAccountNo()).orElseThrow(Exception::new);
        account.setAvailableBalance(account.getAvailableBalance() - withdrawRequest.getAmount());
        accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.OK).body(withdrawRequest);

    }
}
