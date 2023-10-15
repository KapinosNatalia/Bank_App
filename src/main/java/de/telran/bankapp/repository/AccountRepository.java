package de.telran.bankapp.repository;

import de.telran.bankapp.entity.Account;
import de.telran.bankapp.entity.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("SELECT a FROM Account a WHERE a.balance >= :amount")
    List<Account> getAccountsWithBalanceMoreOrEqualThan(BigDecimal amount);

    @Query("SELECT a FROM Account a WHERE a.balance = 0 AND a.createdAt < :date AND a.status <> :statusForDeletion " +
            "AND a NOT IN (SELECT t.creditAccount FROM Transaction t) " +
            "AND a NOT IN (SELECT t.creditAccount FROM Transaction t) ")
    List<Account> getAccountsWithoutTransactionsAndCreatedEarlierThan(LocalDateTime date, AccountStatus statusForDeletion);
}
