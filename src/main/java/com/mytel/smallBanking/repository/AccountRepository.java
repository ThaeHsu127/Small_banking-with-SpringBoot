package com.mytel.smallBanking.repository;

import com.mytel.smallBanking.model.Account;
import com.mytel.smallBanking.projection.AccountProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT * FROM account"
            ,nativeQuery = true)
    List<Account> queryByAllGetAccount();

    @Query(value = "SELECT COUNT(*) FROM account where available_balance >0",nativeQuery = true)

    double queryByCountBalance();

    @Query(value = "SELECT SUM(available_balance) FROM account",nativeQuery = true)
    double queryByCalculateTotalMoney();
}
