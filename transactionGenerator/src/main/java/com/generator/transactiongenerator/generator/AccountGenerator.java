package com.generator.transactiongenerator.generator;

import com.generator.transactiongenerator.AppConfig;
import com.generator.transactiongenerator.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AccountGenerator extends Generator<Account> implements CommandLineRunner {
    @Autowired
    private AppConfig appConfig;

    @Override
    public Boolean generate() {
        Random random = new Random();
        int accountId = 0;
        List<Account> accountList = new ArrayList<>();

        for (int i = 1; i <= appConfig.getCustomerCount(); i++) {
            int randomNumberOfAccounts = random.nextInt(appConfig.getMaxNumberOfAccountsPerCustomer()) + 1;
            for (int j = 0; j < randomNumberOfAccounts; j++) {
                Account account = new Account(accountId, i);
                accountList.add(account);
                accountId++;
            }
        }
        writeToFile(accountList, accountList.size(), "account_id,customer_id", appConfig.getAccountsFilePath());
        return true;
    }

    @Override
    public void run(String... args) throws Exception {
        generate();
    }
}
