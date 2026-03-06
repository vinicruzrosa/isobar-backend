package com.isobar.isobarbackend.client;

import com.isobar.isobarbackend.dto.BandDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Component
public class BandClient {

    private final RestTemplate restTemplate;

    @Value("${api.bands.url}")
    private String apiUrl;

    public BandClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BandDTO> getAllBands() {
        BandDTO[] response = restTemplate.getForObject(apiUrl, BandDTO[].class);
        return response != null ? Arrays.asList(response) : List.of();
    }
}