package com.loader.loader.util.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppProperties {
    @Value("${transaction.loader.db.transaction.input.directory}")
    private String transactionsInputPath;
    @Value("${transaction.loader.db.customer.input.directory}")
    private String customerInputPath;
    @Value("${transaction.loader.db.account.input.directory}")
    private String accountInputPath;
    @Value("${transaction.loader.db.transaction.output.directory}")
    private String transactionsOutputPath;
    @Value("${transaction.loader.db.customer.output.directory}")
    private String customerOutputPath;
    @Value("${transaction.loader.db.account.output.directory}")
    private String accountOutputPath;
}
