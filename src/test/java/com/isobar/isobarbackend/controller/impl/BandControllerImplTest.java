package com.isobar.isobarbackend.controller.impl;

import com.isobar.isobarbackend.model.Band;

import com.isobar.isobarbackend.model.enums.SortOrder;
import com.isobar.isobarbackend.service.BandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BandControllerImpl.class)
class BandControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BandService bandService;

    @Test
    @DisplayName("Should return 200 and filtered bands list")
    void shouldReturnFilteredBands() throws Exception {
        Band band = Band.builder()
                .id("1")
                .name("Radiohead")
                .genre("Alt Rock")
                .numPlays(5000L)
                .build();

        when(bandService.getBands(eq("radio"), eq("Alt Rock"), eq(5000L), eq(SortOrder.POPULARITY)))
                .thenReturn(List.of(band));

        mockMvc.perform(get("/api/bands")
                        .param("name", "radio")
                        .param("genre", "Alt Rock")
                        .param("minPlays", "5000")
                        .param("sort", "POPULARITY")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Radiohead"));
    }

    @Test
    @DisplayName("Should return empty list when no results are found")
    void shouldReturnEmptyList() throws Exception {
        when(bandService.getBands(anyString(), anyString(), anyLong(), any(SortOrder.class)))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/bands")
                        .param("name", "NonExistentBand")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DisplayName("Should return 400 Bad Request for invalid sort value")
    void shouldReturnBadRequestForInvalidSort() throws Exception {
        mockMvc.perform(get("/api/bands")
                        .param("sort", "INVALID_VALUE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return custom error response for invalid sort value")
    void shouldReturnCustomErrorForInvalidSort() throws Exception {
        mockMvc.perform(get("/api/bands")
                        .param("sort", "WRONG_VALUE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid value for parameter 'sort'"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.timestamp").exists());
    }

}