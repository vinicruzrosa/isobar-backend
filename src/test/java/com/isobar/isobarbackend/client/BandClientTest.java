package com.isobar.isobarbackend.client;

import com.isobar.isobarbackend.config.RestTemplateConfig;
import com.isobar.isobarbackend.dto.BandDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(BandClient.class)
@ActiveProfiles("test")
@Import(RestTemplateConfig.class)
public class BandClientTest {

    @Autowired
    private BandClient bandClient;

    @Autowired
    private MockRestServiceServer server;

    @Value("${api.bands.url}")
    private String apiUrl;

    @Test
    void deveRetornarListaDeBandsQuandoApiExternaResponderSucesso() {
        server.expect(requestTo(apiUrl))
                .andRespond(withSuccess(
                        new ClassPathResource("mocks/bands.json"),
                        MediaType.APPLICATION_JSON
                ));

        List<BandDTO> result = bandClient.getAllBands();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Pink Floyd", result.get(0).name());
    }
}