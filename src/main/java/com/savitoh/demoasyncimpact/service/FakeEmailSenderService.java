package com.savitoh.demoasyncimpact.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FakeEmailSenderService {

    private static final int DELAY_IN_MILLIS_SECONDS = 2000;

    private static final Logger LOGGER = LoggerFactory.getLogger(FakeEmailSenderService.class);

    public void send() throws InterruptedException {
        LOGGER.info("fake send EMAILS starts.");
        Thread.sleep(DELAY_IN_MILLIS_SECONDS); //Intentional delay
        LOGGER.info("fake send EMAILS completed.");
    }

}
