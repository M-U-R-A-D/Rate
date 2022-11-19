package com.Currency.Rate.Models;

import lombok.Setter;

import javax.servlet.http.PushBuilder;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;


public class RestCurrencyResponse {




    private double currentRate;
    private double averageRate;
    private String localDateTime;
    DecimalFormat f = new DecimalFormat("##.00");
    public void setCurrentRate(double currentRate) {
        BigDecimal bigDecimal = new BigDecimal(currentRate);
        this.currentRate = bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public void setAverageRate(double averageRate) {
        BigDecimal bigDecimal = new BigDecimal(averageRate);
        this.averageRate = bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString(){
        return "Текущий курс: "+currentRate+" Средний курс: "+averageRate+" Дата: "+localDateTime;
    }
}
