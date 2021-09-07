package ru.alphabank.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alphabank.testtask.client.ExchangeRateClient;
import ru.alphabank.testtask.model.ExchangeRateTimeSeriesResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeRateService {
    @Value("${appID}")
    private String appID;
    @Value("${currency}")
    private String currency;
    @Autowired
    private ExchangeRateClient exchangeRateClient;

    public boolean todayWasGreater() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate today = LocalDate.now();
        BigDecimal rateYd = getRate(yesterday);
        BigDecimal rateTd = getRate(today);
        if (rateTd.compareTo(rateYd) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private BigDecimal getRate(LocalDate day) {
        List<String> symbols = Collections.singletonList("RUB");
        ExchangeRateTimeSeriesResponse exchangeRate = exchangeRateClient.getExchangeRate(appID, day, symbols, currency);
        Map<String, BigDecimal> rates = exchangeRate.getRates();
        BigDecimal rub = rates.get("RUB");
        return rub;
    }
}
