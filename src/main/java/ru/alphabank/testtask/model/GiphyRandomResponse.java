package ru.alphabank.testtask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GiphyRandomResponse {
    private GiphyRandomDataResponse data;
}
