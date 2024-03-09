package com.generator.transactiongenerator.model;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
public class Customer {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String postCode;

    @Override
    public String toString() {
        return id + "," + firstName + "," + lastName + "," + postCode;
    }
}
