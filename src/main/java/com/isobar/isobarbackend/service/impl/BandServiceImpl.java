package com.isobar.isobarbackend.service.impl;

import com.isobar.isobarbackend.dto.BandDTO;
import com.isobar.isobarbackend.model.Band;

import com.isobar.isobarbackend.model.enums.SortOrder;
import com.isobar.isobarbackend.service.BandCacheService;
import com.isobar.isobarbackend.service.BandService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BandServiceImpl implements BandService {

    private final BandCacheService cacheService;

    public BandServiceImpl(BandCacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public List<Band> getBands(String name, String genre, Long minPlays, SortOrder sort) {
        List<BandDTO> dtos = cacheService.getCachedBands();

        return dtos.stream()
                .map(this::mapToModel)
                .filter(b -> isNameMatch(b, name))
                .filter(b -> isGenreMatch(b, genre))
                .filter(b -> isPopularityMatch(b, minPlays))
                .sorted(getComparator(sort))
                .toList();
    }

    private boolean isNameMatch(Band b, String name) {
        return name == null || name.isBlank() ||
                (b.getName() != null && b.getName().toLowerCase().contains(name.toLowerCase()));
    }

    private boolean isGenreMatch(Band b, String genre) {
        return genre == null || genre.isBlank() ||
                (b.getGenre() != null && b.getGenre().equalsIgnoreCase(genre));
    }

    private boolean isPopularityMatch(Band b, Long minPlays) {
        return minPlays == null || (b.getNumPlays() != null && b.getNumPlays() >= minPlays);
    }

    private Comparator<Band> getComparator(SortOrder sort) {
        if (SortOrder.POPULARITY.equals(sort)) {
            return Comparator.comparing(Band::getNumPlays, Comparator.nullsLast(Comparator.naturalOrder())).reversed();
        }
        return Comparator.comparing(Band::getName, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
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