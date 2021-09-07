package ru.alphabank.testtask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateTimeSeriesResponse {
    private Map<String, BigDecimal> rates;
}
