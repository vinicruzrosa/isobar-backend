package com.isobar.isobarbackend.controller.impl;


import com.isobar.isobarbackend.controller.BandController;
import com.isobar.isobarbackend.model.Band;
import com.isobar.isobarbackend.service.BandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BandControllerImpl implements BandController {

    private final BandService bandService;

    public BandControllerImpl(BandService bandService) {
        this.bandService = bandService;
    }

    @Override
    public ResponseEntity<List<Band>> getBands(String name, String genre, Long minPlays, String sort) {
        List<Band> bands = bandService.getBands(name, genre, minPlays, sort);
        return ResponseEntity.ok(bands);
    }
}