package ru.alphabank.testtask.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alphabank.testtask.model.GiphyRandomResponse;

@FeignClient(name = "giphy-client", url = "https://api.giphy.com/v1")
public interface GiphyClient {
    @GetMapping("/gifs/random")
    GiphyRandomResponse getRandomGiphy(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);
}
