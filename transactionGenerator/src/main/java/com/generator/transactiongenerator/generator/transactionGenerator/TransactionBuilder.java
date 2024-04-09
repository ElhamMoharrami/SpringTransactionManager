package com.generator.transactiongenerator.generator.transactionGenerator;

import com.generator.transactiongenerator.model.Transaction;

public interface TransactionBuilder {
    TransactionBuilder withTransactionId(int id);

    TransactionBuilder withSrcAccountId(int accId);

    TransactionBuilder withDestAccountId(int destId);

    TransactionBuilder withAmount();

    TransactionBuilder withDate();

    TransactionBuilder withStatus(String status);

    Transaction build();

}
