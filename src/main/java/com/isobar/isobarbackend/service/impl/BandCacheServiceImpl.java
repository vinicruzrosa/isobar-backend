package com.isobar.isobarbackend.service.impl;

import com.isobar.isobarbackend.client.BandClient;
import com.isobar.isobarbackend.dto.BandDTO;
import com.isobar.isobarbackend.service.BandCacheService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandCacheServiceImpl implements BandCacheService {

    private final BandClient bandClient;

    public BandCacheServiceImpl(BandClient bandClient) {
        this.bandClient = bandClient;
    }

    @Override
    @Cacheable(value = "bands", unless = "#result.isEmpty()")
    public List<BandDTO> getCachedBands() {
        return bandClient.getAllBands();
    }
}