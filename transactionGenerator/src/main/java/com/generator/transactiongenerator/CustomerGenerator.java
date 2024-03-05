package com.generator.transactiongenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CustomerGenerator extends Generator implements CommandLineRunner {
    @Autowired
    private AppConfig appConfig;

    @Override
    public Boolean generate() {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack"};
        String[] familyNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
        String[] postcodes = {
                "AB12 3CD", "EF45 6GH", "IJ78 9KL", "MN01 2OP", "QR34 5ST",
                "UV67 8WX", "YZ90 1AB", "CD23 4EF", "GH56 7IJ", "KL89 0MN",
                "OP12 3QR", "ST45 6UV", "WX78 9YZ", "AB90 1CD", "EF23 4GH",
                "IJ56 7KL", "MN89 0OP", "QR12 3ST", "UV45 6WX", "YZ67 8AB"
        };
        Random random = new Random();
        List<Customer> customers = new ArrayList<>(appConfig.getCustomerCount());
        for (int i = 1; i <= appConfig.getCustomerCount(); i++) {
            int randomIndex = random.nextInt(10);
            Customer customer = new Customer(i, names[randomIndex], familyNames[randomIndex], postcodes[randomIndex]);
            customers.add(customer);
        }
        for (Customer c :
                customers) {
            System.out.println(c.toString());
        }
        return true;
    }

    @Override
    public void run(String... args) throws Exception {
        generate();
    }
}