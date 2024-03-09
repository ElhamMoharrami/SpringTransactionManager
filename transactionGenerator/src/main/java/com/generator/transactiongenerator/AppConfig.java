package com.generator.transactiongenerator;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppConfig {
    @Value("${transaction.manager.generator.customer.count}")
    private int customerCount;

    @Value("${transaction.manager.generator.account.max}")
    private int maxNumberOfAccountsPerCustomer;

    @Value("${transaction.manager.generator.customer.destination}")
    private String customersFilePath;

    @Value("${transaction.manager.generator.account.destination}")
    private String accountsFilePath;
}
