package com.dws.challenge.repository;

import com.dws.challenge.domain.Account;
import com.dws.challenge.exception.DuplicateAccountIdException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountsRepositoryInMemory implements AccountsRepository {

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    @Override
    public String createAccount(Account account) throws DuplicateAccountIdException {
        String status="created";
        Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
        if (previousAccount != null) {

            status = "duplicate";
//            throw new DuplicateAccountIdException(
//                    "Account id " + account.getAccountId() + " already exists!");
            System.out.println(status);
        }
        System.out.println(status);
        return status;
    }

    @Override
    public Account getAccount(String accountId) {



//        if(null == accounts.get(accountId))
//        {
//            throw new AccountNotFoundException(" Account id " + accountId + " Not Found!");
//        }
        return accounts.get(accountId);
    }

    @Override
    public void clearAccounts() {
        accounts.clear();
    }

    @Override
    public Account saveAccount(Account account) {

        Account acct = accounts.replace(account.getAccountId(), account);
        return acct;
    }

}
