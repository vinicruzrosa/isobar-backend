package com.isobar.isobarbackend.client;


import com.isobar.isobarbackend.dto.BandDTO;

import java.util.List;

public interface BandClient {
    List<BandDTO> getAllBands();
}