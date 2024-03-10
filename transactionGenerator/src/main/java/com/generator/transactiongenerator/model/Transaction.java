package com.generator.transactiongenerator.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Transaction {
    private final int id;
    private final int sourceAccountId;
    private final int destinationAccountId;
    private final String amount;
    private final String date;

    private final String status;

    @Override
    public String toString() {
        return id +
                "," + sourceAccountId +
                "," + destinationAccountId +
                "," + amount +
                "," + date +
                "," + status;
    }
}
