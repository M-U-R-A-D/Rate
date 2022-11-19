package com.Currency.Rate.Controllers;

import com.Currency.Rate.Models.RestCurrencyResponse;
import com.Currency.Rate.Services.CurrencyService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.atomic.AtomicReference;




@RestController
public class CurrencyController {
private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency")
    public DeferredResult<String> getUSD_UAH(){
        DeferredResult<String> result =new DeferredResult<>(5000L);
        RestCurrencyResponse restCurrencyResponse = currencyService.getResponseCurrencyUSD_UAH();
        if(restCurrencyResponse!=null){
            result.setResult(restCurrencyResponse.toString());
        }else result.setErrorResult("null");
        return result;
    }
}
