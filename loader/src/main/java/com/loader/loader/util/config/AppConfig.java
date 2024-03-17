package com.loader.loader.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfig {
    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        int corePoolSize = 3;
        int maximumPoolSize = 3;
        long keepAliveTime = 10000;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(50);

        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, queue);
    }
}
