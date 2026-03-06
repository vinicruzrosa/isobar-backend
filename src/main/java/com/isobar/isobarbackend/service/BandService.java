package com.isobar.isobarbackend.service;


import com.isobar.isobarbackend.model.Band;
import java.util.List;

public interface BandService {
    List<Band> getBands(String name, String sort);
}