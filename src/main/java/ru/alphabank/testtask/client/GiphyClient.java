package ru.alphabank.testtask.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "giphy-client", url = "https://api.giphy.com/v1")
public interface GiphyClient {

}
