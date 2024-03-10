package com.generator.transactiongenerator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Account {
    private final int accId;
    private final int customerId;

    @Override
    public String toString() {
        return accId + "," + customerId;
    }
}
