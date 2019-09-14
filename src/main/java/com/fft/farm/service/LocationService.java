package com.fft.farm.service;

import com.fft.farm.entity.Location;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationService {
    ResponseEntity createLocation(Location location);

    List<Location> findAllLocationsByUserSeq(Integer statusSeq);
}
