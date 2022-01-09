package com.mytel.smallBanking.service;

import com.mytel.smallBanking.controller.WithdrawRequest;
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
public class WithdrawService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public ResponseEntity<?> onWithdraw(@RequestBody WithdrawRequest withdrawRequest) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setToAccount(null);
        transaction.setFromAccount(withdrawRequest.getFromAccountNo());
        transaction.setAmount(withdrawRequest.getAmount());
        transaction.setStatus("Success");
        transaction.setTransactionAt(LocalDateTime.now());
        transaction.setLastUpdatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);
        Account account = accountRepository.findById(withdrawRequest.getFromAccountNo()).orElseThrow(Exception::new);
        account.setAvailableBalance(account.getAvailableBalance() - withdrawRequest.getAmount());
        accountRepository.save(account);

        TransactionResponse withdrawResponse=new TransactionResponse();
        withdrawResponse.setCurrentAmount(account.getAvailableBalance());
        withdrawResponse.setTransactionType("Withdraw");
        withdrawResponse.setStatus("Successfully Withdraw");
        return ResponseEntity.status(HttpStatus.OK).body(withdrawResponse);

    }
}
