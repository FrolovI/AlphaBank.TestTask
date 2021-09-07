package ru.alphabank.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alphabank.testtask.client.GiphyClient;

@Service
public class GiphyService {
    @Value("${ghifsAPI}")
    private String apiKey;
    @Autowired
    private GiphyClient giphyClient;
    @Autowired
    private ExchangeRateService exchangeRateService;

    public String getGiphyURL() {
        if (exchangeRateService.todayWasGreater()) {
            return giphyClient.getRandomGiphy(apiKey, "rich").getData().getUrl();
        } else {
            return giphyClient.getRandomGiphy(apiKey, "broke").getData().getUrl();
        }
    }
}
