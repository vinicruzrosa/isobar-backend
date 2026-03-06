package com.isobar.isobarbackend.dto;
import java.util.List;

public record BandDTO(
        String id,
        String name,
        String image,
        String genre,
        String biography,
        Long numPlays,
        List<String> albums
) {}