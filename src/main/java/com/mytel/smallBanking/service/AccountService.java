package com.mytel.smallBanking.service;

import com.mytel.smallBanking.controller.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    ResponseEntity<?> register(RegisterRequest regRequest);

    ResponseEntity<?> showAllAccounts();

    ResponseEntity<?> countBalance();

    ResponseEntity<?> calculateTotalMoney();

}
