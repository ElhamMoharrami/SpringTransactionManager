package com.generator.transactiongenerator.generator.transactionGenerator;

import com.generator.transactiongenerator.model.Transaction;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TransactionBuilderImpl implements TransactionBuilder {
    private int id;
    private int sourceAccountId;
    private int destinationAccountId;
    private String amount;
    private String date;
    private String status;

    private Transaction transaction;

    @Override
    public TransactionBuilder withTransactionId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public TransactionBuilder withSrcAccountId(int accId) {
        this.sourceAccountId = accId;
        return this;
    }

    @Override
    public TransactionBuilder withDestAccountId(int destId) {
        this.destinationAccountId = destId;
        return this;
    }

    @Override
    public TransactionBuilder withAmount() {
        Random random = new Random();
        double amount = Math.round((random.nextDouble() * 999 + 1) * 100.0) / 100.0;
        this.amount = String.valueOf(amount);
        return this;
    }

    @Override
    public TransactionBuilder withDate() {
        long minEpoch = 0L;
        long maxEpoch = 1893456000000L; // January 1, 2030 in milliseconds
        long randomEpoch = ThreadLocalRandom.current().nextLong(minEpoch, maxEpoch);
        this.date = String.valueOf(randomEpoch);
        return this;
    }

    @Override
    public TransactionBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public Transaction build() {
        transaction=new Transaction(id,sourceAccountId,destinationAccountId,amount,date,status);
        return transaction;
    }
}
