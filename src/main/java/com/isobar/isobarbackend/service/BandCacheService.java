package com.isobar.isobarbackend.service;

import com.isobar.isobarbackend.client.BandClient;
import com.isobar.isobarbackend.dto.BandDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BandCacheService {

    private final BandClient bandClient;

    public BandCacheService(BandClient bandClient) {
        this.bandClient = bandClient;
    }

    @Cacheable("bands")
    public List<BandDTO> getCachedBands() {
        return bandClient.getAllBands();
    }
}