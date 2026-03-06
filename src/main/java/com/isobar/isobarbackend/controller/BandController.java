package com.isobar.isobarbackend.controller;

import com.isobar.isobarbackend.model.Band;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Bands")
@RequestMapping("/api/bands")
public interface BandController {

    @Operation(
            summary = "List bands with advanced filters",
            description = "Returns a list of bands filtering by name, genre, and minimum plays.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping
    ResponseEntity<List<Band>> getBands(
            @Parameter(description = "Partial name of the band") @RequestParam(required = false) String name,
            @Parameter(description = "Musical genre (e.g., alternative)") @RequestParam(required = false) String genre,
            @Parameter(description = "Minimum number of plays") @RequestParam(required = false) Long minPlays,
            @Parameter(description = "Sort by: 'alphabetical' or 'popularity'") @RequestParam(required = false) String sort
    );
}