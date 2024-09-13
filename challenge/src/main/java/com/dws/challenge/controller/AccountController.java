package com.dws.challenge.controller;

import com.dws.challenge.domain.Account;
import com.dws.challenge.service.AccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private  final AccountsService accountsService;
    public AccountController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody Map<String,Object> transferRequest)
    {
        String fromAccountId = transferRequest.get("fromAccountId").toString();
        String toAccountId = transferRequest.get("toAccountId").toString();
        BigDecimal amount= new BigDecimal(transferRequest.get("amount").toString());
        Account account = new Account(toAccountId,amount);
        accountsService.transferMoney(fromAccountId,toAccountId,amount);
        return new ResponseEntity<>("Amount : "+ amount + "Transfer Successful to Account : "+toAccountId, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody Map<String,Object> transferRequest)
    {
        String accountId = transferRequest.get("accountId").toString();
        BigDecimal amount= new BigDecimal(transferRequest.get("amount").toString());
        Account account = new Account(accountId,amount);
        String response = accountsService.createAccount(account) ;
        String responseBody="";

        if(response.equalsIgnoreCase("duplicate"))
        {
            responseBody ="Account : "+accountId +" Already exists, Please try with \n another " +
                    "account Id";
            System.out.println(responseBody);
        } else if (response.equalsIgnoreCase("created")) {
            responseBody ="Account  : "+accountId +" Created Successful";
        }

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
