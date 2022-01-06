package com.mytel.smallBanking.service;

import com.mytel.smallBanking.controller.RegisterRequest;
import com.mytel.smallBanking.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    List<Account> findByAllAccount();

    double countBalance();

    double calculateTotalMoney();

    ResponseEntity<?> register(RegisterRequest regRequest);
}
