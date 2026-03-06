package com.isobar.isobarbackend.service;


import com.isobar.isobarbackend.model.Band;
import com.isobar.isobarbackend.model.enums.SortOrder;

import java.util.List;

public interface BandService {
    List<Band> getBands(String name, String genre, Long minPlays, SortOrder sort);
}