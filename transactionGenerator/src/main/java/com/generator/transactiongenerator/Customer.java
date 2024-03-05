package com.generator.transactiongenerator;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class Customer {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String postCode;
}
