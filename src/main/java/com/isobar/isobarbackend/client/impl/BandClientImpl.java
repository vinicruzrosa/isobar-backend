package com.isobar.isobarbackend.client.impl;

import com.isobar.isobarbackend.client.BandClient;
import com.isobar.isobarbackend.dto.BandDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Component
public class BandClientImpl implements BandClient {

    private final RestTemplate restTemplate;

    @Value("${api.bands.url}")
    private String apiUrl;

    public BandClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BandDTO> getAllBands() {
        BandDTO[] response = restTemplate.getForObject(apiUrl, BandDTO[].class);
        return response != null ? Arrays.asList(response) : List.of();
    }
}