package com.isobar.isobarbackend.integration;

import com.isobar.isobarbackend.model.Band;
import com.isobar.isobarbackend.model.enums.SortOrder;
import com.isobar.isobarbackend.service.BandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class BandIntegrationTest {

    @Autowired
    private BandService bandService;

    @Test
    @DisplayName("Should integrate layers and filter by genre and popularity")
    void shouldIntegrateAndFilterCorrectly() {
        List<Band> results = bandService.getBands(null, "alternative", 1000000L, SortOrder.POPULARITY);

        assertNotNull(results);
        if (!results.isEmpty()) {
            results.forEach(band -> {
                assertEquals("alternative", band.getGenre());
                assertTrue(band.getNumPlays() >= 1000000L);
            });
        }
    }
}