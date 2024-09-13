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
            System.out.println(status);
        }

        String responseBody="";

        if(status.equalsIgnoreCase("duplicate"))
        {
            responseBody ="Account : "+account.getAccountId() +" Already exists, Please try with \n another " +
                    "account Id";
            System.out.println(responseBody);
        } else if (status.equalsIgnoreCase("created")) {
            responseBody ="Account  : "+account.getAccountId() +" Created Successful";
        }
        System.out.println(status);
        return responseBody;
    }

    @Override
    public Account getAccount(String accountId) {
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
