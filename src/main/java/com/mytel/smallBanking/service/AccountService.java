package com.mytel.smallBanking.service;

import com.mytel.smallBanking.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    Account findByAllAccount();

    Account countBalance();

    Account calculateTotalMoney();
}
