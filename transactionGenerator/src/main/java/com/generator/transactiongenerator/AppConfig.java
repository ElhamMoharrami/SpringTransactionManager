package com.generator.transactiongenerator;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppConfig {
    @Value("${transaction.manager.generator.customer.count}")
    private int customerCount;
}
