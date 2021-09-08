package ru.alphabank.testtask;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.alphabank.testtask.client.ExchangeRateClient;
import ru.alphabank.testtask.client.GiphyClient;
import ru.alphabank.testtask.model.ExchangeRateTimeSeriesResponse;
import ru.alphabank.testtask.model.GiphyRandomDataResponse;
import ru.alphabank.testtask.model.GiphyRandomResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GiphyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ExchangeRateClient exchangeRateClient;
    @MockBean
    GiphyClient giphyClient;

    @Test
    public void getGiphyRich() throws Exception {
        ExchangeRateTimeSeriesResponse exchangeRateTimeSeriesResponseYD = new ExchangeRateTimeSeriesResponse();
        ExchangeRateTimeSeriesResponse exchangeRateTimeSeriesResponseTD = new ExchangeRateTimeSeriesResponse();
        GiphyRandomResponse giphyRandomResponse = new GiphyRandomResponse();
        GiphyRandomDataResponse data = new GiphyRandomDataResponse();
        data.setUrl("rich");
        giphyRandomResponse.setData(data);
        Map<String, BigDecimal> ratesYD = new HashMap<>();
        ratesYD.put("RUB", BigDecimal.valueOf(0.9));
        exchangeRateTimeSeriesResponseYD.setRates(ratesYD);
        Map<String, BigDecimal> ratesTD = new HashMap<>();
        ratesTD.put("RUB", BigDecimal.valueOf(1.2));
        exchangeRateTimeSeriesResponseTD.setRates(ratesTD);
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate today = LocalDate.now();

        Mockito.when(exchangeRateClient.getExchangeRate(ArgumentMatchers.anyString(), ArgumentMatchers.eq(yesterday)
                , ArgumentMatchers.anyList(), ArgumentMatchers.anyString())).thenReturn(exchangeRateTimeSeriesResponseYD);
        Mockito.when(exchangeRateClient.getExchangeRate(ArgumentMatchers.anyString(), ArgumentMatchers.eq(today)
                , ArgumentMatchers.anyList(), ArgumentMatchers.anyString())).thenReturn(exchangeRateTimeSeriesResponseTD);
        Mockito.when(giphyClient.getRandomGiphy(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(giphyRandomResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/get_giphy")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("rich")));

        Mockito.verify(giphyClient).getRandomGiphy(ArgumentMatchers.anyString(), ArgumentMatchers.eq("rich"));
    }

    @Test
    public void getGiphyBroke() throws Exception {
        ExchangeRateTimeSeriesResponse exchangeRateTimeSeriesResponseYD = new ExchangeRateTimeSeriesResponse();
        ExchangeRateTimeSeriesResponse exchangeRateTimeSeriesResponseTD = new ExchangeRateTimeSeriesResponse();
        GiphyRandomResponse giphyRandomResponse = new GiphyRandomResponse();
        GiphyRandomDataResponse data = new GiphyRandomDataResponse();
        data.setUrl("broke");
        giphyRandomResponse.setData(data);
        Map<String, BigDecimal> ratesYD = new HashMap<>();
        ratesYD.put("RUB", BigDecimal.valueOf(1.2));
        exchangeRateTimeSeriesResponseYD.setRates(ratesYD);
        Map<String, BigDecimal> ratesTD = new HashMap<>();
        ratesTD.put("RUB", BigDecimal.valueOf(0.9));
        exchangeRateTimeSeriesResponseTD.setRates(ratesTD);
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate today = LocalDate.now();

        Mockito.when(exchangeRateClient.getExchangeRate(ArgumentMatchers.anyString(), ArgumentMatchers.eq(yesterday)
                , ArgumentMatchers.anyList(), ArgumentMatchers.anyString())).thenReturn(exchangeRateTimeSeriesResponseYD);
        Mockito.when(exchangeRateClient.getExchangeRate(ArgumentMatchers.anyString(), ArgumentMatchers.eq(today)
                , ArgumentMatchers.anyList(), ArgumentMatchers.anyString())).thenReturn(exchangeRateTimeSeriesResponseTD);
        Mockito.when(giphyClient.getRandomGiphy(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(giphyRandomResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/get_giphy")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("broke")));

        Mockito.verify(giphyClient).getRandomGiphy(ArgumentMatchers.anyString(), ArgumentMatchers.eq("broke"));
    }
}
