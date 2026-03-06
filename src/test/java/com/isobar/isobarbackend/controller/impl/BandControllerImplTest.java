package com.isobar.isobarbackend.controller.impl;

import com.isobar.isobarbackend.model.Band;
import com.isobar.isobarbackend.service.BandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean; // Novo Import
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BandControllerImpl.class)
class BandControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // Substitui o deprecated @MockBean
    private BandService bandService;

    @Test
    @DisplayName("Deve retornar 200 e lista de bandas filtrada")
    void deveRetornarBandasFiltradas() throws Exception {
        Band band = Band.builder()
                .id("1")
                .name("Radiohead")
                .genre("Alt Rock")
                .numPlays(5000L)
                .build();

        when(bandService.getBands(eq("radio"), eq("Alt Rock"), eq(5000L), eq("popularity")))
                .thenReturn(List.of(band));

        mockMvc.perform(get("/api/bands")
                        .param("name", "radio")
                        .param("genre", "Alt Rock")
                        .param("minPlays", "5000")
                        .param("sort", "popularity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Radiohead"));
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando o service não encontrar resultados")
    void deveRetornarVazio() throws Exception {
        when(bandService.getBands(anyString(), anyString(), anyLong(), anyString()))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/bands")
                        .param("name", "BandaInexistente")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}