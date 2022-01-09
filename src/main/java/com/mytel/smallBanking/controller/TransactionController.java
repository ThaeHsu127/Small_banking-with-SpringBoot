package com.mytel.smallBanking.controller;


import com.mytel.smallBanking.repository.TransactionRepository;
import com.mytel.smallBanking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("bank/transaction")
public class TransactionController {
    @Autowired
    DepositService depositBusiness;

    @Autowired
    WithdrawService withdrawBusiness;

    @Autowired
    TransferService transferBusiness;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionService transactionService;



    @PostMapping("/saveTrans")
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionRequest transaction) {
        return transactionService.saveTrans(transaction);
    }
    @GetMapping
    public ResponseEntity<?> showAllTransactions(){

        return transactionService.showAllTransactions();
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest request) throws Exception {
        return depositBusiness.onDeposit(request);

    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithdrawRequest withdrawRequest) throws Exception {
        return withdrawBusiness.onWithdraw(withdrawRequest);

    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest transferRequest) throws Exception {
        return transferBusiness.onTransfer(transferRequest);
    }

    @PostMapping("/transferFee")
    public ResponseEntity<?> transferFee(@RequestBody TransferRequest transferRequest) throws Exception {
        return transferBusiness.onTransferFee(transferRequest);
    }
}
