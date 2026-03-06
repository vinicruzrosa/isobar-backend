package com.isobar.isobarbackend.service.impl;

import com.isobar.isobarbackend.client.impl.BandClientImpl;
import com.isobar.isobarbackend.dto.BandDTO;
import com.isobar.isobarbackend.service.BandCacheService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandCacheServiceImpl implements BandCacheService {

    private final BandClientImpl bandClient;

    public BandCacheServiceImpl(BandClientImpl bandClient) {
        this.bandClient = bandClient;
    }

    @Override
    @Cacheable("bands")
    public List<BandDTO> getCachedBands() {
        return bandClient.getAllBands();
    }
}