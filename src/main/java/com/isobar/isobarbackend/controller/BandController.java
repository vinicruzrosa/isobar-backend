package com.isobar.isobarbackend.controller;


import com.isobar.isobarbackend.model.Band;
import com.isobar.isobarbackend.model.enums.SortOrder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Bands")
@RequestMapping("/api/bands")
public interface BandController {

    @Operation(summary = "Get bands with filters and sorting")
    @GetMapping
    ResponseEntity<List<Band>> getBands(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Long minPlays,
            @Parameter(description = "Sort criteria") @RequestParam(required = false) SortOrder sort
    );
}