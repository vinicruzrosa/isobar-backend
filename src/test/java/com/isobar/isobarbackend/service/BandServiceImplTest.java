package com.isobar.isobarbackend.service;

import com.isobar.isobarbackend.dto.BandDTO;
import com.isobar.isobarbackend.model.Band;
import com.isobar.isobarbackend.service.impl.BandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BandServiceImplTest {

    @Mock
    private BandCacheService cacheService;

    @InjectMocks
    private BandServiceImpl bandService;

    private List<BandDTO> mockDtos;

    @BeforeEach
    void setUp() {
        mockDtos = List.of(
                new BandDTO("1", "Radiohead", "url1", "Alt Rock", "Bio1", 5000L, List.of("OK Computer")),
                new BandDTO("2", "Pink Floyd", "url2", "Prog Rock", "Bio2", 9000L, List.of("The Wall")),
                new BandDTO("3", "Muse", "url3", "Space Rock", "Bio3", 7000L, List.of("Origin of Symmetry"))
        );
    }

    @Test
    @DisplayName("Deve filtrar bandas pelo nome ignorando case")
    void deveFiltrarPorNome() {
        when(cacheService.getCachedBands()).thenReturn(mockDtos);

        List<Band> result = bandService.getBands("pink", null);

        assertEquals(1, result.size());
        assertEquals("Pink Floyd", result.get(0).getName());
    }

    @Test
    @DisplayName("Deve ordenar bandas por popularidade (numPlays) decrescente")
    void deveOrdenarPorPopularidade() {
        when(cacheService.getCachedBands()).thenReturn(mockDtos);

        List<Band> result = bandService.getBands(null, "popularity");

        assertEquals(3, result.size());
        assertEquals("Pink Floyd", result.get(0).getName());
        assertEquals("Muse", result.get(1).getName());
        assertEquals("Radiohead", result.get(2).getName());
    }

    @Test
    @DisplayName("Deve ordenar bandas alfabeticamente")
    void deveOrdenarAlfabeticamente() {
        when(cacheService.getCachedBands()).thenReturn(mockDtos);

        List<Band> result = bandService.getBands(null, "alphabetical");

        assertEquals("Muse", result.get(0).getName());
        assertEquals("Pink Floyd", result.get(1).getName());
        assertEquals("Radiohead", result.get(2).getName());
    }

    @Test
    @DisplayName("Deve retornar todas as bandas mapeadas quando filtros forem nulos")
    void deveRetornarTodasAsBands() {
        when(cacheService.getCachedBands()).thenReturn(mockDtos);

        List<Band> result = bandService.getBands(null, null);

        assertEquals(3, result.size());
        assertNotNull(result.get(0).getName());
    }
}