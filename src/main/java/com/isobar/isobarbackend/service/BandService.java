package com.isobar.isobarbackend.service;


import com.isobar.isobarbackend.model.Band;
import java.util.List;

public interface BandService {
    List<Band> getBands(String name, String genre, Long minPlays, String sort);
}