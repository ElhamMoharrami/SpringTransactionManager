package com.generator.transactiongenerator.generator.transactionGenerator;

import com.generator.transactiongenerator.AppProperties;
import com.generator.transactiongenerator.generator.Generator;
import com.generator.transactiongenerator.model.Account;
import com.generator.transactiongenerator.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class TransactionGenerator extends Generator<Transaction> {

    private final AppProperties appConfig;
    private List<Account> accountList;

    public TransactionGenerator(AppProperties appConfig) {
        this.appConfig = appConfig;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public List<Transaction> generate() {
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
            for (int i = 0; i < numberOfTransactions; i++) {
                TransactionBuilder transactionBuilder = new TransactionBuilderImpl();
                Transaction transaction = transactionBuilder.withTransactionId(transactionId)
                        .withSrcAccountId(acc.getAccId())
                        .withDestAccountId(accountList.get(destination).getAccId())
                        .withAmount().withDate().withStatus(status).build();
                transactions.add(transaction);
                transactionId++;
            }
        }
        writeToFile(transactions, 500, "id,source_acc,destination_acc,amount,date",
                appConfig.getTransactionFilePath());

        return transactions;
    }
}
