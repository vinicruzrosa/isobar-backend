package com.isobar.isobarbackend.service;

import com.isobar.isobarbackend.client.BandClient;
import com.isobar.isobarbackend.dto.BandDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BandCacheServiceTest {

    @Mock
    private BandClient bandClient;

    @InjectMocks
    private BandCacheService bandCacheService;

    @Test
    void deveRetornarListaDeBandas() {
        BandDTO mockBand = new BandDTO(
                "1", "Pink Floyd", "image.png", "Rock", "Bio", 1000L, List.of("album1")
        );
        List<BandDTO> expectedBands = List.of(mockBand);

        when(bandClient.getAllBands()).thenReturn(expectedBands);

        List<BandDTO> result = bandCacheService.getCachedBands();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Pink Floyd", result.get(0).name());

        verify(bandClient, times(1)).getAllBands();
    }
}