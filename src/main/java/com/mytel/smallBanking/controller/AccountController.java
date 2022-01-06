package com.mytel.smallBanking.controller;

import com.mytel.smallBanking.exception.ResourceNotFoundException;
import com.mytel.smallBanking.repository.AccountRepository;
import com.mytel.smallBanking.model.Account;
import com.mytel.smallBanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bank/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody RegisterRequest regRequest) {
        return new ResponseEntity<>(accountService.register(regRequest), HttpStatus.OK);

    }
    @GetMapping
    public List<Account> showAllAccounts() {
        return accountRepository.findAll();
    }

    @PutMapping("{id}")
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

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        Account deleteAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not exist with id:" + id));
        accountRepository.delete(deleteAccount);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //get allAccount
    @GetMapping("/getAllAccount")
    public List<RegisterRequest> getAllAccounts() {
        return accountService.findByAllAccount();

    }
    //count total account which have balance >0
//    @GetMapping("/count")
//    public double countBalanceGreaterZero(){
//        return accountService.countBalance();
//
//    }

    @GetMapping("/count")
    public ResponseEntity<?> countBalanceGreaterZero() {
        return new ResponseEntity<>(accountService.countBalance(), HttpStatus.OK);

    }

    //calculate total emoney
    @GetMapping("/totalMoney")
    public ResponseEntity<?> calculateTotalMoney() {
        return new ResponseEntity<>(accountService.calculateTotalMoney(), HttpStatus.OK);

    }


}
