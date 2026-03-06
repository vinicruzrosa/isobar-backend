package com.isobar.isobarbackend.service;

import com.isobar.isobarbackend.dto.BandDTO;
import java.util.List;

public interface BandCacheService {
    List<BandDTO> getCachedBands();
}