package com.generator.transactiongenerator.generator;

import com.generator.transactiongenerator.AppConfig;
import com.generator.transactiongenerator.model.Account;
import com.generator.transactiongenerator.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class TransactionGenerator extends Generator<Transaction> {

    private final AppConfig appConfig;
    private List<Account> accountList;

    public TransactionGenerator(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    List<Transaction> generate() {
        List<Transaction> transactions = new ArrayList<>();
        int maxTransactionPerAccount = 50;
        int transactionId = 0;
        for (Account acc : accountList) {
            Random random = new Random();
            int numberOfTransactions = random.nextInt(maxTransactionPerAccount);
            int destination = random.nextInt(accountList.size());
            String status = "success";
            if (destination == acc.getAccId()) {
                status = "fail";
            }
            double amount = Math.round((random.nextDouble() * 999 + 1) * 100.0) / 100.0;
            long minEpoch = 0L;
            long maxEpoch = 1893456000000L; // January 1, 2030 in milliseconds
            long randomEpoch = ThreadLocalRandom.current().nextLong(minEpoch, maxEpoch);
            for (int i = 0; i < numberOfTransactions; i++) {
                Transaction transaction = new Transaction(transactionId, acc.getAccId(),
                        accountList.get(destination).getAccId(), Double.toString(amount),
                        Long.toString(randomEpoch), status);
                transactions.add(transaction);
                transactionId++;
            }
        }
        writeToFile(transactions, 500, "id,source_acc,destination_acc,amount,date",
                appConfig.getTransactionFilePath());

        return transactions;
    }
}
