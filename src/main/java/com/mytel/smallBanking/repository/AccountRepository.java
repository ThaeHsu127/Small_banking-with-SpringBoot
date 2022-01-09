package com.mytel.smallBanking.repository;

import com.mytel.smallBanking.controller.RegisterRequest;
import com.mytel.smallBanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT COUNT(*) FROM account where available_balance >0",nativeQuery = true)
    int queryByCountBalance();

    @Query(value = "SELECT SUM(available_balance) FROM account",nativeQuery = true)
    double queryByCalculateTotalMoney();



}
