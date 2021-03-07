package com.savitoh.demoasyncimpact.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class ConfigAsyncTaskExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigAsyncTaskExecutor.class);

    @Value("${corePoolSize:2}")
    private Integer corePoolSize;

    @Value("${queueCapacity:10}")
    private Integer queueCapacity;

    @Value("${maxPoolSize:4}")
    private Integer maxPoolSize;

    @Bean
    public Executor asyncTaskExecutor() {
        LOGGER.info(
                "Creating Async Task Executor with configs: core-pool-size: {} queue-capacity: {} max-pool-size: {}",
                corePoolSize, queueCapacity, maxPoolSize
        );
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(this.corePoolSize);  // create 2 Threads at the time of initialization
        executor.setQueueCapacity(this.queueCapacity); // queue capacity
        executor.setMaxPoolSize(this.maxPoolSize);   // if queue is full, then it will create new thread and go till 4
        executor.setThreadNamePrefix("asynctaskpool-");
        executor.initialize();
        return executor;
    }
}
