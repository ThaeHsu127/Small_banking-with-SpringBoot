package com.mytel.smallBanking.controller;

import com.mytel.smallBanking.exception.ResourceNotFoundException;
import com.mytel.smallBanking.projection.AccountProj;
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
    public Account registerAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @GetMapping
    public List<Account> showAllAccounts() {
        return accountRepository.findAll();
    }

    @PutMapping("{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account account) {
        Account updateAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UpdateAccount not exist with id:" + id));
        updateAccount.setAccountId(account.getAccountId());
        updateAccount.setName(account.getName());
        updateAccount.setPhone(account.getPhone());
        updateAccount.setAvailableBalance(account.getAvailableBalance());
        accountRepository.save(updateAccount);
        return ResponseEntity.ok(updateAccount);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable Long id) {
        Account deleteAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not exist with id:" + id));
        accountRepository.delete(deleteAccount);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //get allAccount
    @GetMapping("/getAllAccount")
    public List<AccountProj> getAllAccounts(){
        return accountService.findByAllAccount();

    }
    //count total account which have balance >0
    @GetMapping("/count")
    public List<Account> countBalanceGreaterZero(){
        return accountService.countBalance();

    }
    //calculate total emoney
    @GetMapping("/totalMoney")
    public List<Account> calculateTotalMoney(){
        return accountService.calculateTotalMoney();

    }




}
