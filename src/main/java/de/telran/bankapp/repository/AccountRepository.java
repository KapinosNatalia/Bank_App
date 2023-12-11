package de.telran.bankapp.repository;

import de.telran.bankapp.entity.Account;
import de.telran.bankapp.entity.Client;
import de.telran.bankapp.entity.enums.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> getAccountByBalanceIsGreaterThanEqual(BigDecimal amount);

    List<Account> getAccountsByStatusIs(AccountStatus status);

    @Query(value = "SELECT * from mark_accounts_for_deletion(:date, :statusForDeletion);", nativeQuery = true)
    List<Account> getAccountsWithoutTransactionsAndCreatedEarlierThan(String date, String statusForDeletion);
}
