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
