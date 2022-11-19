package com.Currency.Rate.Services;

import com.Currency.Rate.Entity.CurrencyRatio;
import com.Currency.Rate.Models.CurrencyResponse;
import com.Currency.Rate.Models.RestCurrencyResponse;
import com.Currency.Rate.Repositories.CurrencyRatioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CurrencyService {
    private final WebClient getCurrency;
    private final CurrencyRatioRepository currencyRatioRepository;

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(30);

    public CurrencyService(WebClient getCurrency, CurrencyRatioRepository currencyRatioRepository) {
        this.getCurrency = getCurrency;
        this.currencyRatioRepository = currencyRatioRepository;
    }

    public CurrencyResponse getCurrencyUSD_UAH(){
        return getCurrency
                .get()
                .retrieve()
                .bodyToFlux(CurrencyResponse.class)
                .blockLast(REQUEST_TIMEOUT);
    }

    public void setInDB(){
        var currencyResponse = getCurrencyUSD_UAH();
        var currencyRatio = responseToCurrencyRatio(currencyResponse);
        if(currencyRatio==null){log.error("Объект currencyRatio вернул Null");}
        currencyRatioRepository.save(currencyRatio);
    }

    public RestCurrencyResponse getResponseCurrencyUSD_UAH(){
        var dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        var restCurrencyResponse = new RestCurrencyResponse();
        var beginDateTime = LocalDateTime.now().minusHours(24);
        var currentRate = getCurrencyUSD_UAH();
        var averageRate = getAverageRate(beginDateTime);
        restCurrencyResponse.setCurrentRate(currentRate.getResult().getUAH());
        restCurrencyResponse.setAverageRate(averageRate);
        restCurrencyResponse.setLocalDateTime(dateFormat.format(new Date()));
        return restCurrencyResponse;
    }

    private Double getAverageRate(LocalDateTime beginDateTime) {
        return currencyRatioRepository.getAverageCurrency(beginDateTime);
    }

    // Правильно было бы сделать мапер.
    private CurrencyRatio responseToCurrencyRatio(CurrencyResponse currencyResponse){
        if (currencyResponse==null) return null;
        var currencyRatio = new CurrencyRatio();
        currencyRatio.setDateTime(LocalDateTime.now());
        currencyRatio.setRatio("USD/UAH");
        currencyRatio.setValue(currencyResponse.getResult().getUAH());
        return currencyRatio;
    }
}
