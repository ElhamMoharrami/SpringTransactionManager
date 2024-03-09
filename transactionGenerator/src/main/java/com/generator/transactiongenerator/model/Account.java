package com.generator.transactiongenerator.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Account {
    private final int accId;
    private final int customerId;

    @Override
    public String toString() {
        return accId + "," + customerId;
    }
}
