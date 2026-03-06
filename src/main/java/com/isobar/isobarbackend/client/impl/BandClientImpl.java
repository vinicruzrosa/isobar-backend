package com.isobar.isobarbackend.client.impl;

import com.isobar.isobarbackend.client.BandClient;
import com.isobar.isobarbackend.dto.BandDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class BandClientImpl implements BandClient {

    private static final Logger log = LoggerFactory.getLogger(BandClientImpl.class);
    private final RestTemplate restTemplate;
    private final String apiUrl;

    public BandClientImpl(RestTemplate restTemplate, @Value("${api.bands.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @Override
    public List<BandDTO> getAllBands() {
        try {
            BandDTO[] response = restTemplate.getForObject(apiUrl, BandDTO[].class);
            return response != null ? Arrays.asList(response) : List.of();
        } catch (Exception e) {
            log.error("Erro ao consumir API: {}", e.getMessage());
            return List.of();
        }
    }
}