package com.mytel.smallBanking.service;

import com.mytel.smallBanking.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    List<Account> findByAllAccount();

    double countBalance();

    double calculateTotalMoney();
}
