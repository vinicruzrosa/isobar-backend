package com.isobar.isobarbackend.model;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class Band {
    private String id;
    private String name;
    private String image;
    private String genre;
    private String biography;
    private Long numPlays;
    private List<String> albums;
}