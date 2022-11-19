package com.Currency.Rate.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Result {
        @JsonProperty("UAH")
        private double UAH;
}
