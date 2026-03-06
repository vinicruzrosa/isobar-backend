package com.isobar.isobarbackend.client;

import com.isobar.isobarbackend.client.impl.BandClientImpl;
import com.isobar.isobarbackend.config.RestTemplateConfig;
import com.isobar.isobarbackend.dto.BandDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(BandClientImpl.class)
@ActiveProfiles("test")
@Import(RestTemplateConfig.class)
class BandClientTest {

    @Autowired
    private BandClient bandClient;

    @Autowired
    private MockRestServiceServer server;

    @Value("${api.bands.url}")
    private String apiUrl;

    @Test
    @DisplayName("Deve retornar lista de bandas consumindo arquivo JSON de mock")
    void deveRetornarListaDeBandsQuandoApiExternaResponderSucesso() {
        server.expect(requestTo(apiUrl))
                .andRespond(withSuccess(
                        new ClassPathResource("mocks/bands.json"),
                        MediaType.APPLICATION_JSON
                ));

        List<BandDTO> result = bandClient.getAllBands();

        assertNotNull(result);
        assertFalse(result.isEmpty());

        BandDTO firstBand = result.get(0);
        assertNotNull(firstBand.id());
        assertNotNull(firstBand.genre());
        assertNotNull(firstBand.numPlays());
    }
}