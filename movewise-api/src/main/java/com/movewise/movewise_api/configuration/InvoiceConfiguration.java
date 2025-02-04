package com.movewise.movewise_api.configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "invoice")
public class InvoiceConfiguration {
    private int dueDays = 14;
    private String description = "Invoice for game development services";

    // AtomicInteger for multi-thread (work asynchronously)
    private final AtomicInteger dailyCounter = new AtomicInteger(0);
    private String currentDate;

    public int getDueDays() {
        return dueDays;
    }

    public void setDueDays(int dueDays) {
        this.dueDays = dueDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Make sure only 1 thread at a time can execute this method
    public synchronized String generateInvoiceNumber() {
        LocalDate now = LocalDate.now();
        String today = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // Reset counter if the day changes
        if (!today.equals(currentDate)) {
            currentDate = today;
            dailyCounter.set(0);
        }

        int count = dailyCounter.incrementAndGet(); // Increment for each invoice today
        return String.format("INV-%s-%03d", today, count);
    }
}
