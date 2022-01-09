package com.mytel.smallBanking.controller;

import com.mytel.smallBanking.exception.ResourceNotFoundException;
import com.mytel.smallBanking.repository.AccountRepository;
import com.mytel.smallBanking.model.Account;
import com.mytel.smallBanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody RegisterRequest regRequest) {
        return accountService.register(regRequest);

    }

    @GetMapping
    public ResponseEntity<?> showAllAccounts() {
        return accountService.showAllAccounts();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable("id") long id, @RequestBody RegisterRequest regRequest) {
        Account updateAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UpdateAccount not exist with id:" + id));
        updateAccount.setAccountId(regRequest.getAccountId());
        updateAccount.setName(regRequest.getName());
        updateAccount.setPhone(regRequest.getPhone());
        updateAccount.setAvailableBalance(regRequest.getAvailableBalance());
        accountRepository.save(updateAccount);
        return ResponseEntity.ok(updateAccount);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        Account deleteAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not exist with id:" + id));
        accountRepository.delete(deleteAccount);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //count total account which have balance >0
    @GetMapping("/count")
    public ResponseEntity<?> countBalanceGreaterZero() {
        return accountService.countBalance();

    }

    //calculate total Emoney
    @GetMapping("/totalMoney")
    public ResponseEntity<?> calculateTotalMoney() {
        return accountService.calculateTotalMoney();

    }


}
