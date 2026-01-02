package com.duckdrunk.literalura.component;

import com.duckdrunk.literalura.dto.GutendexResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GutendexClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "https://gutendex.com/books/?page=";

    public GutendexResponse getBooks(int id) {
        return restTemplate.getForObject(URL+id, GutendexResponse.class);
    }
}
