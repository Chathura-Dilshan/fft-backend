package com.fft.farm.service;

import com.fft.farm.entity.Location;
import org.springframework.http.ResponseEntity;

public interface LocationService {
    ResponseEntity createLocation(Location location);
}
