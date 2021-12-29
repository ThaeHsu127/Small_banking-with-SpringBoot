package com.mytel.smallBanking.repository;

import com.mytel.smallBanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT account_id FROM small_banking.account",nativeQuery = true)

    Account queryByAllGetAccount(String accountId);
}
