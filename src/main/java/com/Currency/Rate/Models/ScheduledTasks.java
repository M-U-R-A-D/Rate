package com.Currency.Rate.Models;

import com.Currency.Rate.Services.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class ScheduledTasks {

    private final CurrencyService currencyService;


    public ScheduledTasks(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Scheduled(initialDelay = 5000,fixedRate = 30000)
    public void reportCurrentTime() {
        currencyService.setInDB();
        log.info( currencyService.getResponseCurrencyUSD_UAH().toString());
    }
}