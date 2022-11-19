package com.Currency.Rate.Models;

import lombok.Data;

@Data
public class CurrencyResponse {
    private String base;
    private Result result;
    private String updated;
    private int ms;
}
