package com.isobar.isobarbackend.service;

import com.isobar.isobarbackend.dto.BandDTO;
import com.isobar.isobarbackend.model.Band;
import com.isobar.isobarbackend.service.impl.BandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BandServiceImplTest {

    @Mock
    private BandCacheService cacheService;
    private BandServiceImpl bandService;

    @BeforeEach
    void setUp() {
        bandService = new BandServiceImpl(cacheService);
        List<BandDTO> mockDtos = List.of(
                new BandDTO("1", "Radiohead", "url", "Alt Rock", "Bio", 5000L, List.of()),
                new BandDTO("2", "Pink Floyd", "url", "Prog Rock", "Bio", 9000L, List.of())
        );
        when(cacheService.getCachedBands()).thenReturn(mockDtos);
    }

    @Test
    void shouldFilterByGenre() {
        List<Band> result = bandService.getBands(null, "Prog Rock", 8000L, null);
        assertEquals(1, result.size());
        assertEquals("Pink Floyd", result.get(0).getName());
    }
}