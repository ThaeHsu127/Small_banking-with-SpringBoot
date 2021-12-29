package com.mytel.smallBanking.controller;


import com.mytel.smallBanking.exception.ResourceNotFoundException;
import com.mytel.smallBanking.model.Transaction;
import com.mytel.smallBanking.repository.TransactionRepository;
import com.mytel.smallBanking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("bank/transaction")
public class TransactionController {
    @Autowired
    DepositBusiness depositBusiness;

    @Autowired
    WithdrawBusiness withdrawBusiness;

    @Autowired
    TransferBusiness transferBusiness;

    @Autowired
    TransactionRepository transactionRepository;

    public static java.sql.Date getSqlDate(String strDate){
        java.sql.Date sqlDate=null;
        try{
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate=format.parse(strDate);
            sqlDate=new java.sql.Date(utilDate.getTime());
        }catch(Exception e){System.out.println(e);}
        return sqlDate;
    }

    public static java.sql.Date getCurrentDate(){
        java.sql.Date sqlCurrentDate=null;
        try{
            java.util.Date utilDate=java.util.Calendar.getInstance().getTime();
            sqlCurrentDate=new java.sql.Date(utilDate.getTime());

        }catch(Exception e){System.out.println(e);}
        return sqlCurrentDate;
    }


    @PostMapping("/saveTrans")
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    @GetMapping
    public List<Transaction> showAllTransactions(){
        return transactionRepository.findAll();
    }

    @PutMapping("{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") long id, @RequestBody Transaction transaction) {
        Transaction updateTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not exist with id:" + id));
        updateTransaction.setTransactionType(transaction.getTransactionType());
        updateTransaction.setToAccount(transaction.getToAccount());
        updateTransaction.setFromAccount(transaction.getFromAccount());
        updateTransaction.setAmount(transaction.getAmount());
        updateTransaction.setStatus(transaction.getStatus());
        updateTransaction.setTransactionAt(LocalDateTime.parse(transaction.getTransactionAt().toString()));
        updateTransaction.setLastUpdatedAt(LocalDateTime.parse(transaction.getLastUpdatedAt().toString()));
        transactionRepository.save(updateTransaction);
        return ResponseEntity.ok(updateTransaction);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteTransaction(@PathVariable Long id) {
        Transaction deleteTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not exist with id:" + id));
        transactionRepository.delete(deleteTransaction);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest request){
        return depositBusiness.onDeposit(request);

    }
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        return withdrawBusiness.onWithdraw(withdrawRequest);



    }
    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest transferRequest) {
        return transferBusiness.onTransfer(transferRequest);



    }




}
