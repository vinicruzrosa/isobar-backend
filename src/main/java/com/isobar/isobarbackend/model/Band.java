package com.isobar.isobarbackend.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class Band {
    String id;
    String name;
    String image;
    String genre;
    String biography;
    Long numPlays;
    List<String> albums;
}