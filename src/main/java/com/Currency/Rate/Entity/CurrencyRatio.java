package com.Currency.Rate.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class CurrencyRatio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ratio;
    private Double value;
    private LocalDateTime dateTime;

}
