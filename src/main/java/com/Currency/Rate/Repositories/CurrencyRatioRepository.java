package com.Currency.Rate.Repositories;

import com.Currency.Rate.Entity.CurrencyRatio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CurrencyRatioRepository extends JpaRepository<CurrencyRatio, Long> {
    @Query(value = "SELECT AVG(cr.\"value\") FROM public.currency_ratio cr WHERE cr.date_time > :bd ",nativeQuery = true)
    Double getAverageCurrency(@Param("bd") LocalDateTime beginDateTime);

}
