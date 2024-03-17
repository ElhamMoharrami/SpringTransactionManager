package com.loader.loader.services;

import com.loader.loader.repositories.AccountRespository;
import com.loader.loader.repositories.CustomerRespository;
import com.loader.loader.repositories.TransactionRepository;
import com.loader.loader.util.config.AppProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class Observer implements CommandLineRunner {
    private final TransactionRepository transactionRepository;

    private final CustomerRespository customerRespository;
    private final AccountRespository accountRespository;
    private final ThreadPoolExecutor executor;

    private final AppProperties appProperties;

    public Observer(TransactionRepository transactionRepository, CustomerRespository customerRespository,
                    AccountRespository accountRespository, ThreadPoolExecutor executor, AppProperties appProperties) {
        this.transactionRepository = transactionRepository;
        this.customerRespository = customerRespository;
        this.accountRespository = accountRespository;
        this.executor = executor;
        this.appProperties = appProperties;
    }

    private File renameFile(File file, String newName) {
        String parentDirectory = file.getParent();
        File newFile = new File(parentDirectory + File.separator + newName);
        boolean success = file.renameTo(newFile);
        if (success) {
            return newFile;
        } else {
            System.out.println("Failed to rename the file.");
            return null;
        }
    }

    @Override
    public void run(String... args) {
        File transactionFolder = new File(appProperties.getTransactionsInputPath());
        File customerFolder = new File(appProperties.getCustomerInputPath());
        File accountFolder = new File(appProperties.getAccountInputPath());
        while (true) {
            File[] transactionFiles = transactionFolder.listFiles((dir, name) -> !name.endsWith(".processing"));
            File[] customerFiles = customerFolder.listFiles((dir, name) -> !name.endsWith(".processing"));
            File[] accountFiles = accountFolder.listFiles((dir, name) -> !name.endsWith(".processing"));

            assert transactionFiles != null;
            for (File file : transactionFiles) {
                if (file.isFile()) {
                    File newFile = renameFile(file, file.getName() + ".processing");
                    executor.submit(new FileProcessor(newFile, transactionRepository, appProperties));
                }
            }
            assert customerFiles != null;
            for (File file : customerFiles) {
                if (file.isFile()) {
                    File newFile = renameFile(file, file.getName() + ".processing");
                    executor.submit(new FileProcessor(newFile, customerRespository, appProperties));
                }
            }
            assert accountFiles != null;
            for (File file : accountFiles) {
                if (file.isFile()) {
                    File newFile = renameFile(file, file.getName() + ".processing");
                    executor.submit(new FileProcessor(newFile, accountRespository, appProperties));
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

