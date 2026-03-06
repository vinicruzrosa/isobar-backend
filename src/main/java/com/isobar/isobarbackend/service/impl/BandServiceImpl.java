package com.isobar.isobarbackend.service.impl;

import com.isobar.isobarbackend.model.Band;
import com.isobar.isobarbackend.dto.BandDTO;
import com.isobar.isobarbackend.service.BandCacheService;
import com.isobar.isobarbackend.service.BandService;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BandServiceImpl implements BandService {

    private final BandCacheService cacheService;

    public BandServiceImpl(BandCacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public List<Band> getBands(String name, String sort) {
        List<BandDTO> dtos = cacheService.getCachedBands();

        List<Band> bands = dtos.stream()
                .map(this::mapToModel)
                .collect(Collectors.toList());

        if (name != null && !name.isEmpty()) {
            bands = bands.stream()
                    .filter(b -> b.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if ("alphabetical".equalsIgnoreCase(sort)) {
            bands.sort(Comparator.comparing(Band::getName));
        } else if ("popularity".equalsIgnoreCase(sort)) {
            bands.sort(Comparator.comparing(Band::getNumPlays).reversed());
        }

        return bands;
    }

    private Band mapToModel(BandDTO dto) {
        return Band.builder()
                .id(dto.id())
                .name(dto.name())
                .numPlays(dto.numPlays())
                .biography(dto.biography())
                .image(dto.image())
                .genre(dto.genre())
                .albums(dto.albums())
                .build();
    }
}