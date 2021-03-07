package com.savitoh.demoasyncimpact.api;


import com.savitoh.demoasyncimpact.service.FakeEmailSenderService;
import com.savitoh.demoasyncimpact.service.FakePaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/purchases")
public class PurchasesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchasesController.class);

    private final FakePaymentService fakePaymentService;

    private final FakeEmailSenderService fakeEmailSenderService;


    public PurchasesController(FakePaymentService fakePaymentService, FakeEmailSenderService fakeEmailSenderService) {
        this.fakePaymentService = fakePaymentService;
        this.fakeEmailSenderService = fakeEmailSenderService;
    }

    @PostMapping
    public Reponse buy() throws InterruptedException {
        final long startInMilliSeconds = Instant.now().toEpochMilli();
        LOGGER.info("Starting time: {}", startInMilliSeconds);
        final CompletableFuture<Integer> timeToPay = fakePaymentService.pay();
        final CompletableFuture<Integer> timeToSendEmail = fakeEmailSenderService.send();
        CompletableFuture.allOf(timeToPay, timeToSendEmail).join();
        final long elapsedTime = Instant.now().toEpochMilli() - startInMilliSeconds;
        LOGGER.info("Elapsed time: {}", elapsedTime);
        return new Reponse(elapsedTime);
    }

    /**
     * For demo only. Avoid this practice =)
     */
    private static class Reponse {

        private final long timeToResponseInMilliSeconds;

        private final String message;

        public Reponse(long timeToResponse) {
            this.timeToResponseInMilliSeconds = timeToResponse;
            this.message = "Success!";
        }

        public long getTimeToResponseInMilliSeconds() {
            return timeToResponseInMilliSeconds;
        }

        public String getMessage() {
            return message;
        }
    }
}
