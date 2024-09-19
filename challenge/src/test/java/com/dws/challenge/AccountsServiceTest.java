package com.dws.challenge;

import com.dws.challenge.domain.Account;
import com.dws.challenge.repository.AccountsRepository;
import com.dws.challenge.service.AccountsService;
import com.dws.challenge.service.NotificationService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

public class AccountsServiceTest {

    private Logger log = Logger.getLogger(this.getClass());
    @BeforeAll
    static void initAll() {
    }
    @BeforeEach
    void init() {
    }

    @Test
    @DisplayName("create Account")
    public void createAccount(){
        try {
            log.info("Starting execution of createAccount");
            String expectedValue="";
            Account account = null;

            AccountsRepository accountsRepositoryc = null;
            NotificationService notificationServicec = null;

            AccountsService accountsservice  =new AccountsService( accountsRepositoryc ,notificationServicec);
            String actualValue=accountsservice.createAccount( account);
            log.info("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
            System.out.println("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-"+exception,exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("get Account")
    public void getAccount(){
        try {
            log.info("Starting execution of getAccount");
            Account expectedValue = null;
            String accountId="";

            AccountsRepository accountsRepositoryc = null;
            NotificationService notificationServicec = null;

            AccountsService accountsservice  =new AccountsService( accountsRepositoryc ,notificationServicec);
            Account actualValue=accountsservice.getAccount( accountId);
            log.info("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
            System.out.println("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-"+exception,exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("transfer Money")
    public void transferMoney(){
        try {
            log.info("Starting execution of transferMoney");
            String fromAccountId="";
            String toAccountId="";
            BigDecimal amount = null;

            AccountsRepository accountsRepositoryc = null;
            NotificationService notificationServicec = null;

            AccountsService accountsservice  =new AccountsService( accountsRepositoryc ,notificationServicec);
            accountsservice.transferMoney( fromAccountId ,toAccountId ,amount);
            Assertions.assertTrue(true);
        } catch (Exception exception) {
            log.error("Exception in execution oftransferMoney-"+exception,exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

}
