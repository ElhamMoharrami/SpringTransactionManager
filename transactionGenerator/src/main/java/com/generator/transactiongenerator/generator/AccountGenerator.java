package com.generator.transactiongenerator.generator;

import com.generator.transactiongenerator.AppConfig;
import com.generator.transactiongenerator.model.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AccountGenerator extends Generator<Account> implements CommandLineRunner {
    private final AppConfig appConfig;

    TransactionGenerator transactionGenerator;

    public AccountGenerator(AppConfig appConfig, TransactionGenerator transactionGenerator) {
        this.appConfig = appConfig;
        this.transactionGenerator = transactionGenerator;
    }

    @Override
    public List<Account> generate() {
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
        return accountList;
    }

    @Override
    public void run(String... args) {
        List<Account> accountList = generate();
        transactionGenerator.setAccountList(accountList);
        transactionGenerator.generate();
    }
}
