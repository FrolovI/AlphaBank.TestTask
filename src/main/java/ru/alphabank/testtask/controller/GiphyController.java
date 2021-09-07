package ru.alphabank.testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alphabank.testtask.service.GiphyService;

@RestController
public class GiphyController {
    @Autowired
    private GiphyService giphyService;
    @GetMapping("/get_giphy")
    public String getGiphyURL(){
        return giphyService.getGiphyURL();
    }
}
