package com.loader.loader.services;

import com.loader.loader.model.Account;
import com.loader.loader.model.Customer;
import com.loader.loader.model.Transaction;
import com.loader.loader.repositories.AccountRespository;
import com.loader.loader.repositories.CustomerRespository;
import com.loader.loader.repositories.TransactionRepository;
import com.loader.loader.util.config.AppProperties;
import jakarta.transaction.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileProcessor implements Runnable {
    private final File file;

    private TransactionRepository transactionRepository;

    private CustomerRespository customerRespository;

    private AccountRespository accountRespository;

    private final AppProperties appProperties;

    public FileProcessor(File file, TransactionRepository transactionRepository, AppProperties appProperties) {
        this.file = file;
        this.transactionRepository = transactionRepository;
        this.appProperties = appProperties;
    }

    public FileProcessor(File file, CustomerRespository customerRespository, AppProperties appProperties) {
        this.file = file;
        this.customerRespository = customerRespository;
        this.appProperties = appProperties;
    }

    public FileProcessor(File file, AccountRespository accountRespository, AppProperties appProperties) {
        this.file = file;
        this.accountRespository = accountRespository;
        this.appProperties = appProperties;
    }


    @Transactional
    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                if (file.getName().contains("transaction")) {
                    Transaction transaction = new Transaction();
                    transaction.setId(Integer.parseInt(data[0]));
                    transaction.setSourceAccountId(Integer.parseInt(data[1]));
                    transaction.setDestinationAccountId(Integer.parseInt(data[2]));
                    transaction.setAmount(data[3]);
                    transaction.setDate(data[4]);
                    transaction.setStatus(data[5]);
                    transactionRepository.save(transaction);
                }

                if (file.getName().contains("customer")) {
                    Customer customer = new Customer();
                    customer.setId(Integer.parseInt(data[0]));
                    customer.setFirstName(data[1]);
                    customer.setLastName(data[2]);
                    customer.setPostCode(data[3]);
                    customerRespository.save(customer);
                }

                if (file.getName().contains("account")) {
                    Account account = new Account();
                    account.setAccId(Integer.parseInt(data[0]));
                    Customer customer = new Customer();
                    customer.setId(Integer.parseInt(data[1]));
                    account.setCustomer(customer);
                    accountRespository.save(account);
                }
            }
            File newFile = rename(file);
            moveFile(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveFile(File file) {
        File destinationDirectory = null;
        if (file.getName().contains("transaction")) {
            destinationDirectory = new File(appProperties.getTransactionsOutputPath());
        }

        if (file.getName().contains("customer")) {
            destinationDirectory = new File(appProperties.getCustomerOutputPath());
        }

        if (file.getName().contains("account")) {
            destinationDirectory = new File(appProperties.getAccountOutputPath());
        }
        try {
            assert destinationDirectory != null;
            Files.move(file.toPath(), destinationDirectory.toPath().resolve(file.getName()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File rename(File file) {
        String fileName = file.getName();
        if (fileName.endsWith(".processing")) {
            String newName = fileName.substring(0, fileName.lastIndexOf(".processing"));
            String parent = file.getParent();
            return new File(parent, newName);
        } else {
            return file;
        }
    }
}
