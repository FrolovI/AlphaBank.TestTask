package ru.alphabank.testtask.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alphabank.testtask.model.ExchangeRateTimeSeriesResponse;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "exchange-client", url = "https://openexchangerates.org/api")
public interface ExchangeRateClient {
    @GetMapping("/historical/{date}.json")
    ExchangeRateTimeSeriesResponse getExchangeRate(@RequestParam("app_id") String appId,
                                                   @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                   @RequestParam("symbols") List<String> symbols,
                                                   @RequestParam("base") String base);
}
