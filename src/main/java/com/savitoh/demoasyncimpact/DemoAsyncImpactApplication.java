package com.savitoh.demoasyncimpact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@SpringBootApplication
public class DemoAsyncImpactApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoAsyncImpactApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoAsyncImpactApplication.class, args);
	}

	@Bean
	public Executor asyncTaskExecutor() {
		LOGGER.info("Creating Async Task Executor");
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);  // create 2 Threads at the time of initialization
		executor.setQueueCapacity(10); // queue capacity
		executor.setMaxPoolSize(4);   // if queue is full, then it will create new thread and go till 4
		executor.setThreadNamePrefix("asynctaskpool-");
		executor.initialize();
		return executor;
	}

}
