package com.generator.transactiongenerator;

import org.springframework.stereotype.Component;

@Component
public abstract class Generator {
 abstract Boolean generate();
}
