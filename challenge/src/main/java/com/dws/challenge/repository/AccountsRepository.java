package com.dws.challenge.repository;

import com.dws.challenge.domain.Account;
import com.dws.challenge.exception.AccountNotFoundException;
import com.dws.challenge.exception.DuplicateAccountIdException;

public interface AccountsRepository  {

  String createAccount(Account account) throws DuplicateAccountIdException;

  Account getAccount(String accountId) throws AccountNotFoundException;

  void clearAccounts();

  Account saveAccount(Account account);
}
