package com.fft.farm.controller;

import com.fft.farm.entity.Location;
import com.fft.farm.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('login','createLocation')")
    public ResponseEntity createLocation(@RequestBody Location location) {
        return this.locationService.createLocation(location);
    }

}
