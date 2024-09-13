package com.dws.challenge.service;

import com.dws.challenge.domain.Account;
import com.dws.challenge.exception.AccountNotFoundException;
import com.dws.challenge.exception.InsufficientFundsExceptoin;
import com.dws.challenge.repository.AccountsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@EnableAsync
@Service
public class AccountsService {

  @Getter
  private final AccountsRepository accountsRepository;
  @Getter
  private NotificationService notificationService;

  @Autowired
  public AccountsService(AccountsRepository accountsRepository,NotificationService notificationService) {

    this.accountsRepository = accountsRepository;
    this.notificationService=notificationService;
  }

  public String createAccount(Account account) {
    return this.accountsRepository.createAccount(account);
  }

  public Account getAccount(String accountId) {

    return this.accountsRepository.getAccount(accountId);
  }


  @Async
  @Transactional
  public synchronized void transferMoney(String fromAccountId, String toAccountId, BigDecimal amount)
  {
     Account fromAccount = getAccount(fromAccountId);
     Account toAccount = getAccount(toAccountId);
     boolean moneyTransfered = true;
     if(null==fromAccount )
     {
         moneyTransfered=false;
        throw new AccountNotFoundException("No Account Found  : "+fromAccountId);
     }

    if(null==toAccount )
    {
      moneyTransfered=false;
      throw new AccountNotFoundException("No Account Found  : "+toAccount);
    }

    if(fromAccount.getBalance().compareTo(amount) <0)
    {
        moneyTransfered=false;
       throw new InsufficientFundsExceptoin("Insufficient Funds in Account : "+fromAccountId);
    }

    CompletableFuture.runAsync(() ->
              {
                  try {
                      fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
                      toAccount.setBalance(toAccount.getBalance().add(amount));

                      accountsRepository.saveAccount(fromAccount);
                      accountsRepository.saveAccount(toAccount);
                  }catch (Exception e)
                  {
                      e.printStackTrace();
                  }
            }
              );


      if(moneyTransfered)
      {
          notificationService.notifyAboutTransfer(fromAccount," Your account has been Debited with amount : "+amount
                  +" To Account Id : "+toAccount.getAccountId());

          notificationService.notifyAboutTransfer(toAccount," Your account has been credited with amount : "+amount
                  +" Sender Account Id : "+fromAccountId);

      }

  }
}
